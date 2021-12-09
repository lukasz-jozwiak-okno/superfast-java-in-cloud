package pl.edu.pw.ljozwiak;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Map;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

  private static final Object lock = new Object();
  private static MongoDBContainer mongo;

  static void initialize() {
    synchronized (lock) {
      if (mongo == null) {
        mongo = new MongoDBContainer(DockerImageName.parse("mongo:4.4.6"));
        mongo.start();
      }
    }
  }

  @Override
  public Map<String, String> start() {
    initialize();
    return Map.of(
        "mongodb.uri",
        "mongodb://"
            + mongo.getContainerIpAddress()
            + ":"
            + mongo.getMappedPort(27017)
            + "/?readPreference=primary&ssl=false&retrywrites=false",
        "mongodb.database",
        "test");
  }

  @Override
  public void stop() {}
}

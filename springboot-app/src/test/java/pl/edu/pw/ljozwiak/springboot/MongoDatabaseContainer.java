package pl.edu.pw.ljozwiak.springboot;

import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

public class MongoDatabaseContainer {

  private static final Object lock = new Object();

  static MongoDBContainer mongo;

  static void initialize() {
    synchronized (lock) {
      if (mongo == null) {
        mongo = new MongoDBContainer(DockerImageName.parse("mongo:4.4.6"));
        mongo.start();
      }
    }
  }
}

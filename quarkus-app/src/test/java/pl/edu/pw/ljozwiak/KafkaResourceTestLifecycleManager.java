package pl.edu.pw.ljozwiak;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Map;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaResourceTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

  private static final Object lock = new Object();
  private static KafkaContainer kafka;

  static void initialize() {
    synchronized (lock) {
      if (kafka == null) {
        kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));
        kafka.start();
      }
    }
  }

  @Override
  public Map<String, String> start() {
    initialize();
    return Map.of("kafka.bootstrap.servers", kafka.getBootstrapServers());
  }

  @Override
  public void stop() {}
}

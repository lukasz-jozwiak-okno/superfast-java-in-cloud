package pl.edu.pw.ljozwiak;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
@QuarkusTestResource(KafkaResourceTestLifecycleManager.class)
public class MovieKafkaTest {

  @Inject MovieService movieService;

  @Test
  public void shouldWriteAndReadKafka() {
    movieService.sendMessageToKafka();
  }
}

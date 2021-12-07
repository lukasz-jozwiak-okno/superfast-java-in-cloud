package pl.edu.pw.ljozwiak;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class ExampleConsumer {

  @Topic("confluent-test")
  void receive(String message) {
    log.info("Message retrieved from confluent; {}", message);
  }
}

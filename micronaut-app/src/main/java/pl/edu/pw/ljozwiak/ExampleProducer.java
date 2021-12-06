package pl.edu.pw.ljozwiak;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(acks = KafkaClient.Acknowledge.ONE)
public interface ExampleProducer {

  @Topic("example")
  void updateTelemetry(Telemetry telemetry);
}

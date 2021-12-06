package pl.edu.pw.ljozwiak;

import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Introspected
@RequiredArgsConstructor
@Singleton
public class ExampleKafkaService {

  private final ExampleProducer exampleProducer;

  public void sendMessageToKafka() {
    Telemetry telemetry = new Telemetry(143);
    exampleProducer.updateTelemetry(telemetry);
    log.info("Message sent to kafka: {}", telemetry);
  }
}

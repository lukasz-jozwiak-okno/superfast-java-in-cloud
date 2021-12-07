package pl.edu.pw.ljozwiak;

import io.smallrye.reactive.messaging.kafka.Record;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class MovieConsumer {

  @Incoming("movies-in")
  public void receive(Record<String, String> record) {
    log.info("Message received key: {}, and value: {}", record.key(), record.value());
  }
}

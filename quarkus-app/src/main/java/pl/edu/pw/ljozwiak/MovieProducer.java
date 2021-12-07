package pl.edu.pw.ljozwiak;

import io.smallrye.reactive.messaging.kafka.Record;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class MovieProducer {

  @Inject
  @Channel("movies-out")
  Emitter<Record<String, String>> emitter;

  public void sendMovieToKafka(Movie movie) {
    emitter.send(Record.of(movie.year, movie.title));
    log.info("Message sent to kafka: {}", movie);
  }
}

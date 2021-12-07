package pl.edu.pw.ljozwiak;

import javax.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class MovieService {

  private final MovieProducer movieProducer;

  public void sendMessageToKafka() {
    Movie movie = new Movie("Matrix", "1998");
    movieProducer.sendMovieToKafka(movie);
  }
}

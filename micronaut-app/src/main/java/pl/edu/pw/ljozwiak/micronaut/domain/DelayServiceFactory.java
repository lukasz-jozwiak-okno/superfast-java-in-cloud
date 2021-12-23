package pl.edu.pw.ljozwiak.micronaut.domain;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@Factory
public class DelayServiceFactory {

  @Singleton
  public DelayService delayService() {
    return new DelayService();
  }
}

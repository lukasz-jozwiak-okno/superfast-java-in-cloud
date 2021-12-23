package pl.edu.pw.ljozwiak.quarkus.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@ApplicationScoped
public class DelayServiceFactory {

  @Produces
  @Singleton
  public DelayService delayService() {
    return new DelayService();
  }
}

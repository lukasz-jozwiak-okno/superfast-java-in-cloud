package pl.edu.pw.ljozwiak.springboot.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@Configuration
public class DelayServiceFactory {

  @Bean
  public DelayService delayService() {
    return new DelayService();
  }
}

package pl.edu.pw.ljozwiak.springboot.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;
import pl.edu.pw.ljozwiak.springboot.domain.AppProperties;

@Configuration
public class RepositoryFactory {

  @Bean
  public ReportRepository reportRepository(MongoClient mongoClient, AppProperties appProperties) {
    return new ReportRepository(mongoClient, appProperties);
  }

  @Bean
  public TelemetryRepository telemetryRepository(
      MongoClient mongoClient, AppProperties appProperties) {
    return new TelemetryRepository(mongoClient, appProperties);
  }
}

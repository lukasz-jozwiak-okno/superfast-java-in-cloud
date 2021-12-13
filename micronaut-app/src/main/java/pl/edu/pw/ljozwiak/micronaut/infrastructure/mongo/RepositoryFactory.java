package pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;
import pl.edu.pw.ljozwiak.micronaut.domain.AppProperties;

@Factory
public class RepositoryFactory {

  @Singleton
  public ReportRepository reportRepository(MongoClient mongoClient, AppProperties appProperties) {
    return new ReportRepository(mongoClient, appProperties);
  }

  @Singleton
  public TelemetryRepository telemetryRepository(
      MongoClient mongoClient, AppProperties appProperties) {
    return new TelemetryRepository(mongoClient, appProperties);
  }
}

package pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;
import pl.edu.pw.ljozwiak.quarkus.domain.AppProperties;

@ApplicationScoped
public class RepositoryFactory {

  @Produces
  public pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository reportRepository(
      MongoClient mongoClient, AppProperties appProperties) {
    return new ReportRepository(mongoClient, appProperties);
  }

  @Produces
  public pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository telemetryRepository(
      MongoClient mongoClient, AppProperties appProperties) {
    return new TelemetryRepository(mongoClient, appProperties);
  }
}

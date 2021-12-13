package pl.edu.pw.ljozwiak.quarkus.domain;

import javax.enterprise.context.ApplicationScoped;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import pl.edu.pw.ljozwiak.coreprocessing.repository.MongoProperties;

@ApplicationScoped
@Data
public class AppProperties implements MongoProperties {

  @ConfigProperty(name = "app.mongoDbName")
  String mongoDbName;

  @ConfigProperty(name = "app.mongoDbTelemetryCollection")
  String mongoDbTelemetryCollection;

  @ConfigProperty(name = "app.mongoDbReportCollection")
  String mongoDbReportCollection;
}

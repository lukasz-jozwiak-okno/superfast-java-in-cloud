package pl.edu.pw.ljozwiak.quarkus.domain;

import javax.enterprise.context.ApplicationScoped;
import lombok.Data;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Data
public class AppProperties {

  @ConfigProperty(name = "app.mongoDbName")
  String mongoDbName;

  @ConfigProperty(name = "app.mongoDbTelemetryCollection")
  String mongoDbTelemetryCollection;

  @ConfigProperty(name = "app.mongoDbReportCollection")
  String mongoDbReportCollection;
}

package pl.edu.pw.ljozwiak.micronaut.domain;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;
import pl.edu.pw.ljozwiak.coreprocessing.repository.MongoProperties;

@ConfigurationProperties("app")
@Data
public class AppProperties implements MongoProperties {

  private String mongoDbName;
  private String mongoDbTelemetryCollection;
  private String mongoDbReportCollection;
}

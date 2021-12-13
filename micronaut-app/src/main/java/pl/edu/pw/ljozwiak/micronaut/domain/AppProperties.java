package pl.edu.pw.ljozwiak.micronaut.domain;

import io.micronaut.context.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties("app")
@Data
public class AppProperties {

  private String mongoDbName;
  private String mongoDbTelemetryCollection;
  private String mongoDbReportCollection;
}

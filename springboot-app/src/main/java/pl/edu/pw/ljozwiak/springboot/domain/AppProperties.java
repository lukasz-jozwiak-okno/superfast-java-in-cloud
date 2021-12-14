package pl.edu.pw.ljozwiak.springboot.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pl.edu.pw.ljozwiak.coreprocessing.repository.MongoProperties;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties implements MongoProperties {

  private String mongoDbName;
  private String mongoDbTelemetryCollection;
  private String mongoDbReportCollection;
}

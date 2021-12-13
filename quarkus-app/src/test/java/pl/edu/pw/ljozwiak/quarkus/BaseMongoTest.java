package pl.edu.pw.ljozwiak.quarkus;

import com.mongodb.client.MongoClient;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import pl.edu.pw.ljozwiak.quarkus.domain.AppProperties;

public abstract class BaseMongoTest {

  @Inject MongoClient mongoClient;

  @Inject AppProperties appProperties;

  @BeforeEach
  void setup() {
    mongoClient
        .getDatabase(appProperties.getMongoDbName())
        .getCollection(appProperties.getMongoDbTelemetryCollection())
        .drop();
    mongoClient
        .getDatabase(appProperties.getMongoDbName())
        .getCollection(appProperties.getMongoDbReportCollection())
        .drop();
  }
}

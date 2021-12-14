package pl.edu.pw.ljozwiak.springboot;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ljozwiak.springboot.domain.AppProperties;

public abstract class BaseMongoTest {

  @Autowired protected MongoClient mongoClient;

  @Autowired AppProperties appProperties;

  @BeforeEach
  public void setup() {
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

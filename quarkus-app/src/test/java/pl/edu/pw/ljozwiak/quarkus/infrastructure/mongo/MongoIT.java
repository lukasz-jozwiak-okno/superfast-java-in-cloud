package pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import io.quarkus.test.junit.QuarkusTest;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ljozwiak.quarkus.BaseMongoTest;

@QuarkusTest
public class MongoIT extends BaseMongoTest {

  @Inject MongoClient mongoClient;

  @Test
  public void shouldConnectToMongo() {
    Assertions.assertTrue(
        StreamSupport.stream(mongoClient.listDatabaseNames().spliterator(), false)
            .anyMatch(dbName -> dbName.equals("admin")));
  }
}

package pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo;

import com.google.common.collect.Lists;
import com.mongodb.client.MongoClient;
import io.quarkus.test.junit.QuarkusTest;
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
        Lists.newArrayList(mongoClient.listDatabaseNames()).stream()
            .anyMatch(dbName -> dbName.equals("admin")));
  }
}

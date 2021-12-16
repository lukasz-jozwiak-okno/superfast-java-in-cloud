package pl.edu.pw.ljozwiak.springboot.infrastructure.mongo;

import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pw.ljozwiak.springboot.BaseMongoTest;
import pl.edu.pw.ljozwiak.springboot.MongoDatabaseInitializer;

@SpringBootTest
@ContextConfiguration(initializers = MongoDatabaseInitializer.class)
public class MongoIT extends BaseMongoTest {

  @Test
  public void shouldConnectToMongo() {
    Assertions.assertTrue(
        StreamSupport.stream(mongoClient.listDatabaseNames().spliterator(), false)
            .anyMatch(dbName -> dbName.equals("admin")));
  }
}

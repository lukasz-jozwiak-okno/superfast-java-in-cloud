package pl.edu.pw.ljozwiak.springboot;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MongoDatabaseInitializer
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {

    MongoDatabaseContainer.initialize();

    TestPropertyValues values =
        TestPropertyValues.of(
            "spring.data.mongodb.uri="
                + "mongodb://"
                + MongoDatabaseContainer.mongo.getContainerIpAddress()
                + ":"
                + MongoDatabaseContainer.mongo.getMappedPort(27017)
                + "/?readPreference=primary&ssl=false&retrywrites=false",
            "spring.data.mongodb.database=test");

    values.applyTo(applicationContext);
  }
}

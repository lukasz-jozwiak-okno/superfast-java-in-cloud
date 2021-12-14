package pl.edu.pw.ljozwiak.springboot.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pw.ljozwiak.springboot.MongoDatabaseInitializer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = MongoDatabaseInitializer.class)
public class PingControllerIT {

  @LocalServerPort int port;

  @Test
  public void testHelloEndpoint() {
    given()
        .when()
        .get("http://localhost:" + port + "/ping")
        .then()
        .statusCode(200)
        .body(is("ping"));
  }
}

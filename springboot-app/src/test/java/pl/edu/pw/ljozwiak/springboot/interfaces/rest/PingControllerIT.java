package pl.edu.pw.ljozwiak.springboot.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;
import pl.edu.pw.ljozwiak.springboot.MongoDatabaseInitializer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = MongoDatabaseInitializer.class)
public class PingControllerIT {

  @SpyBean DelayService delayService;
  @LocalServerPort int port;

  @Test
  public void testHelloEndpoint() {
    given()
        .when()
        .get("http://localhost:" + port + "/ping")
        .then()
        .statusCode(200)
        .body(is("ping"));
    verify(delayService, times(1)).delay((Integer) null);
  }

  @Test
  public void shouldProcessRequestWithDelay() {
    given()
        .queryParam("delay", 10)
        .get("http://localhost:" + port + "/ping")
        .then()
        .statusCode(200);
    verify(delayService, times(1)).delay(10);
  }
}

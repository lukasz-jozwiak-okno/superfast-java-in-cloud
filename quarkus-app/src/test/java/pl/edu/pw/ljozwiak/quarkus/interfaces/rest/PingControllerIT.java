package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@QuarkusTest
public class PingControllerIT {

  @InjectMock(convertScopes = true)
  DelayService delayService;

  @Test
  public void testHelloEndpoint() {
    given().when().get("/ping").then().statusCode(200).body(is("ping"));
    verify(delayService, times(1)).delay((Integer) null);
  }

  @Test
  public void shouldProcessRequestWithDelay() {
    given().queryParam("delay", 10).get("/ping").then().statusCode(200);
    verify(delayService, times(1)).delay(10);
  }
}

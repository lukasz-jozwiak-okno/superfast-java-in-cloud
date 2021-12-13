package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PingControllerIT {

  @Test
  public void testHelloEndpoint() {
    given().when().get("/ping").then().statusCode(200).body(is("ping"));
  }
}

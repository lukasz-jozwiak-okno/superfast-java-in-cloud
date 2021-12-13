package pl.edu.pw.ljozwiak.micronaut.interfaces.rest

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.hamcrest.CoreMatchers
import spock.lang.Specification

import static io.restassured.RestAssured.given

@MicronautTest
class PingControllerIT extends Specification {

  @Inject
  EmbeddedServer server

  def 'should return ping'() {
    expect:
      given()
          .get("${server.getURL()}ping")
          .then()
          .statusCode(200)
          .body(CoreMatchers.equalTo('ping'))
  }
}

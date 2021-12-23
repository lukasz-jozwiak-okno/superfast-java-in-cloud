package pl.edu.pw.ljozwiak.micronaut.interfaces.rest

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import pl.edu.pw.ljozwiak.coreprocessing.DelayService
import spock.lang.Specification

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo

@MicronautTest
class PingControllerIT extends Specification {

  @Inject
  EmbeddedServer server

  @Inject
  DelayService delayService

  @MockBean(DelayService)
  DelayService delayService() {
    Mock(DelayService)
  }

  def 'should return ping'() {
    when:
      given()
          .get("${server.getURL()}ping")
          .then()
          .statusCode(200)
          .body(equalTo('ping'))
    then:
      1 * delayService.delay(Optional.empty())
  }

  def 'should process request with delay'() {
    when:
      given().queryParam("delay", 10)
          .get("${server.getURL()}ping")
          .then()
          .statusCode(200)
    then:
      1 * delayService.delay(Optional.of(10))
  }
}

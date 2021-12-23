package pl.edu.pw.ljozwiak.micronaut.interfaces.rest

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import pl.edu.pw.ljozwiak.coreprocessing.DelayService
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository
import pl.edu.pw.ljozwiak.micronaut.BaseMongoTest

import java.time.LocalDateTime
import java.time.ZoneOffset

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.containsString

@MicronautTest
class SingleControllerIT extends BaseMongoTest {

  @Inject
  EmbeddedServer server

  @Inject
  TelemetryRepository telemetryRepository

  @Inject
  ReportRepository reportRepository

  @Inject
  DelayService delayService

  @MockBean(DelayService)
  DelayService delayService() {
    Mock(DelayService)
  }

  def 'should return report of single telemetry'() {
    given:
      def telemetry1 = Telemetry.builder()
          .id('1')
          .speed(10)
          .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
          .fuelLevel(123.3)
          .build()
      def telemetry2 = Telemetry.builder()
          .id('2')
          .speed(30)
          .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
          .fuelLevel(34.3)
          .build()
      telemetryRepository.insertOne(telemetry1)
      telemetryRepository.insertOne(telemetry2)

    when:
      given().get("${server.getURL()}single")
          .then()
          .statusCode(200)
          .body(containsString("10.0"))
    then:
      1 * delayService.delay(Optional.empty())
  }

  def 'should process request with delay'() {
    when:
      given().queryParam("delay", 10)
          .get("${server.getURL()}single")
          .then()
          .statusCode(200)
    then:
      1 * delayService.delay(Optional.of(10))
  }
}

package pl.edu.pw.ljozwiak.micronaut.interfaces.rest

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import pl.edu.pw.ljozwiak.BaseMongoTest
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.ReportRepository
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.TelemetryRepository

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue

@MicronautTest
class SingleControllerIT extends BaseMongoTest {

  @Inject
  EmbeddedServer server

  @Inject
  TelemetryRepository telemetryRepository

  @Inject
  ReportRepository reportRepository

  def 'should return report of single telemetry'() {
    given:
      def telemetry1 = Telemetry.builder().speed(10).id('1').build()
      def telemetry2 = Telemetry.builder().speed(30).id('2').build()
      telemetryRepository.insertOne(telemetry1)
      telemetryRepository.insertOne(telemetry2)

    expect:
      given().get("${server.getURL()}single")
          .then()
          .statusCode(200)
          .body('id', notNullValue())
          .body('averageSpeed', equalTo(10.0F))
  }
}

package pl.edu.pw.ljozwiak.micronaut.interfaces.rest

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import pl.edu.pw.ljozwiak.BaseMongoTest
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.ReportRepository
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.TelemetryRepository

import static io.restassured.RestAssured.given
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue

@MicronautTest
class BatchControllerIT extends BaseMongoTest {

  @Inject
  EmbeddedServer server

  @Inject
  TelemetryRepository telemetryRepository

  @Inject
  ReportRepository reportRepository

  def 'should return report of all telemetries'() {
    given:
      def telemetry1 = Telemetry.builder().speed(10).id('1').build()
      def telemetry2 = Telemetry.builder().speed(30).id('2').build()
      telemetryRepository.insertOne(telemetry1)
      telemetryRepository.insertOne(telemetry2)

    expect:
      given().get("${server.getURL()}batch")
          .then()
          .statusCode(200)
          .body('id', notNullValue())
          .body('averageSpeed', equalTo(20.0F))
    and:
      List<Report> reports = reportRepository.findAll()
      reports.size() == 1
  }
}

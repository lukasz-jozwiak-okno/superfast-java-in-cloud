package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.quarkus.BaseMongoTest;
import pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo.ReportRepository;
import pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo.TelemetryRepository;

@QuarkusTest
public class BatchControllerIT extends BaseMongoTest {

  @Inject TelemetryRepository telemetryRepository;

  @Inject ReportRepository reportRepository;

  @Test
  public void shouldReturnReportOfAllTelemetries() {
    var telemetry1 = Telemetry.builder().speed(10).id("1").build();
    var telemetry2 = Telemetry.builder().speed(30).id("2").build();
    telemetryRepository.insertOne(telemetry1);
    telemetryRepository.insertOne(telemetry2);

    given()
        .get("/batch")
        .then()
        .statusCode(200)
        .body("id", notNullValue())
        .body("averageSpeed", equalTo(20.0F));

    List<Report> reports = reportRepository.findAll();
    assertEquals(1, reports.size());
  }
}

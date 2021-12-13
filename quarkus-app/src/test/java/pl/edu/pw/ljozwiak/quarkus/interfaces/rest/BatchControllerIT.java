package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;
import pl.edu.pw.ljozwiak.quarkus.BaseMongoTest;

@QuarkusTest
public class BatchControllerIT extends BaseMongoTest {

  @Inject TelemetryRepository telemetryRepository;

  @Inject ReportRepository reportRepository;

  @Test
  public void shouldReturnReportOfAllTelemetries() {
    var telemetry1 =
        Telemetry.builder()
            .id("1")
            .speed(10)
            .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .fuelLevel(123.3)
            .build();
    var telemetry2 =
        Telemetry.builder()
            .id("2")
            .speed(30)
            .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .fuelLevel(34.3)
            .build();
    telemetryRepository.insertOne(telemetry1);
    telemetryRepository.insertOne(telemetry2);

    given().get("/batch").then().statusCode(200).body(containsString("20.0"));

    List<Report> reports = reportRepository.findAll();
    assertEquals(1, reports.size());
  }
}

package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;
import pl.edu.pw.ljozwiak.quarkus.BaseMongoTest;

@QuarkusTest
public class SingleControllerIT extends BaseMongoTest {

  @Inject TelemetryRepository telemetryRepository;

  @Inject ReportRepository reportRepository;

  @InjectMock(convertScopes = true)
  DelayService delayService;

  @Test
  public void shouldReturnReportOfSingleTelemetry() {
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

    given().get("/single").then().statusCode(200).body(containsString("10.0"));

    List<Report> reports = reportRepository.findAll();
    assertEquals(1, reports.size());
    verify(delayService, times(1)).delay((Integer) null);
  }

  @Test
  public void shouldProcessRequestWithDelay() {
    given().queryParam("delay", 10).get("/single").then().statusCode(200);
    verify(delayService, times(1)).delay(10);
  }
}

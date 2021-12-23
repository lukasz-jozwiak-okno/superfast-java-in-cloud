package pl.edu.pw.ljozwiak.micronaut.interfaces.rest;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;
import pl.edu.pw.ljozwiak.coreprocessing.TelemetryProcessor;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;

@RequiredArgsConstructor
@Controller("/single")
public class SingleController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;
  private final DelayService delayService;

  @Get
  @Produces(MediaType.TEXT_PLAIN)
  public String single(@QueryValue Optional<Integer> delay) {

    delayService.delay(delay);

    Telemetry telemetry = telemetryRepository.getFirst();
    Report report = new TelemetryProcessor().process(telemetry);
    reportRepository.insertOne(report);

    return report.toString();
  }
}

package pl.edu.pw.ljozwiak.micronaut.interfaces.rest;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.TelemetryProcessor;
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.ReportRepository;
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.TelemetryRepository;

@RequiredArgsConstructor
@Controller("/single")
public class SingleController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;

  @Get
  @Produces(MediaType.APPLICATION_JSON)
  public Report single() {

    Telemetry telemetry = telemetryRepository.getFirst();
    Report report = new TelemetryProcessor().process(telemetry);
    reportRepository.insertOne(report);

    return report;
  }
}

package pl.edu.pw.ljozwiak.micronaut.interfaces.rest;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.TelemetryProcessor;
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.ReportRepository;
import pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo.TelemetryRepository;

@RequiredArgsConstructor
@Controller("/batch")
public class BatchController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;

  @Get
  @Produces(MediaType.APPLICATION_JSON)
  public Report batch() {

    List<Telemetry> telemetries = telemetryRepository.getAll();
    Report report = new TelemetryProcessor().process(telemetries);
    reportRepository.insertOne(report);

    return report;
  }
}

package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.TelemetryProcessor;
import pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo.ReportRepository;
import pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo.TelemetryRepository;

@Path("/single")
@RequiredArgsConstructor
public class SingleController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Report single() {
    Telemetry telemetry = telemetryRepository.getFirst();
    Report report = new TelemetryProcessor().process(telemetry);
    reportRepository.insertOne(report);

    return report;
  }
}

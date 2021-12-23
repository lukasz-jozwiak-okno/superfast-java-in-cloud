package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;
import pl.edu.pw.ljozwiak.coreprocessing.TelemetryProcessor;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;

@Path("/batch")
@RequiredArgsConstructor
public class BatchController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;
  private final DelayService delayService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String single(@QueryParam("delay") Integer delay) {
    delayService.delay(delay);

    List<Telemetry> telemetries = telemetryRepository.getAll();
    Report report = new TelemetryProcessor().process(telemetries);
    reportRepository.insertOne(report);

    return report.toString();
  }
}

package pl.edu.pw.ljozwiak.springboot.interfaces.rest;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ljozwiak.coreprocessing.TelemetryProcessor;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.repository.ReportRepository;
import pl.edu.pw.ljozwiak.coreprocessing.repository.TelemetryRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/batch")
public class BatchController {

  private final TelemetryRepository telemetryRepository;
  private final ReportRepository reportRepository;

  @GetMapping(produces = TEXT_PLAIN_VALUE)
  public String batch() {

    List<Telemetry> telemetries = telemetryRepository.getAll();
    Report report = new TelemetryProcessor().process(telemetries);
    reportRepository.insertOne(report);

    return report.toString();
  }
}

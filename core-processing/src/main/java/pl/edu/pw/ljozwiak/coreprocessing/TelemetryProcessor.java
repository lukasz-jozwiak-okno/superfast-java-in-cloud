package pl.edu.pw.ljozwiak.coreprocessing;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;
import pl.edu.pw.ljozwiak.coreprocessing.utils.Preconditions;

public class TelemetryProcessor {

  public Report process(Telemetry telemetry) {
    return telemetry != null
        ? process(Collections.singletonList(telemetry))
        : Report.builder().id(UUID.randomUUID().toString()).averageSpeed(0.0).build();
  }

  public Report process(List<Telemetry> telemetries) {
    Preconditions.checkNotNull(telemetries, "telemetries is null");

    double averageSpeed = telemetries.stream().mapToInt(Telemetry::getSpeed).average().orElse(0.0);

    return Report.builder().id(UUID.randomUUID().toString()).averageSpeed(averageSpeed).build();
  }
}

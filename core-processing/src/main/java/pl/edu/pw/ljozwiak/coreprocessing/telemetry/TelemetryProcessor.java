package pl.edu.pw.ljozwiak.coreprocessing.telemetry;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.UUID;

public class TelemetryProcessor {

  public Report process(Telemetry telemetry) {
    return telemetry != null
        ? process(Lists.newArrayList(telemetry))
        : Report.builder().id(UUID.randomUUID().toString()).averageSpeed(0.0).build();
  }

  public Report process(List<Telemetry> telemetries) {
    checkNotNull(telemetries, "telemetries is null");

    double averageSpeed = telemetries.stream().mapToInt(Telemetry::getSpeed).average().orElse(0.0);

    return Report.builder().id(UUID.randomUUID().toString()).averageSpeed(averageSpeed).build();
  }
}

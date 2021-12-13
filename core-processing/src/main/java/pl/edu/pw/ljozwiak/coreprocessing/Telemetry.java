package pl.edu.pw.ljozwiak.coreprocessing;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Telemetry {
  String id;
  int speed;
  double fuel;
}

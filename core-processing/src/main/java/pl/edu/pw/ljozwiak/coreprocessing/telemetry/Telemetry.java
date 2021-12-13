package pl.edu.pw.ljozwiak.coreprocessing.telemetry;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Telemetry {
  private String id;
  private Instant time;
  private int speed;
  private double fuelLevel;
}

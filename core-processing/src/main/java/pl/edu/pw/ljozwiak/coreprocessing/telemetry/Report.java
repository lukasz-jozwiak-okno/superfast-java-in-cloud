package pl.edu.pw.ljozwiak.coreprocessing.telemetry;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

  private String id;
  private double averageSpeed;
}

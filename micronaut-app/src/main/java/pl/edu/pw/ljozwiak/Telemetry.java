package pl.edu.pw.ljozwiak;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class Telemetry {

  private final int speed;
}

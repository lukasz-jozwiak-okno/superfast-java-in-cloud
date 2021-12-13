package pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo;

import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.ReportCodec;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.TelemetryCodec;

/** https://stackoverflow.com/questions/57659579/registering-codecs-for-mongodb-using-micronaut */
@Factory
public class CodecsFactory {

  @Singleton
  public TelemetryCodec telemetryCodec() {
    return new TelemetryCodec();
  }

  @Singleton
  public ReportCodec reportCodec() {
    return new ReportCodec();
  }
}

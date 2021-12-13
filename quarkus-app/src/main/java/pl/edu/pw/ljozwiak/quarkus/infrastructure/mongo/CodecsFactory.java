package pl.edu.pw.ljozwiak.quarkus.infrastructure.mongo;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.ReportCodec;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.TelemetryCodec;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;

/** https://stackoverflow.com/questions/61750243/registering-codec-in-mongodb-using-quarkus */
public class CodecsFactory implements CodecProvider {

  @Override
  @SuppressWarnings("unchecked")
  public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
    if (clazz.equals(Telemetry.class)) {
      return (Codec<T>) new TelemetryCodec();
    } else if (clazz.equals(Report.class)) {
      return (Codec<T>) new ReportCodec();
    }

    return null;
  }
}

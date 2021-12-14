package pl.edu.pw.ljozwiak.springboot.infrastructure.mongo;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.ReportCodec;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.TelemetryCodec;

@Configuration
public class CodecsFactory {

  @Bean
  CodecRegistry codecRegistry() {
    return CodecRegistries.fromCodecs(new TelemetryCodec(), new ReportCodec());
  }
}

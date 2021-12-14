package pl.edu.pw.ljozwiak.springboot.infrastructure.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.ReportCodec;
import pl.edu.pw.ljozwiak.coreprocessing.codecs.TelemetryCodec;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.uri}")
  private String connectionString;

  @Bean
  @Primary
  public MongoClient mongoClient() {
    CodecRegistry registry =
        CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromCodecs(new TelemetryCodec(), new ReportCodec()));

    MongoClientSettings settings =
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(connectionString))
            .codecRegistry(registry)
            .build();

    return MongoClients.create(settings);
  }
}

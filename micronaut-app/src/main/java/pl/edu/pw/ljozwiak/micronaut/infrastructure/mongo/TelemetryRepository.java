package pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.micronaut.domain.AppProperties;

@RequiredArgsConstructor
@Singleton
public class TelemetryRepository {

  private final MongoClient mongoClient;
  private final AppProperties appProperties;

  public Telemetry getFirst() {
    return getCollection().find().first();
  }

  public List<Telemetry> getAll() {
    var cursor = getCollection().find().cursor();

    var telemetries = new ArrayList<Telemetry>();

    while (cursor.hasNext()) {
      telemetries.add(cursor.next());
    }

    return telemetries;
  }

  public String insertOne(Telemetry telemetry) {
    return getCollection().insertOne(telemetry).getInsertedId().asString().getValue();
  }

  private MongoCollection<Telemetry> getCollection() {
    return mongoClient
        .getDatabase(appProperties.getMongoDbName())
        .getCollection(appProperties.getMongoDbTelemetryCollection(), Telemetry.class);
  }
}

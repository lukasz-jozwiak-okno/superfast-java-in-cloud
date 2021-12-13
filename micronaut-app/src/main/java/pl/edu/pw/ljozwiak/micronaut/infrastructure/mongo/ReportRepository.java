package pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Report;
import pl.edu.pw.ljozwiak.micronaut.domain.AppProperties;

@RequiredArgsConstructor
@Singleton
public class ReportRepository {

  private final MongoClient mongoClient;
  private final AppProperties appProperties;

  public String insertOne(Report report) {
    return getCollection().insertOne(report).getInsertedId().asString().getValue();
  }

  public List<Report> findAll() {
    var cursor = getCollection().find().cursor();

    var reports = new ArrayList<Report>();

    while (cursor.hasNext()) {
      reports.add(cursor.next());
    }

    return reports;
  }

  private MongoCollection<Report> getCollection() {
    return mongoClient
        .getDatabase(appProperties.getMongoDbName())
        .getCollection(appProperties.getMongoDbReportCollection(), Report.class);
  }
}

package pl.edu.pw.ljozwiak.coreprocessing.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;

@RequiredArgsConstructor
public class ReportRepository {

  private final MongoClient mongoClient;
  private final MongoProperties mongoProperties;

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
        .getDatabase(mongoProperties.getMongoDbName())
        .getCollection(mongoProperties.getMongoDbReportCollection(), Report.class);
  }
}

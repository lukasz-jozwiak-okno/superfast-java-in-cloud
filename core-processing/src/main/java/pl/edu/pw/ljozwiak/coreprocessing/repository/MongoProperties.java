package pl.edu.pw.ljozwiak.coreprocessing.repository;

public interface MongoProperties {

  String getMongoDbName();

  String getMongoDbReportCollection();

  String getMongoDbTelemetryCollection();
}

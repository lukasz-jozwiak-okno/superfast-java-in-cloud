package pl.edu.pw.ljozwiak.micronaut

import com.mongodb.client.MongoClient
import io.micronaut.test.support.TestPropertyProvider
import jakarta.inject.Inject
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import pl.edu.pw.ljozwiak.micronaut.domain.AppProperties
import spock.lang.Shared
import spock.lang.Specification

abstract class BaseMongoTest extends Specification implements TestPropertyProvider {

  private static final Object lock = new Object()

  @Shared
  static MongoDBContainer mongo

  @Inject
  MongoClient mongoClient

  @Inject
  AppProperties appProperties

  def setupSpec() {
    initialize()
  }

  def setup() {
    mongoClient.getDatabase(appProperties.mongoDbName).getCollection(appProperties.mongoDbTelemetryCollection).drop()
    mongoClient.getDatabase(appProperties.mongoDbName).getCollection(appProperties.mongoDbReportCollection).drop()
  }

  static def initialize() {
    synchronized (lock) {
      if (mongo == null) {
        mongo = new MongoDBContainer(DockerImageName.parse("mongo:4.4.6"));
        mongo.start()
      }
    }
  }

  @Override
  Map<String, String> getProperties() {
    initialize()
    ["mongodb.uri"     :
         "mongodb://${mongo.getContainerIpAddress()}:${mongo.getMappedPort(27017)}/?readPreference=primary&ssl=false&retrywrites=false",
     "mongodb.database": "test"]
  }
}

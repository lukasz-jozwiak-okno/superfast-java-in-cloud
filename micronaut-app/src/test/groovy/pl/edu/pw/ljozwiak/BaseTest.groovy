package pl.edu.pw.ljozwiak

import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName;
import spock.lang.Shared;
import spock.lang.Specification

class BaseTest extends Specification implements TestPropertyProvider {

    private static final Object lock = new Object()

    @Shared
    static MongoDBContainer mongo

    def setupSpec() {
        initialize()
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

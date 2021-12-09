package pl.edu.pw.ljozwiak

import com.mongodb.client.MongoClient
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject

@MicronautTest
class MongoIT extends BaseTest {

    @Inject
    MongoClient mongoClient

    def 'should connect to mongo'() {
        expect:
            mongoClient.listDatabaseNames().find { it == "admin" }
    }
}

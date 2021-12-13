package pl.edu.pw.ljozwiak.micronaut.infrastructure.mongo

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import pl.edu.pw.ljozwiak.BaseMongoTest

@MicronautTest
class MongoIT extends BaseMongoTest {

  def 'should connect to mongo'() {
    expect:
      mongoClient.listDatabaseNames().find { it == "admin" }
  }
}

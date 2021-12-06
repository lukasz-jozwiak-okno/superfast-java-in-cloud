package pl.edu.pw.ljozwiak


import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

class BaseKafkaTest extends Specification implements TestPropertyProvider {
    private static final Object lock = new Object()

    @Shared
    static KafkaContainer kafka

    def setupSpec() {
        initialize()
    }

    static def initialize() {
        synchronized (lock) {
            if (kafka == null) {
                kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
                kafka.start()
            }
        }
    }

    @Override
    Map<String, String> getProperties() {
        initialize()
        ["kafka.bootstrap.servers": kafka.getBootstrapServers()]
    }
}

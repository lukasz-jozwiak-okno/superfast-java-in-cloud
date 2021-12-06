package pl.edu.pw.ljozwiak

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.awaitility.Awaitility

import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.TimeUnit

@MicronautTest
class KafkaSimpleTest extends BaseKafkaTest {

    static def received = new ConcurrentLinkedDeque<Telemetry>()

    @Inject
    ExampleKafkaService exampleKafkaService

    @Inject
    TestListener testListener

    def 'should read from kafka'() {
        when:
            exampleKafkaService.sendMessageToKafka()
        then:
            Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> !received.isEmpty())
    }

    @KafkaListener(offsetReset = OffsetReset.EARLIEST, groupId = "TestListener")
    static class TestListener {

        @Topic("example")
        void receive(Telemetry telemetry) {
            received.add(telemetry)
        }
    }
}

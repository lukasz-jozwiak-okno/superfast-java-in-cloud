package pl.edu.pw.ljozwiak.coreprocessing

import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime
import java.time.ZoneOffset

class TelemetryProcessorTest extends Specification {

  @Subject
  def telemetryProcessor = new TelemetryProcessor()

  def 'should process multiple telemetries'() {
    given:
      def telemetry1 = Telemetry.builder()
          .id('1')
          .speed(10)
          .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
          .fuelLevel(123.3)
          .build()
      def telemetry2 = Telemetry.builder()
          .id('2')
          .speed(30)
          .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
          .fuelLevel(34.3)
          .build()
    when:
      def report = telemetryProcessor.process(Arrays.asList(telemetry1, telemetry2))
    then:
      report.id != null
      report.averageSpeed == 20.0d
  }

  def 'should process single telemetry'() {
    given:
      def telemetry = Telemetry.builder()
          .id('1')
          .speed(10)
          .time(LocalDateTime.now().toInstant(ZoneOffset.UTC))
          .fuelLevel(123.3)
          .build()
    when:
      def report = telemetryProcessor.process(telemetry)
    then:
      report.id != null
      report.averageSpeed == 10.0d
  }
}

package pl.edu.pw.ljozwiak.datagenerator.process;

import com.github.javafaker.Faker;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.edu.pw.ljozwiak.coreprocessing.telemetry.Telemetry;
import pl.edu.pw.ljozwiak.datagenerator.argument.GenerateDataCommand;

@RequiredArgsConstructor
@Component
public class GenerateCommandHandler {

  private static final SecureRandom random = new SecureRandom();
  private static final Faker faker = new Faker(random);
  private final MongoTemplate mongoTemplate;

  public void handle(GenerateDataCommand command) {

    switch (command.getTargetEntity()) {
      case telemetry:
        generateTelemetry(command.getNumberOfRecords());
        break;
      default:
        throw new IllegalStateException("Unknown entity");
    }
  }

  private void generateTelemetry(Integer numberOfRecords) {
    mongoTemplate.insertAll(fakeTelemetries(numberOfRecords));
  }

  static <T> List<T> fakeCollection(int numberOfRecords, Supplier<T> supplier) {
    return IntStream.range(0, numberOfRecords)
        .mapToObj(ignored -> supplier.get())
        .collect(Collectors.toList());
  }

  static List<Telemetry> fakeTelemetries(int numberOfRecords) {
    return fakeCollection(numberOfRecords, GenerateCommandHandler::fakeTelemetry);
  }

  static Telemetry fakeTelemetry() {
    return Telemetry.builder()
        .id(UUID.randomUUID().toString())
        .speed(faker.number().numberBetween(0, 300))
        .fuelLevel(faker.number().numberBetween(0, 100))
        .build();
  }
}

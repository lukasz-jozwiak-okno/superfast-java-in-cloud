package pl.edu.pw.ljozwiak.datagenerator.process;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import pl.edu.pw.ljozwiak.datagenerator.argument.ClearCommand;

@RequiredArgsConstructor
@Component
public class ClearCommandHandler {

  private final MongoTemplate mongoTemplate;

  public void handle(ClearCommand command) {
    mongoTemplate.dropCollection(command.getTargetEntity().name());
  }
}

package pl.edu.pw.ljozwiak.datagenerator.process;

import com.beust.jcommander.JCommander;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pw.ljozwiak.datagenerator.argument.ClearCommand;
import pl.edu.pw.ljozwiak.datagenerator.argument.GenerateDataCommand;

@RequiredArgsConstructor
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

  private final GenerateCommandHandler generateCommandHandler;
  private final ClearCommandHandler clearCommandHandler;

  @Override
  public void run(String... args) throws Exception {
    GenerateDataCommand generateDataCommand = new GenerateDataCommand();
    ClearCommand clearCommand = new ClearCommand();

    JCommander jc =
        JCommander.newBuilder().addCommand(generateDataCommand).addCommand(clearCommand).build();

    jc.parse(args);

    switch (jc.getParsedCommand()) {
      case GenerateDataCommand.NAME:
        generateCommandHandler.handle(generateDataCommand);
        break;
      case ClearCommand.NAME:
        clearCommandHandler.handle(clearCommand);
        break;
      default:
        throw new IllegalArgumentException("Unknown command");
    }
  }
}

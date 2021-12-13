package pl.edu.pw.ljozwiak.datagenerator.argument;

import static pl.edu.pw.ljozwiak.datagenerator.argument.ClearCommand.NAME;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pw.ljozwiak.datagenerator.process.TargetEntity;

@Parameters(commandNames = NAME, commandDescription = "Clear entity")
@Getter
@Setter
@ToString
public class ClearCommand {

  public static final String NAME = "clear";

  @Parameter(
      names = "-e",
      description = "Target entity",
      converter = TargetEntityConverter.class,
      required = true)
  private TargetEntity targetEntity;
}

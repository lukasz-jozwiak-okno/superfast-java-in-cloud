package pl.edu.pw.ljozwiak.datagenerator.argument;

import static pl.edu.pw.ljozwiak.datagenerator.argument.GenerateDataCommand.NAME;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.edu.pw.ljozwiak.datagenerator.process.TargetEntity;

@Parameters(commandNames = NAME, commandDescription = "Train neural network")
@Getter
@Setter
@ToString
public class GenerateDataCommand {

  public static final String NAME = "generate";

  @Parameter(
      names = "-e",
      description = "Target entity",
      converter = TargetEntityConverter.class,
      required = true)
  private TargetEntity targetEntity;

  @Parameter(names = "-i", description = "Number of records", required = true)
  private Integer numberOfRecords;
}

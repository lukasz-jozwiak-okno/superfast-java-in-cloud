package pl.edu.pw.ljozwiak.datagenerator.argument;

import com.beust.jcommander.IStringConverter;
import pl.edu.pw.ljozwiak.datagenerator.process.TargetEntity;

public class TargetEntityConverter implements IStringConverter<TargetEntity> {

  @Override
  public TargetEntity convert(String value) {

    try {
      return TargetEntity.valueOf(value);
    } catch (Exception e) {
      throw new IllegalArgumentException(String.format("Target entity - [%s] - not found", value));
    }
  }
}

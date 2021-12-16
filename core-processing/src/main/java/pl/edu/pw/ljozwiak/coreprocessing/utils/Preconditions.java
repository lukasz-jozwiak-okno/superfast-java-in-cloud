package pl.edu.pw.ljozwiak.coreprocessing.utils;

public class Preconditions {

  public static <T> T checkNotNull(T reference, String errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }
}

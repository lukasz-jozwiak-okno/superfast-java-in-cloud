package pl.edu.pw.ljozwiak.coreprocessing.utils;

@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
  void run() throws E;

  static void wrap(ThrowingRunnable<Exception> throwingRunnable) {
    try {
      throwingRunnable.run();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }
}

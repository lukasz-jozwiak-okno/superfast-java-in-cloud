package pl.edu.pw.ljozwiak.coreprocessing;

import static pl.edu.pw.ljozwiak.coreprocessing.utils.ThrowingRunnable.wrap;

import java.util.Optional;

public class DelayService {

  public void delay(Optional<Integer> delay) {
    delay.ifPresent(delayInMillis -> wrap(() -> Thread.sleep(delayInMillis)));
  }

  public void delay(Integer delayInMillis) {
    if (delayInMillis != null) {
      wrap(() -> Thread.sleep(delayInMillis));
    }
  }
}

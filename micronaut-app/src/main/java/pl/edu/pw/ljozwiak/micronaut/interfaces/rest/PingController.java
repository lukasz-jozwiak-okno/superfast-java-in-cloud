package pl.edu.pw.ljozwiak.micronaut.interfaces.rest;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@RequiredArgsConstructor
@Controller("/ping")
public class PingController {

  private final DelayService delayService;

  @Get
  @Produces(MediaType.TEXT_PLAIN)
  public String ping(@QueryValue Optional<Integer> delay) {
    delayService.delay(delay);
    return "ping";
  }
}

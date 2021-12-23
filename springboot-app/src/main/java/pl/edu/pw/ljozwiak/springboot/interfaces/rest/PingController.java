package pl.edu.pw.ljozwiak.springboot.interfaces.rest;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ping")
public class PingController {

  private final DelayService delayService;

  @GetMapping(produces = TEXT_PLAIN_VALUE)
  public String ping(@RequestParam(name = "delay", required = false) Integer delay) {
    delayService.delay(delay);

    return "ping";
  }
}

package pl.edu.pw.ljozwiak.springboot.interfaces.rest;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ping")
public class PingController {

  @GetMapping(produces = TEXT_PLAIN_VALUE)
  public String ping() {
    return "ping";
  }
}

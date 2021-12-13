package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/ping")
@RequiredArgsConstructor
public class PingController {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String ping() {
    return "ping";
  }
}

package pl.edu.pw.ljozwiak.quarkus.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import pl.edu.pw.ljozwiak.coreprocessing.DelayService;

@Path("/ping")
@RequiredArgsConstructor
public class PingController {

  private final DelayService delayService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String ping(@QueryParam("delay") Integer delay) {
    delayService.delay(delay);
    return "ping";
  }
}

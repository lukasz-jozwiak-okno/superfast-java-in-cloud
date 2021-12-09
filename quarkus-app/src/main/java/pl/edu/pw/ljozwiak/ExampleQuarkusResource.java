package pl.edu.pw.ljozwiak;

import com.mongodb.client.MongoClient;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/hello")
@RequiredArgsConstructor
public class ExampleQuarkusResource {

  private final MongoClient mongoClient;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return mongoClient.listDatabaseNames().first();
  }
}

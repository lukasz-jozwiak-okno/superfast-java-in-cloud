package pl.edu.pw.ljozwiak;

import com.mongodb.client.MongoClient;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller("/hello")
public class ExampleMicronautResource {

  private final MongoClient mongoClient;

  @Get
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello() {
    return mongoClient.listDatabaseNames().first();
  }
}

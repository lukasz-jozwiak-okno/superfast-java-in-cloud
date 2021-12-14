package pl.edu.pw.ljozwiak.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringbootAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootAppApplication.class, args);
  }
}

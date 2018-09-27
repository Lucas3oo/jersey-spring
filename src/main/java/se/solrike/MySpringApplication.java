package se.solrike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.solrike.resources.CsvResource;

@SpringBootApplication(scanBasePackageClasses = {CsvResource.class, MySpringApplication.class})
public class MySpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(MySpringApplication.class, args);
  }
}

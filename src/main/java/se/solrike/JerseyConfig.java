package se.solrike;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.solrike.resources.CsvResource;

@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    packages(CsvResource.class.getPackage().getName());
  }
}
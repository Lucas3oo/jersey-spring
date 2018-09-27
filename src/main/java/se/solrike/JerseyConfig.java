package se.solrike;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import se.solrike.resources.CsvResource;

@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    packages(CsvResource.class.getPackage().getName());
    
    property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    register(RolesAllowedDynamicFeature.class);

    packages("org.glassfish.jersey.examples.multipart");
    register(MultiPartFeature.class);

    //register(JacksonJaxbJsonProviderFactory.createProvider());
    
    configureSwagger();    
    
  }
  
  
  private BeanConfig configureSwagger() {
    // support Swagger
    register(ApiListingResource.class);
    register(SwaggerSerializers.class);
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setVersion("API " + "1.0");
    beanConfig.setSchemes(new String[] { "https" });
    beanConfig.setHost("localhost:9081");
    beanConfig.setBasePath("/rxp");
    // comma separated string
    beanConfig.setResourcePackage(CsvResource.class.getPackage().getName());
    beanConfig.setPrettyPrint(true);
    beanConfig.setScan(true);
    beanConfig.setTitle("C360 Admin Internal REST API");
    beanConfig.setDescription("The Internal REST API is used from the JavaScript web GUI.");
    return beanConfig;
  }  
  
}
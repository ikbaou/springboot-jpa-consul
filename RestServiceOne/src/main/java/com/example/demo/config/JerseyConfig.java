package com.example.demo.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
public class JerseyConfig extends ResourceConfig {
	
    @Value("${spring.jersey.application-path:/api}")
    private String apiPath;	

    public JerseyConfig() {
    	register(WadlResource.class); //generates WADL service descriptor
        packages("com.example.demo.rest");        
    }
    
    @PostConstruct
    public void init(){
    	 configureSwagger();
    }

    private void configureSwagger() {
        // Available at localhost:port/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
//        config.setConfigId("something");
//        config.setTitle("something");
//        config.setVersion("v1");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.example.demo.rest");
        config.setPrettyPrint(true);
        config.setScan(true);
    }	

}
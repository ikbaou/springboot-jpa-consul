package com.example.demo.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.rest.base.ApiConstants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Configure multiple Rest Applications, Servlets etc.
 * 
 * @author baourdos
 *
 */
@Component
public class ServletRegistration {

	@Bean
	public ServletRegistrationBean baseJersey() {
	    ServletRegistrationBean baseJersey 
	            = new ServletRegistrationBean(new ServletContainer(new BaseRestConfig()));
	    baseJersey.addUrlMappings(ApiConstants.VERSION_BASE_PXF + "/*");
	    baseJersey.setName("BaseJersey");
	    baseJersey.setLoadOnStartup(0);
	    return baseJersey;
	}

	@Bean
	public ServletRegistrationBean flavourJersey() {
	    ServletRegistrationBean flavourJersey 
	           = new ServletRegistrationBean(new ServletContainer(new FlavourRestConfig()));
	    flavourJersey.addUrlMappings(ApiConstants.VERSION_FLAVOUR_PXF + "/*");
	    flavourJersey.setName("FlavourJersey");
	    flavourJersey.setLoadOnStartup(1);
	    return flavourJersey;
	}
	
		
	/*
	 * Rest configuration classes
	 */	
	protected class BaseRestConfig extends ResourceConfig {
	    public BaseRestConfig() {
	    	register(WadlResource.class); //generates WADL service descriptor
	        packages("com.example.demo.rest.base");
	                    
            ServletRegistration.registerSwagger(this, ApiConstants.VERSION_BASE_PXF, "com.example.demo.rest.base");
	    }
	}
	
	protected class FlavourRestConfig extends ResourceConfig {
	    public FlavourRestConfig() {
	    	register(WadlResource.class); //generates WADL service descriptor
	        packages("com.example.demo.rest.flavour"); 
	        
            ServletRegistration.registerSwagger(this, ApiConstants.VERSION_FLAVOUR_PXF, "com.example.demo.rest.flavour");
	    }
	}

	public static void registerSwagger(ResourceConfig resourseConfig, String path, String packages) {
        //generates Swagger json/yaml service descriptor
		resourseConfig.register(ApiListingResource.class);
		resourseConfig.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(path);
        config.setResourcePackage(packages);
        config.setPrettyPrint(true);
        config.setScan(true);
	}
	
	
}

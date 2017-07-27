package com.example.demo.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.demo.rest.base.ApiConstants;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Configure multiple Rest Applications / Servlets etc.
 * 
 * @author baourdos
 *
 */
@Component
public class ServletRegistration {

	@Bean
	public ServletRegistrationBean baseJersey() {
		
	    ServletRegistrationBean baseJersey 
	        = new ServletRegistrationBean(new ServletContainer(
	    	    	new ResourceConfig()
	    	    	.register(WadlResource.class)
	    	    	.register(ApiListingResource.class)
	    	    	.register(SwaggerSerializers.class)
	    	    	.packages("com.example.demo.rest.base")));
	    
	    baseJersey.addUrlMappings(ApiConstants.FULL_BASE_PXF + "/*");
	    baseJersey.setName("BaseJersey");	    
	    //TO BE COMBINED WITH BeanConfig::setUsePathBasedConfig(true);
	    baseJersey.addInitParameter(SwaggerContextService.USE_PATH_BASED_CONFIG, "true");
	    baseJersey.setLoadOnStartup(0);
	    return baseJersey;
	}

	@Bean
	public ServletRegistrationBean flavourJersey() {
	    ServletRegistrationBean flavourJersey 
	        = new ServletRegistrationBean(new ServletContainer(
	    	    	new ResourceConfig()
	    	    	.register(WadlResource.class)
	    	    	.register(ApiListingResource.class)
	    	    	.register(SwaggerSerializers.class)
	    	    	.packages("com.example.demo.rest.flavour")));
	    
	    flavourJersey.addUrlMappings(ApiConstants.FULL_FLAVOUR_PXF + "/*");
	    flavourJersey.setName("FlavourJersey");
	    //TO BE COMBINED WITH BeanConfig::setUsePathBasedConfig(true);
	    flavourJersey.addInitParameter(SwaggerContextService.USE_PATH_BASED_CONFIG, "true");
	    flavourJersey.setLoadOnStartup(1);
	    return flavourJersey;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = "api.documentation", name = "enabled", matchIfMissing = false)
	public BeanConfig getBaseJerseySwaggerConfig() {
        BeanConfig config = new BeanConfig();
        config.setUsePathBasedConfig(true); //VERY IMPORTANT, TO BE MATHCED WITH THE CORRECT JAX-RS SERVLET
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(ApiConstants.FULL_BASE_PXF);
        config.setResourcePackage("com.example.demo.rest.base");
        config.setPrettyPrint(true);
        config.setScan(true);		
        
        return config;
	}
	
	@Bean
	@ConditionalOnProperty(prefix = "api.documentation", name = "enabled", matchIfMissing = false)
	public BeanConfig getFlavourJerseySwaggerConfig() {
        BeanConfig config = new BeanConfig();
        config.setUsePathBasedConfig(true); //VERY IMPORTANT, TO BE MATHCED WITH THE CORRECT JAX-RS SERVLET
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(ApiConstants.FULL_FLAVOUR_PXF);
        config.setResourcePackage("com.example.demo.rest.flavour");
        config.setPrettyPrint(true);
        config.setScan(true);		
        
        return config;
	}	
}

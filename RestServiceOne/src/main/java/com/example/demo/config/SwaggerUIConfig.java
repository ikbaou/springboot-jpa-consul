package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configures Swagger plugin, note: springfox does not yet support Jersey, 
 * because we're using Jersey, the actual Rest Swagger Configuration is at JerseyConfig
 * @author ibaou
 *
 */

@Configuration
@DependsOn(value={})
@Component
@PropertySource("classpath:application.properties")
@EnableSwagger2
@ConditionalOnProperty(prefix = "api.documentation", name = "enabled", matchIfMissing = false)
public class SwaggerUIConfig {
	
    @Bean   
    public Docket baseApiDoc() {             
    	return new Docket(DocumentationType.SWAGGER_2).groupName("base");
    } 
    
    @Bean   
    public Docket flacourApiDoc() {             
    	return new Docket(DocumentationType.SWAGGER_2).groupName("flavour");
    }    
}

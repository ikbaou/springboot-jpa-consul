package com.example.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Component
@PropertySource("classpath:swagger.properties")
@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() {             
        return new Docket(DocumentationType.SWAGGER_2);                                       
    }
}

package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.example.demo.rest.base.ApiConstants;
import com.example.demo.spring.springfox.swagger.web.GroupDocumentationMapper;

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
@Component
@PropertySource("classpath:application.properties")
@EnableSwagger2
@ConditionalOnProperty(prefix = "api.documentation", name = "enabled", matchIfMissing = false)
public class SwaggerUIConfig {
	
    /**
     * setting the group
     * @return
     */
    @Bean   
    public Docket baseApiDoc() {         
    	//FOR CUSTOM SERVLET DEFINITIONS SET ENABLE FALSE (see <code>ServletRegistration</code>)
    	return new Docket(DocumentationType.SWAGGER_2).groupName(ApiConstants.BASE_PXF).enable(false);
    } 
    
    /**
     * setting the group
     * @return
     */
    @Bean   
    public Docket flacourApiDoc() {        
    	//FOR CUSTOM SERVLET DEFINITIONS SET ENABLE FALSE (see <code>ServletRegistration</code>)
    	return new Docket(DocumentationType.SWAGGER_2).groupName(ApiConstants.FLAVOUR_PXF).enable(false);
    }    
    
    /**
     * mapping the groups to the generated JSON files (see <code>ServletRegistration</code>)
     * @return
     */
    @Bean
    public GroupDocumentationMapper getDocumentationMapper(){
    	return new GroupDocumentationMapper()
    			.addToMap(ApiConstants.BASE_PXF, ApiConstants.FULL_BASE_PXF + "/swagger.json")
    			.addToMap(ApiConstants.FLAVOUR_PXF, ApiConstants.FULL_FLAVOUR_PXF + "/swagger.json");
    }
}

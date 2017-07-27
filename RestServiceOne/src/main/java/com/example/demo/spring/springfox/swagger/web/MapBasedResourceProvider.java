package com.example.demo.spring.springfox.swagger.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Primary
@Component
public class MapBasedResourceProvider implements SwaggerResourcesProvider {

	private final GroupDocumentationMapper mapper;
	private final String swaggerVersion;

	@Autowired
	public MapBasedResourceProvider(Environment environment, GroupDocumentationMapper mapper) {
		this.mapper = mapper;
		swaggerVersion = environment.getProperty("springfox.documentation.swagger.version", "2.0");
	}

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();

		for (Map.Entry<String, String> entry : mapper.getGroupDocMap().entrySet()) {
			String swaggerGroup = entry.getKey();
			String swaggerDocUrl = entry.getValue();
			
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setSwaggerVersion(swaggerVersion);
			swaggerResource.setName(swaggerGroup);
			swaggerResource.setLocation(swaggerDocUrl);			
						
			resources.add(swaggerResource);			
		}
		Collections.sort(resources);
		return resources;
	}
}

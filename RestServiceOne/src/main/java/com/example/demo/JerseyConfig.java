package com.example.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import com.example.demo.rest.TestEndpoint;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(WadlResource.class);
        register(TestEndpoint.class);
    }

}
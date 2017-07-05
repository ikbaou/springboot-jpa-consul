package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/two")
public class TestEndpoint {
	
	
	
    @GET
    @Path("/test")
    public String test() {
        return "test";
    }
    
    @GET
    @Path("/health")    
	public String health() {
		return "OK";
	}    

}
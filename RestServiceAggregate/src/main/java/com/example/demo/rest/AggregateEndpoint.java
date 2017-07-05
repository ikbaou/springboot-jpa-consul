package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/aggregate")
public class AggregateEndpoint {
	
    @GET
    @Path("/join")
    public String test() {
        return "join";
    }
    
    @GET
    @Path("/health")    
	public String health() {
		return "OK";
	}    

}
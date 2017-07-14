package com.example.demo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.EventMessage;
import com.example.demo.jms.Sender;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("health")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Health endpoints", produces = "application/json")
public class HealthEndpoint {

	@Autowired
	Sender sender;
	
	@GET
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Service is up and running")})
	public String health() {
		sender.send("DurableTestTopic", true, new EventMessage("Everybody Respond!"));
		sender.send("VirtualTopic.TestTopic", true, new EventMessage("Only One Respond!"));
		
		
		
		return "{\"status\": \"OK\"}"; 
	}

}
package com.example.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/health")
public class HealthEndpoint {

	@GET
	public String health() {
		return "OK";
	}    

}
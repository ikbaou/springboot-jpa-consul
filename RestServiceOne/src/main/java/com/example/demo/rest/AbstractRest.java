package com.example.demo.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

public class AbstractRest {
	
	@Context protected UriInfo uriInfo;
	
	@Context protected HttpServletRequest request;
	
	private ResponseBuilder responseBuilder;


}
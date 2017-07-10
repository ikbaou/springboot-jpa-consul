package com.example.demo.rest.response.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        // your uncaught exception handling logic here...
    	
    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
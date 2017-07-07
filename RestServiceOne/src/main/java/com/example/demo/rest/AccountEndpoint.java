package com.example.demo.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.demo.domain.Account;
import com.example.demo.domain.Customer;
import com.example.demo.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Account endpoints", produces = "application/json")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("accounts")
public class AccountEndpoint {
	
	@Inject
	AccountService accountService;
	
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get an Account", nickname="getAccount")    
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Account found", response = Account.class),
			@ApiResponse(code = 404, message = "Account not found") })    
	public Account getAccount(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {
    	
    	Account account = accountService.getAccount(id);
        if(account == null) {
        	Response.status(Response.Status.NOT_FOUND);
        }else{
        	Response.status(Response.Status.OK);
        }
    	return account;
    }	
	
    @GET
    @Path("/{id}/customer")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get a Customer for an Account")    
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Customer found", response=Customer.class),
			@ApiResponse(code = 404, message = "Customer not found") })	    
	public Customer getCustomer(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {
    	Account account = accountService.getAccount(id);
    	Customer customer =  null;
        if(account == null || account.getCustomer()==null) {
        	Response.status(Response.Status.NOT_FOUND);
        }else{
        	Response.status(Response.Status.OK);
        	customer = account.getCustomer();
        }
    	return customer;
    }
 

}
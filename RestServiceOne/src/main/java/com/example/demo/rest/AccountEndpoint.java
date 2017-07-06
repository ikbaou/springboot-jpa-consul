package com.example.demo.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
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

@Path("/accounts")
public class AccountEndpoint {
	
	@Inject
	AccountService accountService;
	
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Account getAccount(@PathParam("id") Long id, @Context final HttpServletResponse response) {
    	
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
    public Customer getPseudoCustomer(@PathParam("id") Long id, @Context final HttpServletResponse response) {
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
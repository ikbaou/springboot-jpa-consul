package com.example.demo.rest.base;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.demo.domain.Account;
import com.example.demo.domain.PublicAccount;
import com.example.demo.domain.validation.groups.CreateAccountGroup;
import com.example.demo.domain.validation.groups.ModifyAccountGroup;
import com.example.demo.domain.views.Views;
import com.example.demo.service.AccountService;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Named
@Api(value = "Account endpoints", produces = "application/json")
@Produces(MediaType.APPLICATION_JSON)
@Path("accounts/")
public class AccountEndpoint {
	
	protected AccountService accountService;
	
	@Inject
	public AccountEndpoint(AccountService accountService){
		this.accountService = accountService;
	}	
	
	/**
	 * Search for an account by ID and set the response accordingly
	 * 
	 * @param id
	 * @param response
	 * @return
	 */
	protected Account getAccount(Long id, final HttpServletResponse response) {
    	Account account = accountService.getAccount(id);
        if(account == null) {
        	Response.status(Response.Status.NOT_FOUND);
        }else{
        	Response.status(Response.Status.OK);
        }
    	return account;		
	}
	
    /**
     * Get a Public Account based on ID
     * 
     * @param id
     * @param response
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get an Account Public view", nickname="getAccount")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Account found", response = PublicAccount.class),
			@ApiResponse(code = 404, message = "Account not found") })    
	public PublicAccount getAccountPublic(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {
    	
    	final Account account = this.getAccount(id,response);
    	return account!=null ? new PublicAccount(account) : null;
    }
    
    /**
     * Get a full Account based on ID     
     * 
     * @param id
     * @param response
     * @return
     */
    @GET
    @Path("/{id}/sensitive")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get an Account with Sensitive", nickname="getAccountSensitive")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Account found", response = Account.class),
			@ApiResponse(code = 404, message = "Account not found") })    
	public Account getAccountSensitive(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {

    	return this.getAccount(id,response);
    }    
            
    /**
     * Using Jackson views to get an Account in public view
     * 
     * @param id
     * @param response
     * @return
     */
    @GET
    @JsonView(Views.Public.class)
    @Path("/{id}/jackson")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get an Account rendered with view: Views.Public", nickname="getAccountJacksonPublic")    
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Account found", response = Account.class),
			@ApiResponse(code = 404, message = "Account not found") })        
	public Account getAccountJacksonPublic(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {

    	return this.getAccount(id,response);
    }    
    
    /**
     * Using Jackson views to get an Account in sensitive view
     * 
     * @param id
     * @param response
     * @return
     */
    @GET
    @JsonView(Views.Sensitive.class)
    @Path("/{id}/jackson/sensitive")
    @Produces({ MediaType.APPLICATION_JSON })
    @ApiOperation(value = "Resource to get an Account rendered with view: Views.Sensitive", nickname="getAccountJacksonSensitive")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Public Account found", response = Account.class),
			@ApiResponse(code = 404, message = "Public Account not found") })      
	public Account getAccountJacksonSensitive(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {

    	return this.getAccount(id,response);
    }   
    	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Resource to create an Account")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Account created", response = Account.class),
			@ApiResponse(code = 404, message = "Account not created") })
	public Response postAccount(
			@Valid @ConvertGroup(
					from = Default.class,
					to = CreateAccountGroup.class) Account account,
			@Context final HttpServletResponse response) {

		account = accountService.createAccount(account);
		
		return Response.status(Response.Status.CREATED).entity(account).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Resource to update an Account")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Account modified", response = Account.class),
			@ApiResponse(code = 404, message = "Account not created") })
	public Response putAccount(
			@Valid @ConvertGroup(
					from = Default.class, 
					to = ModifyAccountGroup.class) Account account,
			@Context final HttpServletResponse response) {

		account = accountService.modifyAccount(account);
		
		return Response.status(Response.Status.CREATED).entity(account).build();
	}	    
	

 

}
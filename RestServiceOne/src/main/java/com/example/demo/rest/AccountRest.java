package com.example.demo.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountResponseBody;
import com.example.demo.domain.Customer;
import com.example.demo.domain.Message;
import com.example.demo.domain.validation.groups.CreateAccountGroup;
import com.example.demo.domain.validation.groups.ModifyAccountGroup;
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
public class AccountRest {
	
	/*
	 * TODO:
	 * 
	 * a) (?) wrap RequestObject ( attributes, entities )
	 * b) -- wrap ResponseObject ( messages, entity )
	 * c) -- wrap ResponseMessages ( ResponseMessage (key, params), info, error, success, warn )
	 * d) Validation API (on entities, on RequestObject)
	 * e) RestSecurity
	 * f) (?) wrap rest (AbstractRest, ResponseBuilder)
	 * g) CRUD (Wrapped-Rest ? )?
	 * g) Pagination ( Wrapped Rest ?, Page object ?)
	 * 
	 */

	@Inject	AccountService accountService;
	
	@Context UriInfo uriInfo;
	
	@Context HttpServletRequest request;

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Resource to get an Account", nickname = "getAccount")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Account found", response = AccountResponseBody.class),
			@ApiResponse(code = 404, message = "Account not found") })
	public Response getAccount(@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {

		ResponseBuilder rb = Response.ok();
		AccountResponseBody rbd = new AccountResponseBody();
		
		Account account = accountService.getAccount(id);
		if (account == null) {
			rb.status(Response.Status.NOT_FOUND);
			rbd.addError(new Message("account.not.found", id));
		}else{
			rb.status(Response.Status.FOUND);
			rbd.addSuccess(new Message("account.found")).entity(account);
		}
				
		return rb.entity(rbd).build();
	}
	
	@POST
	@Path("/")
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
	@Path("/")
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
	
	@GET
	@Path("/{id}/customer")
	@ApiOperation(value = "Resource to get a Customer for an Account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Customer found", response = Customer.class),
			@ApiResponse(code = 404, message = "Customer not found") })
	public Response getCustomer(
			@ApiParam(value = "the account id", required = true) @PathParam("id") Long id,
			@Context final HttpServletResponse response) {
		
		Response.Status status = Response.Status.OK;
		Account account = accountService.getAccount(id);
		Customer customer = null;
		if (account == null || account.getCustomer() == null) {
			status=Response.Status.NOT_FOUND;
		}else{
			customer = account.getCustomer();
		}
		return Response.status(status).entity(customer).build();
	}



}
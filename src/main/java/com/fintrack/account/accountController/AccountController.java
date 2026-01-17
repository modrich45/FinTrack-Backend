package com.fintrack.account.accountController;

import java.util.List;

import javax.print.attribute.standard.Media;

import com.fintrack.account.accountService.AccountService;
import com.fintrack.account.dto.AccountResponse;
import com.fintrack.account.dto.CreateAccountRequest;

import io.quarkus.runtime.Application;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.*;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;


@Path("/accounts")
public class AccountController {

    @Inject
    private AccountService accountService;

    @POST
    @Path("/createAccount")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createAccount(CreateAccountRequest request) {
        // Implementation for creating an account
        AccountResponse response = accountService.createAccount(request);
        return Response.status(Response.Status.CREATED)
               .entity(response)
               .build();
    }


    @GET
    @Path("/accounts/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAccountById(@PathParam("id") Long id) {
        // Implementation for retrieving an account by ID
        AccountResponse response = accountService.getAccountById(id);
        return Response.status(Response.Status.OK)
               .entity(response)
               .build();
    }

    @GET
    @Path("/user/{userId}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getAccountsByUserId(@PathParam("userId") Long userId) {
        // Implementation for retrieving accounts by user ID
        List<AccountResponse> responses = accountService.getAccountsByUserId(userId);
        return Response.status(Response.Status.OK)
               .entity(responses)
               .build();

    }


}

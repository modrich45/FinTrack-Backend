package com.fintrack.user.controller;

import com.fintrack.user.dto.CreateUserRequest;
import com.fintrack.user.dto.UserResponse;
import com.fintrack.user.entity.User;
import com.fintrack.user.service.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;
    
    @POST
    @Path("/saveUser")
    public Response saveUser(@Valid CreateUserRequest user) {
        UserResponse userResponse = userService.saveUser(user);
        return Response.status(Response.Status.CREATED)
               .entity(userResponse)
               .build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return Response.status(Response.Status.CREATED).entity(userResponse).build();
    }


}

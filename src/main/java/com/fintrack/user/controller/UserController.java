package com.fintrack.user.controller;

import com.fintrack.user.dto.CreateUserRequest;
import com.fintrack.user.dto.UserResponse;
import com.fintrack.user.entity.User;
import com.fintrack.user.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
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
    public Response saveUser(CreateUserRequest user) {
        UserResponse userResponse = userService.saveUser(user);
        return Response.ok(
            userResponse
        ).build();
    }
}

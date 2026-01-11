package com.fintrack.user.service;

import com.fintrack.exception.ResourceNotFound;
import com.fintrack.user.dto.CreateUserRequest;
import com.fintrack.user.dto.UserResponse;
import com.fintrack.user.entity.User;
import com.fintrack.user.repo.UserRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    
    @Inject
    private UserRepo userRepo;
    
    @Transactional
    public UserResponse saveUser(CreateUserRequest user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        userRepo.persist(newUser);

        UserResponse response= new UserResponse();
        response.setId(newUser.getId());
        response.setName(newUser.getName());
        response.setEmail(newUser.getEmail());
        return response;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id);
        if (user == null) {
            throw new ResourceNotFound("User not found with id: " + id); // Or throw an exception based on your error handling strategy
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }
}

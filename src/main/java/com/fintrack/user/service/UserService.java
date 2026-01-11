package com.fintrack.user.service;

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
}

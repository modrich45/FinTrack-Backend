package com.fintrack.user.service;

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
    public User saveUser(User user) {
        userRepo.persist(user);
        return user;
    }
}

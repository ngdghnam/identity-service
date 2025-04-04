package com.devteria.identity_service.services;

import com.devteria.identity_service.dto.requests.UserCreationRequest;
import com.devteria.identity_service.dto.requests.UserUpdateRequest;
import com.devteria.identity_service.entities.User;
import com.devteria.identity_service.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if(userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUserById(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepo.save(user);
    }

    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}

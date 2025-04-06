package com.devteria.identity_service.controllers;

import com.devteria.identity_service.dto.requests.ApiRespone;
import com.devteria.identity_service.dto.requests.UserCreationRequest;
import com.devteria.identity_service.dto.requests.UserUpdateRequest;
import com.devteria.identity_service.entities.User;
import com.devteria.identity_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiRespone<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiRespone<User> response = new ApiRespone<>();
        response.setResult(userService.createUser(request));

        return response;
    }

    @GetMapping
    List<User> getAllUsers() {  
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}

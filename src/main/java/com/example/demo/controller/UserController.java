package com.example.demo.controller;

import com.example.demo.response.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @CrossOrigin(origins = "*")
    @PostMapping
    public ApiResponse<UUID> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get all users
    @CrossOrigin(origins = "*")
    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<Optional<User>> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // Update user by ID
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<UUID> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // Delete all users
    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete-all")
    public ApiResponse<String> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    // Delete user by ID
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ApiResponse<UUID> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}

package com.example.demo.service;

import com.example.demo.response.Code;
import com.example.demo.response.Status;
import com.example.demo.response.ApiResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public ApiResponse<UUID> createUser(User user) {
        user.setId(generateId());
        userRepository.save(user);
        return new ApiResponse<>(Code.OK, Status.OK, user.getId());
    }

    public UUID generateId() {
        return UUID.randomUUID();
    }

    // Get all users
    public ApiResponse<List<User>> getAllUsers() {
        return new ApiResponse<>(Code.OK, Status.OK,userRepository.findAll());
    }

    // Get user by ID
    public ApiResponse<Optional<User>> getUserById(UUID id) {
        return new ApiResponse<>(Code.OK, Status.OK, userRepository.findById(id));
    }

    // Update user
    public ApiResponse<UUID> updateUser(UUID id, User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            userRepository.save(existingUser);
            return new ApiResponse<>(Code.OK, Status.OK, id);
        }
        return new ApiResponse<>(Code.ID_NOT_FOUND, Status.FAIL, id);
    }

    // Delete all users
    public ApiResponse<String> deleteAllUsers() {
        userRepository.deleteAll();
        return new ApiResponse<>(Code.OK, Status.OK, "Deleted all users");
    }

    // Delete user
    public ApiResponse<UUID> deleteUser(UUID id) {
        userRepository.deleteById(id);
        return new ApiResponse<>(Code.OK, Status.OK, id);
    }

    // Other business logic related to users
}

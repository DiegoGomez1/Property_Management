package com.example.Property.Management.service;

import com.example.Property.Management.dto.CreateUserRequest;
import com.example.Property.Management.entity.User;
import com.example.Property.Management.enums.UserRole;
import com.example.Property.Management.enums.UserStatus;
import com.example.Property.Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // For now, we'll store passwords as plain text (NOT recommended for production)
    // private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(CreateUserRequest request) {
        // Validate request
        if (!request.isValid()) {
            throw new IllegalArgumentException("Invalid user data");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword()); // Store plain text for now (NOT secure)
        user.setRole(request.getRole());
        user.setStatus(UserStatus.ACTIVE);

        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(userDetails.getFirstName());
                    user.setLastName(userDetails.getLastName());
                    user.setEmail(userDetails.getEmail());
                    user.setPhone(userDetails.getPhone());
                    user.setRole(userDetails.getRole());
                    user.setStatus(userDetails.getStatus());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> getUsersByRole(String roleString) {
        try {
            UserRole role = UserRole.valueOf(roleString.toUpperCase());
            return userRepository.findByRole(role);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + roleString);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean validatePassword(String rawPassword, String storedPassword) {
        // Simple string comparison for now (NOT secure)
        return rawPassword.equals(storedPassword);
    }
}
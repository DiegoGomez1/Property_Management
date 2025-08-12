package com.example.Property.Management.dto;

import com.example.Property.Management.enums.UserRole;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private UserRole role = UserRole.OWNER; // Default role
    
    // Validation methods
    public boolean isValid() {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               password != null && password.length() >= 6;
    }
}
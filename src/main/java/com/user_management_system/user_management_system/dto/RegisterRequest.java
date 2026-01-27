package com.user_management_system.user_management_system.dto;

import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "User name is required!")
    private String username;

    private String address;

    @Email(message = "Invalid Email")
    @NotBlank
    private String email;

    @Size(min = 6, message = "Password must be at least 6 character")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

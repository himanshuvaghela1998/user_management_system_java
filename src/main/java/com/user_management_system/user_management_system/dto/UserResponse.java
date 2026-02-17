package com.user_management_system.user_management_system.dto;

import com.user_management_system.user_management_system.entity.Role;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String address;
    private String role;
    private String token;

    public UserResponse(Long id, String username, String email, String address, String role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.role = role;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String Role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

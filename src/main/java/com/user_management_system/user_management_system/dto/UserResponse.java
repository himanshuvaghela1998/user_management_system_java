package com.user_management_system.user_management_system.dto;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String address;

    public UserResponse(Long id, String username, String email, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

}

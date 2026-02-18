package com.user_management_system.user_management_system.controller;

import com.user_management_system.user_management_system.dto.LoginRequest;
import com.user_management_system.user_management_system.dto.RegisterRequest;
import com.user_management_system.user_management_system.dto.UserResponse;
import com.user_management_system.user_management_system.service.UserService;
import com.user_management_system.user_management_system.entity.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    //Register
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }


}

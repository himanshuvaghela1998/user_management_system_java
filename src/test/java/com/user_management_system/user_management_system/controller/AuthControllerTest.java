package com.user_management_system.user_management_system.controller;

import com.user_management_system.user_management_system.dto.RegisterRequest;
import com.user_management_system.user_management_system.dto.UserResponse;
import com.user_management_system.user_management_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void register_ShouldReturnSuccess() {
        RegisterRequest req = new RegisterRequest();
        req.setUsername("john");
        req.setEmail("john@gmail.com");
        req.setPassword("password");

        UserResponse mockResp = new UserResponse(1L, "john", "john@gmail.com", "", "USER", "token-123");

        when(userService.register(any(RegisterRequest.class))).thenReturn(mockResp);

        UserResponse resp = authController.register(req);

        assertNotNull(resp);
        assertEquals("john@gmail.com", resp.getEmail());
        assertEquals("token-123", resp.getToken());
    }
}

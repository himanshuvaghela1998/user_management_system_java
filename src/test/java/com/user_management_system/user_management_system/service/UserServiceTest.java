package com.user_management_system.user_management_system.service;

import com.user_management_system.user_management_system.dto.RegisterRequest;
import com.user_management_system.user_management_system.dto.UserResponse;
import com.user_management_system.user_management_system.entity.User;
import com.user_management_system.user_management_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserService userService;

    @Test
    void register_ShouldRegisterUserSuccessfully() {

        RegisterRequest request = new RegisterRequest();
        request.setUsername("John Doe");
        request.setAddress("Street 123");
        request.setEmail("john@test.com");
        request.setPassword("password123");

        when(repository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(request.getPassword()))
                .thenReturn("encodedPassword");

        when(jwtService.generateToken(request.getEmail())).thenReturn("token-123");

        UserResponse response = userService.register(request);

        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals("token-123", response.getToken());

        verify(repository).save(any(User.class));
    }
}

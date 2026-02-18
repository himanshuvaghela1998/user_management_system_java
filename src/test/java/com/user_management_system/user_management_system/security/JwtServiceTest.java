package com.user_management_system.user_management_system.security;

import com.user_management_system.user_management_system.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JwtServiceTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void generateToken_ShouldGenerateValidToken() {
        String email = "test@gmail.com";

        String token = jwtService.generateToken(email);

        assertNotNull(token);
        // token should not be expired immediately
        assertFalse(jwtService.isTokenExpired(token));
    }

}

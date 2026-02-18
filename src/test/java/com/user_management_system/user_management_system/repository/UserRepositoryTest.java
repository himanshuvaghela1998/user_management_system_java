package com.user_management_system.user_management_system.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import com.user_management_system.user_management_system.entity.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_ShouldReturnUser() {
        User user = new User();
        user.setUserName("John Doe");
        user.setEmail("Test@gmail.com");
        user.setAddress("123 Main Street");
        user.setPassword("password123");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("test@gmail.com");

        assertTrue(foundUser.isPresent());
    }

}

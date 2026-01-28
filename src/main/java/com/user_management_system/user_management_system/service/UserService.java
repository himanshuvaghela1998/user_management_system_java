package com.user_management_system.user_management_system.service;

import com.user_management_system.user_management_system.dto.LoginRequest;
import com.user_management_system.user_management_system.dto.RegisterRequest;
import com.user_management_system.user_management_system.dto.UserResponse;
import com.user_management_system.user_management_system.entity.User;
import com.user_management_system.user_management_system.exception.EmailAlreadyExistsException;
import com.user_management_system.user_management_system.exception.ResourceNotFoundException;
import com.user_management_system.user_management_system.exception.BadRequestException;
import com.user_management_system.user_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //Register
    public UserResponse register(RegisterRequest request) {

//        if(validateEmail(request)) {
//            throw new RuntimeException("Email Can not be null");
//        }
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("User with email '" + request.getEmail() + "' already exists");
        }

        User user = new User();
        user.setUserName(request.getUsername());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());

        repository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getAddress()
        );
    }

//    private boolean validateEmail(RegisterRequest request) {
//        return !nonNull(request.getEmail());
//    }

    public String login(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        return "Login successfully";
    }

    //Create or Update
    public User saveUser(User user) {
        return repository.save(user);
    }

    //List All User
    public List<User> getAllUser() {
        return repository.findAll();
    }

    //Get User By ID
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Founder!!"));
    }

    //Delete user
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
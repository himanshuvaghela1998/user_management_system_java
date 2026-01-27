package com.user_management_system.user_management_system.controller;

import com.user_management_system.user_management_system.dto.LoginRequest;
import com.user_management_system.user_management_system.dto.RegisterRequest;
import com.user_management_system.user_management_system.dto.UserResponse;
import com.user_management_system.user_management_system.entity.User;
import com.user_management_system.user_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    //Create
    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    //List
    @GetMapping
    public List<User> getAll() {
        return service.getAllUser();
    }

    //Read By id
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    //Update User
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return service.saveUser(user);
    }

    //Ddelete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "User deleted successfully";
    }

}

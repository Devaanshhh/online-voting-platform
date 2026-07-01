package com.voting.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.User;
import com.voting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {

        User savedUser = service.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }
}
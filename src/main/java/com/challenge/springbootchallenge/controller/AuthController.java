package com.challenge.springbootchallenge.controller;

import com.challenge.springbootchallenge.model.AuthResponse;
import com.challenge.springbootchallenge.model.User;
import com.challenge.springbootchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user) {
        System.out.println(user);
        AuthResponse response = userService.createUser(user);
        System.out.println(response);
        return response;
    }
}

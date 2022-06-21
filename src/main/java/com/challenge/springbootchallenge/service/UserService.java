package com.challenge.springbootchallenge.service;

import com.challenge.springbootchallenge.model.AuthResponse;
import com.challenge.springbootchallenge.model.User;
import com.challenge.springbootchallenge.repository.UserRepo;
import com.challenge.springbootchallenge.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthResponse createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepo.save(user);
        String token = jwtUtil.generateToken(created.getTelephone());
        AuthResponse response = new AuthResponse(created, token);
        return response;
    }
}

package com.challenge.springbootchallenge.service;

import com.challenge.springbootchallenge.model.AuthResponse;
import com.challenge.springbootchallenge.model.User;
import com.challenge.springbootchallenge.repository.UserRepo;
import com.challenge.springbootchallenge.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager manager;
    public AuthResponse createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepo.save(user);
        String token = jwtUtil.generateToken(created.getTelephone());
        AuthResponse response = new AuthResponse(created, token);
        return response;
    }

    public AuthResponse loginUser(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getTelephone(), user.getPassword());
        manager.authenticate(usernamePasswordAuthenticationToken);
        //here login is successful no error was thrown
        Optional<User> loggedInUser = userRepo.findByTelephone(user.getTelephone());
        String token = jwtUtil.generateToken(loggedInUser.get().getTelephone());
        AuthResponse response = new AuthResponse(loggedInUser.get(), token);
        return response;
    }
}

package com.challenge.springbootchallenge.service;

import com.challenge.springbootchallenge.model.AuthResponse;
import com.challenge.springbootchallenge.model.PasswordReset;
import com.challenge.springbootchallenge.model.User;
import com.challenge.springbootchallenge.model.VerifyOtp;
import com.challenge.springbootchallenge.repository.ResetRepo;
import com.challenge.springbootchallenge.repository.UserRepo;
import com.challenge.springbootchallenge.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.Optional;
import java.util.Random;

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

    @Autowired
    ResetRepo resetRepo;

    @Autowired
    NotificationServiceImpl notificationService;
    public AuthResponse createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepo.save(user);
        String token = jwtUtil.generateToken(created.getEmail());
        AuthResponse response = new AuthResponse(created, token);
        return response;
    }

    public AuthResponse loginUser(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        manager.authenticate(usernamePasswordAuthenticationToken);
        //here login is successful no error was thrown
        Optional<User> loggedInUser = userRepo.findByEmail(user.getEmail());
        String token = jwtUtil.generateToken(loggedInUser.get().getEmail());
        AuthResponse response = new AuthResponse(loggedInUser.get(), token);
        return response;
    }

    public String generateOtp(String email) throws Exception {
        //check if user already exists
        Optional<User> user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }else{
            int otp = new Random().nextInt(900000) + 100000;
            System.out.println(otp);
            System.out.println(user);
            try{
                notificationService.sendNotification(user.get(), "OTP FOR PASSWORD RESET", "This is your password reset otp "+otp);
                PasswordReset passwordReset = new PasswordReset(user.get(), otp);
                resetRepo.save(passwordReset);
                return "Please check your email for otp";
            }catch (Exception e) {
                throw new SendFailedException("Unable to send otp");
            }
        }
    }
    public PasswordReset verifyOtp(VerifyOtp verifyOtp) {
        return resetRepo.findByUserAndOtp(new User(verifyOtp.getEmail()), verifyOtp.getOtp());
    }
}

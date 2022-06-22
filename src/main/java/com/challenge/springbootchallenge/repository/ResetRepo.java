package com.challenge.springbootchallenge.repository;

import com.challenge.springbootchallenge.model.PasswordReset;
import com.challenge.springbootchallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ResetRepo extends JpaRepository<PasswordReset, Long> {
    public PasswordReset findByUserAndOtp(User user, int otp);
}

package com.challenge.springbootchallenge.repository;

import com.challenge.springbootchallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findByTelephone(String telephone);
}

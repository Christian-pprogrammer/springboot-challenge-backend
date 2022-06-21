package com.challenge.springbootchallenge.security;

import com.challenge.springbootchallenge.model.User;
import com.challenge.springbootchallenge.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

// A UserDetailsService is used to fetch the user details of the user trying to authenticate
// into the application. This is done in the loadUserByUsername method.
// If no such user is found a UsernameNotFoundException is thrown

@Component // Marks this as a component. Now, Spring Boot will handle the creation and management of the MyUserDetailsService Bean
// and you will be able to inject it in other places of your code
public class MyUserDetailsService implements UserDetailsService {

    // Injecting Dependencies
    @Autowired private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        // Fetch User from the DB
        Optional<User> userRes = userRepo.findByTelephone(phone);
        // No user found
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with phone = " + phone);
        // Return a User Details object using the fetched User information
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                user.getTelephone(),
                user.getPassword(),
                Collections.EMPTY_LIST
        ); // Sets the role of the found user
    }
}

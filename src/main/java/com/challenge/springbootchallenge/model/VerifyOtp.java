package com.challenge.springbootchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyOtp {
    private String email;
    private int otp;
}

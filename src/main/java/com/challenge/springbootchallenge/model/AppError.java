package com.challenge.springbootchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AppError {
    private String message;
    private HttpStatus status;
    public AppError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

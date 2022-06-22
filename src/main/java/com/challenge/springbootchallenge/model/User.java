package com.challenge.springbootchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "names", length = 255, nullable = false)
    private String names;

    @Column(name = "email", length = 500, nullable = false)
    private String email;
    @Column(name = "password", length = 500, nullable = false)
    private String password;



    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String names, String email, String password) {
        this.names = names;
        this.email = email;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }
}

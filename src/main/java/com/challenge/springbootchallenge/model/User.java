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
    @Column(name = "telephone", length = 10, nullable = false)
    private String telephone;
    @Column(name = "password", length = 500, nullable = false)
    private String password;

    public User(String telephone, String password) {
        this.telephone = telephone;
        this.password = password;
    }

    public User(String names, String telephone, String password) {
        this.names = names;
        this.telephone = telephone;
        this.password = password;
    }
}

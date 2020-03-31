package com.example.demo;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    public String id;

    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

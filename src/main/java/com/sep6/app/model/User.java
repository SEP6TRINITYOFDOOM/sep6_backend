package com.sep6.app.model;

import jakarta.persistence.*;

@Entity(name = "user")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private String username;
    private String password;

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}


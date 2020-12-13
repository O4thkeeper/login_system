package com.example.login_system.entity;

import lombok.Data;

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String phone;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String phone) {
        this.phone = phone;
    }

}

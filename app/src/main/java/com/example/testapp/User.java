package com.example.testapp;

public class User {
    private String name;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    };

    public User() {

    };

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

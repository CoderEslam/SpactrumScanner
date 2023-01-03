package com.doubleclick.spactrumscanner.model;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class Login {

    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.doubleclick.spactrumscanner.model;

import androidx.annotation.NonNull;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class UserData {

    private User user;
    private String token;

    @NonNull
    @Override
    public String toString() {
        return "UserData{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

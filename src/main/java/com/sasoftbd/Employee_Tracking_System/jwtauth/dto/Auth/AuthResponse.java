package com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth;

import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Users;

public class AuthResponse {
    private String token;
    private String refreshToken;
    private Users user;

    // constructor, getters, setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
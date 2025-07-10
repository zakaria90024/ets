package com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth;


public record AuthResponseToken(
        String token,
        String refreshToken
) {}
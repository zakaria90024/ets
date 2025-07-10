package com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth;


import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.AccessModule;

import java.util.List;

public record AuthRequest(
        String username,
        String password,
        String email,
        String number,
        String role,  // Will be validated against enum
        String image,
        String cardNo,
        List<AccessModule> modules // ✅ Add this

) {}



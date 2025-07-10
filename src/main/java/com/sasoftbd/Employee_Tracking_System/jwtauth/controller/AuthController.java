package com.sasoftbd.Employee_Tracking_System.jwtauth.controller;// Sample Java class


import com.sasoftbd.Employee_Tracking_System.jwtauth.config.JwtUtil;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthRequestToken;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthResponse;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthResponseToken;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.AccessModule;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.RefreshToken;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Users;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.RefreshTokenRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.UserRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AuthRequest req) {
        return userService.register(req);
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        Optional<RefreshToken> rt = refreshTokenRepository.findByToken(refreshToken);
        if (rt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid refresh token"));
        }

        // Validate expiration
        if (rt.get().getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(rt.get());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Refresh token expired"));
        }

        JwtUtil jwtUtil = new JwtUtil();

        String username = jwtUtil.extractUsername(refreshToken); // You need to implement this

        String newAccessToken = jwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", newAccessToken));
    }


//    only using user and password login
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseToken> login(@RequestBody AuthRequest req) {
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
//        final UserDetails userDetails = userService.loadUserByUsername(req.username());
//
//
//        final String token = jwtUtil.generateToken(userDetails.getUsername());
//        final String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
//
//        //String accessToken = jwtUtil.generateAccessToken(req.getUsername());
//        //String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());
//
//        // Optionally save refreshToken to DB
//        RefreshToken rt = new RefreshToken();
//        rt.setToken(refreshToken);
//        rt.setUsername(req.username());
//        rt.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
//        refreshTokenRepository.save(rt);
//
//        return ResponseEntity.ok(new AuthResponseToken(token, refreshToken));
//
////        return ResponseEntity.ok(
////                Map.of(
////                        "token", token,
////                        "refreshToken", refreshToken
////                )
////        );
//
//    }


    //username/email, password both working ============================================================================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        // Find user by username or email
        Optional<Users> optionalUser = userRepository.findByUsername(req.username());
        if (optionalUser.isEmpty()) {
            optionalUser = userRepository.findByEmail(req.username()); // fallback to email
        }

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or email"));
        }

        Users user = optionalUser.get();

        // Check password
        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid password"));
        }

        // Authenticate using Spring Security (optional if you already validated above)
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), req.password())
        );

        final String token = jwtUtil.generateToken(user.getUsername());
        final String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        // Save refresh token
        RefreshToken rt = new RefreshToken();
        rt.setToken(refreshToken);
        rt.setUsername(user.getUsername());
        rt.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshTokenRepository.save(rt);

        return ResponseEntity.ok(new AuthResponseToken(token, refreshToken));
    }


    @PostMapping("/login-details")
    public ResponseEntity<?> login(@RequestBody AuthRequestToken req) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.username(), req.password())
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        final UserDetails userDetails = userService.loadUserByUsername(req.username());
        final Users user = userRepository.findByUsername(req.username()).orElseThrow();

        final String token = jwtUtil.generateToken(userDetails.getUsername());
        final String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

        RefreshToken rt = new RefreshToken();
        rt.setToken(refreshToken);
        rt.setUsername(req.username());
        rt.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        refreshTokenRepository.save(rt);

        List<AccessModule> moduleDTOs = user.getModules().stream()
                .map(m -> {
                    AccessModule dto = new AccessModule();
                    dto.setModuleName(m.getModuleName());
                    dto.setCanAccess(m.isCanAccess());
                    return dto;
                })
                .collect(Collectors.toList());

        Users userDTO = new Users();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.isStatus());
        userDTO.setImage(user.getImage());
        userDTO.setNumber(user.getNumber());
        userDTO.setCardNo(user.getCardNo());
        userDTO.setModules(moduleDTOs);
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        response.setUser(userDTO);

        return ResponseEntity.ok(response);
    }


}



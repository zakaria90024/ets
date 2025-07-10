package com.sasoftbd.Employee_Tracking_System.jwtauth.service;// Sample Java class

import com.sasoftbd.Employee_Tracking_System.constant.Role;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Users;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.RefreshTokenRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
//    public Users register(AuthRequest request) {
//        Users user = new Users();
//        user.setUsername(request.username());
//        user.setPassword(passwordEncoder.encode(request.password()));
//        user.setRole("");
//        return userRepository.save(user);
//    }


    public ResponseEntity<Object> register(AuthRequest req) {

        if (userRepository.existsByUsername(req.username())) {
            return new ResponseEntity<>("{\"message\":\"Username already exists\"}", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(req.email())) {
            return new ResponseEntity<>("{\"message\":\"Email already exists\"}", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByNumber(req.number())) {
            return new ResponseEntity<>("{\"message\":\"Number already exists\"}", HttpStatus.BAD_REQUEST);
        }

        Role role;
        try {
            role = Role.valueOf(req.role().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("{\"message\":\"Invalid role: " + req.role() + "\"}", HttpStatus.BAD_REQUEST);
        }

        // === Generate cardNo ===
        String lastCardNo = userRepository
                .findTopCardNo(PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElse(null);

        int nextNumber = 10000; // default starting number

        if (lastCardNo != null && lastCardNo.startsWith("M-")) {
            try {
                int lastNumber = Integer.parseInt(lastCardNo.substring(2));
                nextNumber = lastNumber + 1;
            } catch (NumberFormatException ignored) {
                // If somehow the old cardNo is malformed, just use the default nextNumber
            }
        }

        String newCardNo = "M-" + nextNumber;

        // === Save new user ===
        Users user = new Users();
        user.setUsername(req.username());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setEmail(req.email());
        user.setNumber(req.number());
        user.setImage(req.image());
        user.setRole(role);
        user.setStatus(true);
        user.setCardNo(newCardNo);

        userRepository.save(user);

        return new ResponseEntity<>("{\"message\":\"User registered successfully\"}", HttpStatus.OK);
    }




//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
//        String refreshToken = request.get("refreshToken");
//
//        Optional<RefreshToken> rt = refreshTokenRepository.findByToken(refreshToken);
//        if (rt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("message", "Invalid refresh token"));
//        }
//
//        // Validate expiration
//        if (rt.get().getExpiryDate().isBefore(Instant.now())) {
//            refreshTokenRepository.delete(rt.get());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("message", "Refresh token expired"));
//        }
//
//        JwtUtil jwtUtil = new JwtUtil();
//
//        String username = jwtUtil.extractUsername(refreshToken); // You need to implement this
//
//        String newAccessToken = jwtUtil.generateToken(username);
//        return ResponseEntity.ok(Map.of("token", newAccessToken));
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }


//    public ResponseEntity<?> login(AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        String accessToken = JwtUtil.generateAccessToken(request.getUsername());
//        String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());
//
//        // Optionally save refreshToken to DB
//        RefreshToken rt = new RefreshToken();
//        rt.setToken(refreshToken);
//        rt.setUsername(request.getUsername());
//        rt.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
//        refreshTokenRepository.save(rt);
//
//        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
//    }
}

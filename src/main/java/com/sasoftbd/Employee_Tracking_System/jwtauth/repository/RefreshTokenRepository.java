package com.sasoftbd.Employee_Tracking_System.jwtauth.repository;

import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsername(String username);
}

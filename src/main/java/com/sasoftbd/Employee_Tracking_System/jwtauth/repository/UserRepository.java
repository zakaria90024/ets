package com.sasoftbd.Employee_Tracking_System.jwtauth.repository;// Sample Java class

import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByNumber(String number);


    @Query("SELECT u.cardNo FROM Users u WHERE u.cardNo IS NOT NULL ORDER BY u.id DESC LIMIT 1")
    String findLastCardNo();

    @Query("SELECT u.cardNo FROM Users u WHERE u.cardNo IS NOT NULL ORDER BY u.id DESC")
    List<String> findTopCardNo(Pageable pageable);


}

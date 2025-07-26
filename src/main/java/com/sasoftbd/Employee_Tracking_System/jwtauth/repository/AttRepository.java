package com.sasoftbd.Employee_Tracking_System.jwtauth.repository;

import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AttRepository extends JpaRepository<Attendance, String> {
    boolean existsById(String id);
    @Query("SELECT a FROM Attendance a WHERE a.strEMP_CARD_NO = :cardNo AND a.strATTEN_DATEIN = :dateIn")
    Optional<List<Attendance>> findAttendance(@Param("cardNo") String cardNo, @Param("dateIn") String dateIn);

}

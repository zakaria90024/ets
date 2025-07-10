package com.sasoftbd.Employee_Tracking_System.jwtauth.repository;

import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Attendance;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT a FROM Location a WHERE a.strEMP_CARD_NO = :cardNo AND a.insertDate = :dateIn")
    List<Location> findLocation(@Param("cardNo") String cardNo, @Param("dateIn") String dateIn);

}
package com.sasoftbd.Employee_Tracking_System.jwtauth.controller;

import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Attendance.AttRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Attendance.AttendanceRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Attendance;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.AttRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.UserRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.service.AttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttController {


    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttRepository attendanceRepository;

    @Autowired
    private AttService AttendanceService;

    @PostMapping("/read")
    public List<Attendance> getAttendance(@RequestBody AttendanceRequest request) {
        return AttendanceService.getAttendance(
                request.getStrEMP_CARD_NO(),
                request.getStrATTEN_DATEIN()
        );
    }


    @PostMapping("/insert")
    public ResponseEntity<?> saveAttendance(@RequestBody AttRequest request) {
        return AttendanceService.saveAttendance(request);
    }


}

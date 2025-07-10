package com.sasoftbd.Employee_Tracking_System.jwtauth.service;

import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Attendance.AttRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Attendance;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.AttRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.RefreshTokenRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class AttService {

    @Autowired
    private UserRepository userRepository;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AttRepository attendanceRepository;


    public ResponseEntity<?> saveAttendance(AttRequest req) {

        if (req.strLATITUDE() == null || req.strLONGITUDE() == null || req.strADDRESS() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Latitude, Longitude and Address are required"));
        }

        LocalDateTime now = LocalDateTime.now();
        String insertDate = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String insertTime = now.format(DateTimeFormatter.ofPattern("hh:mm a"));


        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String id = formattedDate + req.strEMP_CARD_NO() + req.strATTEN_STATUS();
        Attendance attendance = new Attendance();
        attendance.setId(id);
        attendance.setAppsVersion(req.appsVersion());
        attendance.setIntDISTANCE(req.intDISTANCE());
        attendance.setStrACTION(req.strACTION());
        attendance.setStrADDRESS(req.strADDRESS());
        attendance.setStrATTEN_COMMENTS(req.strATTEN_COMMENTS());
        attendance.setStrATTEN_DATEIN(req.strATTEN_DATEIN());
        attendance.setStrATTEN_SHIFT(req.strATTEN_SHIFT());
        attendance.setStrATTEN_STATUS(req.strATTEN_STATUS());
        attendance.setStrATTEN_TIMEIN(req.strATTEN_TIMEIN());
        attendance.setStrEMP_CARD_NO(req.strEMP_CARD_NO());
        attendance.setStrEMP_IMAGE(req.strEMP_IMAGE());
        attendance.setStrLATITUDE(req.strLATITUDE());
        attendance.setStrLONGITUDE(req.strLONGITUDE());
        attendance.setStrROLE(req.strROLE());
        attendance.setStrUSER_NAME(req.strUSER_NAME());
        attendance.setInsertDate(insertDate);
        attendance.setInsertTime(insertTime);
        attendanceRepository.save(attendance);
        return ResponseEntity.ok(Map.of("id", id, "message", "Attendance saved successfully"));

    }


    public Attendance getAttendance(String strEMP_CARD_NO, String strATTEN_DATEIN) {
        return attendanceRepository
                .findAttendance(strEMP_CARD_NO, strATTEN_DATEIN)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance not found"));
    }

}

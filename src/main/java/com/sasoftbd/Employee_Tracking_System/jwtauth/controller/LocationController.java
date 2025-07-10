package com.sasoftbd.Employee_Tracking_System.jwtauth.controller;


import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Location;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> saveLocation(@RequestBody Location location) {
        try {
            // Auto set current server date and time
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

            LocalDateTime now = LocalDateTime.now();

            location.setInsertDate(now.format(dateFormatter));
            location.setInsertTime(now.format(timeFormatter));  // "02:25 PM"

            Location saved = locationRepository.save(location);

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("message", "Failed to save location", "error", e.getMessage()));
        }
    }


    @GetMapping("/read")
    public ResponseEntity<?> getByCardNoAndDate(
            @RequestParam String strEMP_CARD_NO,
            @RequestParam String insertDate
    ) {
        List<Location> results = locationRepository.findLocation(strEMP_CARD_NO, insertDate);

        if (results.isEmpty()) {
            return ResponseEntity.status(404).body(
                    java.util.Map.of("message", "No location data found for given card no and date"));
        }

        return ResponseEntity.ok(results);
    }


}

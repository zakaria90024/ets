package com.sasoftbd.Employee_Tracking_System.jwtauth.MyWebSocketClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NotificationController {

//    @GetMapping("/api/notify")
//    public ResponseEntity<String> notifyClients() throws IOException {
//        MyWebSocketHandler.sendToAllClients("অভিনন্দন!", "আপনার জন্য নতুন মেসেজ এসেছে।");
//        return ResponseEntity.ok("Notification sent to all clients!");
//    }


    @GetMapping("/api/notify")
    public ResponseEntity<String> notifyClients(
            @RequestParam String title,
            @RequestParam String body) throws IOException {

        MyWebSocketHandler.sendToAllClients(title, body);
        return ResponseEntity.ok("Notification sent to all clients!");
    }
}


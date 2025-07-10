package com.sasoftbd.Employee_Tracking_System.jwtauth.controller;

import com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Auth.AuthRequest;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.AccessModule;
import com.sasoftbd.Employee_Tracking_System.jwtauth.entity.Users;
import com.sasoftbd.Employee_Tracking_System.jwtauth.repository.UserRepository;
import com.sasoftbd.Employee_Tracking_System.jwtauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
public class AccessController {

    @Autowired
    private UserService userService;
    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String hello() {
        return "Hello, authenticated user!";

//        Users user = new Users();
//        user.setUsername("zakaria");
//        user.setPassword(passwordEncoder.encode("12345678"));
//        user.setEmail("zakaria@example.com");
//        user.setRole(Role.ADMIN);
//
//        AccessModule module1 = new AccessModule();
//        module1.setModuleName("Dashboard");
//        module1.setCanAccess(true);
//        module1.setUser(user);
//
//        AccessModule module2 = new AccessModule();
//        module2.setModuleName("Reports");
//        module2.setCanAccess(false);
//        module2.setUser(user);
//
//        user.setModules(List.of(module1, module2));
//
//        userService.register(user);  // CascadeType.ALL দিয়ে module গুলোও save হবে

    }


    @PostMapping("/module")
    public ResponseEntity<Object> access(@RequestBody AuthRequest req) {
        Optional<Users> optionalUser = userRepository.findByUsername(req.username());

        if (optionalUser.isEmpty() || !passwordEncoder.matches(req.password(), optionalUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        Users user = optionalUser.get();

        // পুরনো module গুলা নামের তালিকা
        Set<String> existingModuleNames = user.getModules() != null
                ? user.getModules().stream().map(AccessModule::getModuleName).collect(Collectors.toSet())
                : new HashSet<>();

        List<AccessModule> newModules = new ArrayList<>();
        for (AccessModule dto : req.modules()) {
            if (!existingModuleNames.contains(dto.getModuleName())) {
                AccessModule module = new AccessModule();
                module.setModuleName(dto.getModuleName());
                module.setCanAccess(dto.isCanAccess());
                module.setUser(user);
                newModules.add(module);
            }
        }

        if (newModules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "All provided modules already exist for the user"));
        }

        if (user.getModules() == null) {
            user.setModules(newModules);
        } else {
            user.getModules().addAll(newModules);
        }

        try {
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Modules added successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save modules", "details", e.getMessage()));
        }
    }


}
package com.sasoftbd.Employee_Tracking_System.jwtauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*--

CORS এর পূর্ণরূপ হলো:
👉 Cross-Origin Resource Sharing

এটি একটি সিকিউরিটি ফিচার যেটি ব্রাউজার ব্যবহার করে। যখন আপনার Angular frontend (localhost:4200) একটি Spring Boot backend (localhost:8080)-এ API call করে,
তখন যেহেতু দুইটি অন্য origin (পোর্ট আলাদা), ব্রাউজার সেই request ব্লক করতে পারে — এটাই CORS issue।

--*/


@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // সব রুটে অনুমতি
                        .allowedOrigins("http://localhost:4200") // Angular app
                        .allowedOrigins("http://192.168.115.1:8080") // Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

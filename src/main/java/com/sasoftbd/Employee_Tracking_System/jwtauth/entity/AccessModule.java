package com.sasoftbd.Employee_Tracking_System.jwtauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class AccessModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String moduleName;  // eg. "Dashboard", "Admin Panel", "Sales"

    private boolean canAccess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // foreign key to Users table
    private Users user;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public boolean isCanAccess() {
        return canAccess;
    }

    public void setCanAccess(boolean canAccess) {
        this.canAccess = canAccess;
    }

    public Users getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(Users user) {
        this.user = user;
    }
}

package com.sasoftbd.Employee_Tracking_System.jwtauth.entity;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String strADDRESS;
    private String strBAT_CHARGE;
    private String strDISTANCE;
    private String strDURATION;
    private String strEMP_CARD_NO;
    private String strEND_TIME;
    private String strINTERVAL;
    private String strLATITUDE;
    private String strLONGITUDE;
    private String strRole;
    private String strSTART_TIME;
    private String strUSER_NAME;
    @Column(name = "insert_date")
    private String insertDate;

    @Column(name = "insert_time")
    private String insertTime;


    // Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrADDRESS() {
        return strADDRESS;
    }

    public void setStrADDRESS(String strADDRESS) {
        this.strADDRESS = strADDRESS;
    }

    public String getStrBAT_CHARGE() {
        return strBAT_CHARGE;
    }

    public void setStrBAT_CHARGE(String strBAT_CHARGE) {
        this.strBAT_CHARGE = strBAT_CHARGE;
    }

    public String getStrDISTANCE() {
        return strDISTANCE;
    }

    public void setStrDISTANCE(String strDISTANCE) {
        this.strDISTANCE = strDISTANCE;
    }

    public String getStrDURATION() {
        return strDURATION;
    }

    public void setStrDURATION(String strDURATION) {
        this.strDURATION = strDURATION;
    }

    public String getStrEMP_CARD_NO() {
        return strEMP_CARD_NO;
    }

    public void setStrEMP_CARD_NO(String strEMP_CARD_NO) {
        this.strEMP_CARD_NO = strEMP_CARD_NO;
    }

    public String getStrEND_TIME() {
        return strEND_TIME;
    }

    public void setStrEND_TIME(String strEND_TIME) {
        this.strEND_TIME = strEND_TIME;
    }

    public String getStrINTERVAL() {
        return strINTERVAL;
    }

    public void setStrINTERVAL(String strINTERVAL) {
        this.strINTERVAL = strINTERVAL;
    }

    public String getStrLATITUDE() {
        return strLATITUDE;
    }

    public void setStrLATITUDE(String strLATITUDE) {
        this.strLATITUDE = strLATITUDE;
    }

    public String getStrLONGITUDE() {
        return strLONGITUDE;
    }

    public void setStrLONGITUDE(String strLONGITUDE) {
        this.strLONGITUDE = strLONGITUDE;
    }

    public String getStrRole() {
        return strRole;
    }

    public void setStrRole(String strRole) {
        this.strRole = strRole;
    }

    public String getStrSTART_TIME() {
        return strSTART_TIME;
    }

    public void setStrSTART_TIME(String strSTART_TIME) {
        this.strSTART_TIME = strSTART_TIME;
    }

    public String getStrUSER_NAME() {
        return strUSER_NAME;
    }

    public void setStrUSER_NAME(String strUSER_NAME) {
        this.strUSER_NAME = strUSER_NAME;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }
}
package com.sasoftbd.Employee_Tracking_System.jwtauth.entity;


import jakarta.persistence.*;


@Entity
public class Attendance {

    @Id
    @Column(unique = true)
    private String id; // format: ddMMyyyyH-M12382IN

    private String appsVersion;
    private String intDISTANCE;
    private String strACTION;

    private String strADDRESS;
    private String strATTEN_COMMENTS;
    private String strATTEN_DATEIN;
    private String strATTEN_SHIFT;
    private String strATTEN_STATUS;
    private String strATTEN_TIMEIN;
    private String strEMP_CARD_NO;
    private String strEMP_IMAGE;
    private String strLATITUDE;
    private String strLONGITUDE;
    private String strROLE;

    private String strUSER_NAME;


    @Column(name = "insert_date")
    private String insertDate;

    @Column(name = "insert_time")
    private String insertTime;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppsVersion() {
        return appsVersion;
    }


    public void setAppsVersion(String appsVersion) {
        this.appsVersion = appsVersion;
    }

    public String getIntDISTANCE() {
        return intDISTANCE;
    }

    public void setIntDISTANCE(String intDISTANCE) {
        this.intDISTANCE = intDISTANCE;
    }

    public String getStrACTION() {
        return strACTION;
    }

    public void setStrACTION(String strACTION) {
        this.strACTION = strACTION;
    }

    public String getStrADDRESS() {
        return strADDRESS;
    }

    public void setStrADDRESS(String strADDRESS) {
        this.strADDRESS = strADDRESS;
    }

    public String getStrATTEN_COMMENTS() {
        return strATTEN_COMMENTS;
    }

    public void setStrATTEN_COMMENTS(String strATTEN_COMMENTS) {
        this.strATTEN_COMMENTS = strATTEN_COMMENTS;
    }

    public String getStrATTEN_DATEIN() {
        return strATTEN_DATEIN;
    }

    public void setStrATTEN_DATEIN(String strATTEN_DATEIN) {
        this.strATTEN_DATEIN = strATTEN_DATEIN;
    }

    public String getStrATTEN_SHIFT() {
        return strATTEN_SHIFT;
    }

    public void setStrATTEN_SHIFT(String strATTEN_SHIFT) {
        this.strATTEN_SHIFT = strATTEN_SHIFT;
    }

    public String getStrATTEN_STATUS() {
        return strATTEN_STATUS;
    }

    public void setStrATTEN_STATUS(String strATTEN_STATUS) {
        this.strATTEN_STATUS = strATTEN_STATUS;
    }

    public String getStrATTEN_TIMEIN() {
        return strATTEN_TIMEIN;
    }

    public void setStrATTEN_TIMEIN(String strATTEN_TIMEIN) {
        this.strATTEN_TIMEIN = strATTEN_TIMEIN;
    }

    public String getStrEMP_CARD_NO() {
        return strEMP_CARD_NO;
    }

    public void setStrEMP_CARD_NO(String strEMP_CARD_NO) {
        this.strEMP_CARD_NO = strEMP_CARD_NO;
    }

    public String getStrEMP_IMAGE() {
        return strEMP_IMAGE;
    }

    public void setStrEMP_IMAGE(String strEMP_IMAGE) {
        this.strEMP_IMAGE = strEMP_IMAGE;
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

    public String getStrROLE() {
        return strROLE;
    }

    public void setStrROLE(String strROLE) {
        this.strROLE = strROLE;
    }

    public String getStrUSER_NAME() {
        return strUSER_NAME;
    }

    public void setStrUSER_NAME(String strUSER_NAME) {
        this.strUSER_NAME = strUSER_NAME;
    }
}


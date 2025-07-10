package com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Attendance;

public class AttendanceRequest {
    private String strEMP_CARD_NO;
    private String strATTEN_DATEIN;

    // Getters and Setters


    public String getStrEMP_CARD_NO() {
        return strEMP_CARD_NO;
    }

    public void setStrEMP_CARD_NO(String strEMP_CARD_NO) {
        this.strEMP_CARD_NO = strEMP_CARD_NO;
    }

    public String getStrATTEN_DATEIN() {
        return strATTEN_DATEIN;
    }

    public void setStrATTEN_DATEIN(String strATTEN_DATEIN) {
        this.strATTEN_DATEIN = strATTEN_DATEIN;
    }
}

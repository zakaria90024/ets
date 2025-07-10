package com.sasoftbd.Employee_Tracking_System.jwtauth.dto.Attendance;

public record AttRequest(
        String appsVersion,
        String intDISTANCE,
        String strACTION,
        String strADDRESS,
        String strATTEN_COMMENTS,
        String strATTEN_DATEIN,
        String strATTEN_SHIFT,
        String strATTEN_STATUS,
        String strATTEN_TIMEIN,
        String strEMP_CARD_NO,
        String strEMP_IMAGE,
        String strLATITUDE,
        String strLONGITUDE,
        String strROLE,
        String strUSER_NAME
) {

}

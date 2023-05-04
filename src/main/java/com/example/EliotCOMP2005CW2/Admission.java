package com.example.EliotCOMP2005CW2;

public class Admission {
    /*
       This is a model to be used for the response bodies
    */
    private int id;
    private String admissionDate;
    private String dischargeDate;
    private int patientID;

    public String getAdmissionDate() {
        return admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public int getPatientID() {
        return patientID;
    }

}

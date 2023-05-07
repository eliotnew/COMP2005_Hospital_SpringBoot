package com.example.EliotCOMP2005CW2;

public class Admission {
    /*
       This is a model to be used for the response bodies from the admisssion endpoint
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

    public void setId(Integer new_id){ id = new_id;}

    public void setAdmissionDate(String new_date){admissionDate =new_date;}

    public void setDischargeDate(String new_date){dischargeDate =new_date;}

    public  void setPatientID(Integer new_id){patientID = new_id;}


}

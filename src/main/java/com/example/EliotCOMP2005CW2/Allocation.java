package com.example.EliotCOMP2005CW2;

public class Allocation {
    /*
        This is a model to be used for the response bodies from the table allocations
     */
    private int id;
    private int admissionID;
    private int employeeID;
    private String startTime;
    private String endTime;

    // getters and setters

    public int getAdmissionID() {
        return admissionID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setId(Integer new_id){id=new_id;};

    public void setAdmissionID(Integer new_id){admissionID = new_id; }

    public void setEmployeeID(Integer new_id){employeeID = new_id;};

    public void setStartTime(String new_startTime){startTime=new_startTime;};

    public void setEndTime(String new_endTime){endTime=new_endTime;};



}

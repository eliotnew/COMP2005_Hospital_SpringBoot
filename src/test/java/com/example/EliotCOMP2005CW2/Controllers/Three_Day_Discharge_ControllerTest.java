package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Three_Day_Discharge_ControllerTest {
    private Three_Day_Discharge_Controller three_day_discharge_controller;
    @BeforeEach
    void setUp() {
        three_day_discharge_controller = new Three_Day_Discharge_Controller();
    }

    @Test
    void testGetShortStay(){
        //takes admissions returns a list of days
        String startDate = "2020-11-28T16:45:00"; //was a saturday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;


    }
}
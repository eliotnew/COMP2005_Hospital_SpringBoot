package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Busiest_Day_ControllerTest {
    private Busiest_Day_Controller busiest_day_controller;
    @BeforeEach
    void setUp() {
        this.busiest_day_controller = new Busiest_Day_Controller();
    }

    @Test
    public void testGetDayTally(){

        String startDate = "2020-11-28T16:45:00"; //was a saturday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = busiest_day_controller.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1,tally); //asserts that saturday has correctly tallied once


    }

    @Test
    public void testGetDayTally_In100Years(){

        String startDate = "2116-11-28T16:45:00"; // will be a sunday saturday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = busiest_day_controller.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1, tally, "tally for Monday should be 1");

    }
    @Test
    public void testGetDayTally_100Years_In_Past(){

        String startDate = "1930-11-28T16:45:00"; // friday
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        Map<DayOfWeek,Integer> testMap = busiest_day_controller.getDayTally(testAdmissionsArray);


        DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
        int tally = testMap.get(dayOfWeek);

        assertNotNull(tally, "tally should not be null");
        assertEquals(1, tally, "tally for Monday should be 1");

    }
}
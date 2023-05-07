package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
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

    @Test
    public void testHasMultipleBusiestDays(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertFalse(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_True(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_minus(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, -1);
        dayTally.put(DayOfWeek.FRIDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertFalse(test); //should just be one busiest day

    }

    @Test
    public void testHasMultipleBusiestDays_two_minus(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, -1);
        dayTally.put(DayOfWeek.FRIDAY, -1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //no entries values means no patients

    }

    @Test
    public void testHasMultipleBusiestDays_moreThanTwo(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertTrue(test); //no entries values means no patients

    }
    @Test
    public void testHasMultipleBusiestDays_massiveNumber(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 100000);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertFalse(test);

    }

    @Test
    public void testHasMultipleBusiestDays_massiveNumbersTrue(){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 100000);
        dayTally.put(DayOfWeek.TUESDAY, 100000);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        Boolean test = busiest_day_controller.hasMultipleBusiestDays(dayTally);

        assertTrue(test);
    }

    @Test
    public void testDetermineBusiestDay(){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);

        String test = busiest_day_controller.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Monday",test);
    }
    @Test
    public void testDetermineBusiestDay_Massive (){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 999999999);

        String test = busiest_day_controller.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Monday",test);

    }
    @Test
    public void testDetermineBusiestDay_zero (){
        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 0);
        dayTally.put(DayOfWeek.THURSDAY, 1);

        String test = busiest_day_controller.determineBusiestDay(dayTally);

        System.out.println(test);
        assertEquals( "Thursday",test);
    }

    @Test
    public void testDetermineAllBusiestDays (){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);

        String test = busiest_day_controller.determineAllBusyDays(dayTally);
        //System.out.println(test);

        assertEquals("Monday Thursday ",test);

    }

    @Test
    public void testDetermineAllBusiestDays_All_Days (){

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();
        dayTally.put(DayOfWeek.MONDAY, 1);
        dayTally.put(DayOfWeek.TUESDAY, 1);
        dayTally.put(DayOfWeek.WEDNESDAY, 1);
        dayTally.put(DayOfWeek.THURSDAY, 1);
        dayTally.put(DayOfWeek.FRIDAY, 1);
        dayTally.put(DayOfWeek.SATURDAY, 1);
        dayTally.put(DayOfWeek.SUNDAY, 1);

        String test = busiest_day_controller.determineAllBusyDays(dayTally);
        //System.out.println(test);

        assertEquals("Tuesday Wednesday Thursday Monday Sunday Saturday Friday ",test);

    }
}
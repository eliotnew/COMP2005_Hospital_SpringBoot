package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.constant.Constable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Three_Day_Discharge_ControllerTest {
    private Three_Day_Discharge_Controller three_day_discharge_controller;
    @BeforeEach
    void setUp() {
        three_day_discharge_controller = new Three_Day_Discharge_Controller();
    }

    @Test
    void testGetShortStay_1Day(){

        String startDate = "2020-11-28T17:45:00.000Z"; //correct time zone!!!!!!
        String finishDate = "2020-11-29T17:45:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;


        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(1,testList.size());
        assertEquals(1,testList.get(0));

        //returns patient ID 1
    }
    @Test
    void testGetShortStay_When4Days(){

        String startDate = "2020-11-24T17:45:00.000Z"; //correct time zone!!!!!!
        String finishDate = "2020-11-28T17:45:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(0,testList.size());

    }

    @Test
    void testGetShortStay_BigListAllShort(){

        Admission[] testAdmissionsArray = new Admission[999];

        String startDate = "2020-11-28T16:45:00.000Z";
        String finishDate = "2020-11-29T17:45:00.000Z";

        for (int i = 0; i <999 ; i++) {
            Admission admissionTest = new Admission();

            admissionTest.setId(i);
            admissionTest.setPatientID(i);
            admissionTest.setAdmissionDate(startDate);
            admissionTest.setDischargeDate(finishDate);

            testAdmissionsArray[i] = admissionTest;
        }

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(999,testList.size());
        assertEquals(998,testList.get(998));
    }
    @Test
    void testGetShortStay_BigListNoShortStays(){

        Admission[] testAdmissionsArray = new Admission[999];

        String startDate = "2020-11-20T16:45:00.000Z"; //correct time zone!!!!!!
        String finishDate = "2020-11-29T17:45:00.000Z";

        for (int i = 0; i <999 ; i++) {
            Admission admissionTest = new Admission();

            admissionTest.setId(i);
            admissionTest.setPatientID(i);
            admissionTest.setAdmissionDate(startDate);
            admissionTest.setDischargeDate(finishDate);

            testAdmissionsArray[i] = admissionTest;
        }

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(0,testList.size());
    }
    @Test
    void testGetShortStay_TimeTraveller_Sub3Days(){

        String finishDate = "2020-11-28T16:45:00.000Z";
        String startDate = "2020-12-28T16:45:00.000Z";
        //I've swapped the times so they have stayed for -1 day and -1 hour

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);
        System.out.println(testList);

        assertEquals(0,testList.size()); //I would expect it to not count if they were dismissed before they were admitted


    }
    @Test
    void testGetShortStay_TimeTraveller_MoreThan3Days(){
        //Ensures that stays spanning more than three days and are negative wont be added.

        String finishDate = "2020-09-28T17:45:00.000Z";
        String startDate = "2020-12-28T17:45:00.000Z";
        //I've swapped the timesso they have stayed for -3 day and -1 hour

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(0,testList.size());

    }
    @Test
    void testGetShortStay_With_3Days_1Hours(){

        String startDate = "2020-11-01T00:00:00.000Z";
        String finishDate = "2020-11-04T01:00:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(0,testList.size());

    }
    @Test
    void testGetShortStay_With_2_Days_23Hours_59Mins(){

        String startDate = "2020-11-28T00:00:00.000Z";
        String finishDate = "2020-11-30T23:59:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(1,testList.size());
        assertEquals(1,testList.get(0));
    }
}
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
    void testGetShortStay(){

        String startDate = "2020-11-28T16:45:00";
        String finishDate = "2020-11-28T17:45:00";

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

        String startDate = "2020-11-24T16:45:00.744Z"; //correct time zone!!!!!!
        String finishDate = "2020-11-28T17:45:00.744Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] testAdmissionsArray = new Admission[1];
        testAdmissionsArray[0] = admissionTest;

        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);

        assertEquals(0,testList.size());
        //assertEquals(1,testList.get(0));

        //returns patient ID 1
    }

    @Test
    void testGetShortStay_BigListAllShort(){

        Admission[] testAdmissionsArray = new Admission[999];

        String startDate = "2020-11-28T16:45:00";
        String finishDate = "2020-11-28T17:45:00";

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

        String startDate = "2019-11-28T16:45:00";
        String finishDate = "2020-11-28T17:45:00";

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

        String finishDate = "2020-11-28T16:45:00";
        String startDate = "2020-12-28T17:45:00";
        //I've swapped the times so they have stayed for -1 day and -1 hour

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

        //Time calculated duration doesnt seem to care which direction the duration is going so this is possible in my program

    }
//    @Test
//    void testGetShortStay_TimeTraveller_MoreThan3Days(){
//
//        String finishDate = "2020-09-28T17:45:00";
//        String startDate = "2020-12-28T17:45:00";
//        //I've swapped the timesso they have stayed for -3 day and -1 hour
//
//        Admission admissionTest = new Admission();
//
//        admissionTest.setId(1);
//        admissionTest.setPatientID(1);
//        admissionTest.setAdmissionDate(startDate);
//        admissionTest.setDischargeDate(finishDate);
//
//        Admission[] testAdmissionsArray = new Admission[1];
//        testAdmissionsArray[0] = admissionTest;
//
//        List<Integer> testList = three_day_discharge_controller.getShortStays(testAdmissionsArray);
//
//        assertEquals(0,testList.size());
//        //assertEquals(1,testList.get(0));
//
//        //Time calculated duration doesnt seem to care which direction the duration is going so this is possible in my program
//
//    }
    @Test
    void testGetShortStay_With_23_Hours(){

        String startDate = "2020-11-28T23:00:00";
        String finishDate = "2020-11-28T00:00:00";

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
    @Test
    void testGetShortStay_With_1_Min(){

        String startDate = "2020-11-28T00:01:00";
        String finishDate = "2020-11-28T00:00:00";

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
    @Test
    void testGetShortStay_With_3Days_1Hours(){

        String startDate = "2020-11-01T00:00:00";
        String finishDate = "2020-11-04T01:00:00";

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
    @Test
    void testGetShortStay_With_2_Days(){

        String startDate = "2020-11-28T00:00:00";
        String finishDate = "2020-11-30T00:00:00";

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
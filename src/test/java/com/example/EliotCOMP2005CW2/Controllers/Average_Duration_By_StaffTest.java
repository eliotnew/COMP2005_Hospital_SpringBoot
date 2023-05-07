package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.example.EliotCOMP2005CW2.Allocation;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServlet;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Average_Duration_By_StaffTest {
    private Average_Duration_By_Staff average_duration_by_staff;

    @BeforeEach
    public void setup() {
        this.average_duration_by_staff = new Average_Duration_By_Staff();
    }


    @Test
    void testGetAdmissionIdsFromEmployeeID_With1(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = average_duration_by_staff.getAdmissionIdsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(121,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithNegative(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(-121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = average_duration_by_staff.getAdmissionIdsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(-121,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithMassiveValue(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(123456789);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = average_duration_by_staff.getAdmissionIdsFromEmployeeID(100,testAllocations);

        assertEquals(1,testList.size());
        assertEquals(123456789,testList.get(0));

    }

    @Test
    void testGetAdmissionIdsFromEmployeeID_WithNoMatch(){
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(123456789);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        List<Integer> testList = average_duration_by_staff.getAdmissionIdsFromEmployeeID(50,testAllocations);

        assertEquals(0,testList.size());

    }

    @Test
    void testCalculateStayDurationHours(){

        String startDate = "2020-11-28T16:45:00";
        String finishDate = "2020-11-28T17:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        double result = average_duration_by_staff.calculateStayDurationHours(admissionTest);

        assertEquals(1.00,result);

    }

    @Test
    void testCalculateStayDurationHours_Negative(){

        String startDate = "2020-11-28T17:45:00";
        String finishDate = "2020-11-28T16:45:00";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        double result = average_duration_by_staff.calculateStayDurationHours(admissionTest);

        assertEquals(-1.00,result);

    }


    @Test
    void collectStayDurations(){

    }


    /*
      ------------------Test's average hour calculator------------------
     */

    @Test
    void testShowAverageHours_WithEmptyList(){ //Tests empty List
        List<Double> emptyList = new ArrayList<>();
        Double expected = 0.0;
        Double Actual = average_duration_by_staff.showAverageHours(emptyList);
        assertEquals(expected,Actual);

    }

    @Test
    void testShowAverageHours_WithPositiveList(){

        List<Double> posiList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            posiList.add(5.0);
        }

        Double expected = 5.0;
        Double Actual = average_duration_by_staff.showAverageHours(posiList);
        assertEquals(expected,Actual);

    }

    @Test
    void testShowAverageHours_WithNegativeList(){

        List<Double> negaList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            negaList.add(-5.0);
        }

        Double expected = -5.0;
        Double Actual = average_duration_by_staff.showAverageHours(negaList);
        assertEquals(expected,Actual);

    }

    @Test
    void testShowAverageHours_WithMassiveList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 99999; i++) {
            massiveList.add(5.5);
        }

        Double expected = 5.5;
        Double Actual = average_duration_by_staff.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithZeroesList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            massiveList.add(0.0);
        }

        Double expected = 0.0;
        Double Actual = average_duration_by_staff.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithZeroesMixedInList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            massiveList.add((double) i);
        }

        Double expected = 2.5;
        Double Actual = average_duration_by_staff.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }

    @Test
    void testShowAverageHours_WithLargeNumbersList(){
        List<Double> massiveList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            massiveList.add(691236123.23);
        }

        Double expected = 691236123.23;
        Double Actual = average_duration_by_staff.showAverageHours(massiveList);
        assertEquals(expected,Actual);
    }
}
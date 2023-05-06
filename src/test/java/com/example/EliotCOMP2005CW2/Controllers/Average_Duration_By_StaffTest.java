package com.example.EliotCOMP2005CW2.Controllers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class Average_Duration_By_StaffTest {
    private Average_Duration_By_Staff average_duration_by_staff;

    @BeforeEach
    public void setup() {
        this.average_duration_by_staff = new Average_Duration_By_Staff();
    }


    @Test
    void getAverageDurationByStaff() {
    }

    @Test
    void findAdmissionIDsByEmployeeID(){

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
}
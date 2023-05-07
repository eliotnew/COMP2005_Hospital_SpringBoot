package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Allocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Get_Staffs_PatientsTest {
    private Get_Staffs_Patients get_staffs_patients;


    @BeforeEach
    void setUp() {
        get_staffs_patients=new Get_Staffs_Patients();
    }

    @Test
    void testCreateAdmissionIDList(){

        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(40);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Integer testID = 40;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        assertEquals(121,listEntry);

    }

    @Test
    void testCreateAdmissionIDList_MinusNumber(){

        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(-121);
        allocation1.setEmployeeID(40);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Integer testID = 40;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        assertEquals(-121,listEntry);

    }
    @Test
    void testCreateAdmissionIDList_MassiveNumber(){

        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(999999999);
        allocation1.setEmployeeID(888888888);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Integer testID = 888888888;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        assertEquals(999999999,listEntry);

    }

    @Test
    void testCreateAdmissionIDList_NegativeMassiveNumber(){

        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(-999999999);
        allocation1.setEmployeeID(-888888888);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Integer testID = -888888888;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        assertEquals(-999999999,listEntry);

    }

    @Test
    void testCreateAdmissionIDList_moreThanOne(){

        Allocation[] testAllocations = new Allocation[2];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(123);
        allocation1.setEmployeeID(10);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Allocation allocation2 = new Allocation();
        allocation2.setId(23);
        allocation2.setAdmissionID(456);
        allocation2.setEmployeeID(10);
        allocation2.setStartTime("2020-11-28T16:45:00");
        allocation2.setEndTime("2020-11-28T17:45:00");

        testAllocations[1] = allocation2;

        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        Integer listEntry2 = testList.get(1);

        assertEquals(123,listEntry);
        assertEquals(456,listEntry2);

    }

    @Test
    void testCreateAdmissionIDList_oneMatch_oneNot(){

        Allocation[] testAllocations = new Allocation[2];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(123);
        allocation1.setEmployeeID(10);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Allocation allocation2 = new Allocation();
        allocation2.setId(23);
        allocation2.setAdmissionID(456);
        allocation2.setEmployeeID(9);
        allocation2.setStartTime("2020-11-28T16:45:00");
        allocation2.setEndTime("2020-11-28T17:45:00");

        testAllocations[1] = allocation2;

        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);

        assertEquals(123,listEntry);
        assertEquals(1,testList.size());

    }


    @Test
    void testCreateAdmissionIDList_with_clones(){

        Allocation[] testAllocations = new Allocation[2];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(123);
        allocation1.setEmployeeID(10);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;

        Allocation allocation2 = new Allocation();
        allocation2.setId(43);
        allocation2.setAdmissionID(123);
        allocation2.setEmployeeID(10);
        allocation2.setStartTime("2020-11-28T16:45:00");
        allocation2.setEndTime("2020-11-28T17:45:00");

        testAllocations[1] = allocation2;


        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);
        Integer listEntry = testList.get(0);
        Integer listEntry2 = testList.get(1);

        assertEquals(123,listEntry);
        assertEquals(123,listEntry2);
        assertEquals(2,testList.size());

    }
    @Test
    public void testCreateAdmissionIDList_Massive(){
        Allocation[] testAllocations = new Allocation[900];

        for (int i = 0; i < testAllocations.length ; i++) {

            Allocation allocation1 = new Allocation();
            allocation1.setId(i);
            allocation1.setAdmissionID(123);
            allocation1.setEmployeeID(10);
            allocation1.setStartTime("2020-11-28T16:45:00");
            allocation1.setEndTime("2020-11-28T17:45:00");

            testAllocations[i] = allocation1;

        }
        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);

        Integer counter =0;
        for (Integer val : testList) {
            assertEquals(123,testList.get(counter));
            counter++;
        }
        assertEquals(900,testList.size());
    }

    @Test
    public void testCreateAdmissionIDList_Massive_NO_MATCHES(){
        Allocation[] testAllocations = new Allocation[900];

        for (int i = 0; i < testAllocations.length ; i++) {

            Allocation allocation1 = new Allocation();
            allocation1.setId(i);
            allocation1.setAdmissionID(123);
            allocation1.setEmployeeID(9);
            allocation1.setStartTime("2020-11-28T16:45:00");
            allocation1.setEndTime("2020-11-28T17:45:00");

            testAllocations[i] = allocation1;

        }
        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);

        assertEquals(0,testList.size());
    }

    @Test
    public void testCreateAdmissionIDList_Massive_One_M(){
        Allocation[] testAllocations = new Allocation[900];

        for (int i = 0; i < testAllocations.length ; i++) {

            Allocation allocation1 = new Allocation();
            allocation1.setId(i);
            allocation1.setAdmissionID(123);
            allocation1.setEmployeeID(i);
            allocation1.setStartTime("2020-11-28T16:45:00");
            allocation1.setEndTime("2020-11-28T17:45:00");

            testAllocations[i] = allocation1;

        }
        Integer testID = 10;

        List<Integer> testList = get_staffs_patients.createAdmissionIDList(testAllocations,testID);

        assertEquals(1,testList.size());
        assertEquals(123,testList.get(0));
    }



}
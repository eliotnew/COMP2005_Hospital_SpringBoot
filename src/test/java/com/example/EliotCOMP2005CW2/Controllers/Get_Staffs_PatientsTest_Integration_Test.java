package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.example.EliotCOMP2005CW2.Allocation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//I beleive this test is written ok but just does not work because of another reason i wil come back.
class Get_Staffs_PatientsTest_Integration_Test {

    private Get_Staffs_Patients mockGetStaffsPatients = mock(Get_Staffs_Patients.class);


    @Before
    public void setup(){

        //Assert the allocations to go in and the id to take to search with
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(43);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(40);
        allocation1.setStartTime("2020-11-28T16:45:00.000Z");
        allocation1.setEndTime("2020-11-28T17:45:00.000Z");

        testAllocations[0] = allocation1;

        when(mockGetStaffsPatients.getAllocations()).thenReturn(testAllocations);

        List<Integer> mockPatientIDList = new ArrayList<>();
        mockPatientIDList.add(72);


        when(mockGetStaffsPatients.getPatientIDWithAdmissionID(any())).thenReturn(mockPatientIDList);

    }

    @Test
    void IntegrateTestThatListCorrectlyReturned(){
        List<Integer> result = mockGetStaffsPatients.Get_Staffs_Patients(40);
        assertEquals(1,result.size());
    }

}
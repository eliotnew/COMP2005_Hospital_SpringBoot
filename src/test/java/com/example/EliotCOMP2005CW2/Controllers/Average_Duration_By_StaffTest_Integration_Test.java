package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Allocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Average_Duration_By_StaffTest_Integration_Test {
    private Average_Duration_By_Staff mockAverageDurationByStaff = mock(Average_Duration_By_Staff.class);
    //Integration tests returning null objects again!!

    @BeforeEach
    public void setup() throws IOException {
        Integer mockInputStaffId = 100;
        Allocation[] testAllocations = new Allocation[1];

        Allocation allocation1 = new Allocation();
        allocation1.setId(40);
        allocation1.setAdmissionID(121);
        allocation1.setEmployeeID(100);
        allocation1.setStartTime("2020-11-28T16:45:00");
        allocation1.setEndTime("2020-11-28T17:45:00");

        testAllocations[0] = allocation1;
        when(mockAverageDurationByStaff.getAllocations(any())).thenReturn(testAllocations);

        Double mockHours = 1.00;
        List<Double> mockHoursList = new ArrayList<>();
        mockHoursList.add(mockHours);

        when(mockAverageDurationByStaff.calculateStayDurationHours(any())).thenReturn(mockHours);
        when(mockAverageDurationByStaff.collectStayDurations(any())).thenReturn(mockHoursList);


    }

    @Test
    public void testOutputIntegrated(){
        Double mockHours = 1.00;
        List<Double> mockHoursList = new ArrayList<>();
        mockHoursList.add(mockHours);

        String answer = mockAverageDurationByStaff.getAverageDurationByStaff(100);

        boolean contains1hr = answer.contains("1.00");
        boolean containsid = answer.contains("100");

        assertTrue(contains1hr);
        assertTrue(containsid);

    }

}
package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class Three_Day_Discharge_Controller_Integration_Test {


//    @Mock
//    private Three_Day_Discharge_Controller;

    @Test
    void mockResponseIntegrate3Day(){
        //First i Create a mock Admission[] which i expect to pass through the whole class and get it's patient id returned in a list
        String startDate = "2020-11-28T17:45:00.000Z";
        String finishDate = "2020-11-29T17:45:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(1);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        //Instantiating a mock version of the class
        Three_Day_Discharge_Controller three_day_discharge_controller = mock(Three_Day_Discharge_Controller.class);

        //Telling it to return the mock Admission[] instead of actually retrieving an array
        doReturn(mockAdmissionsArray).when(three_day_discharge_controller.getAdmissions());

        List<Integer> mockResults = three_day_discharge_controller.getPatients3Days();
        // Assert that the list contains only the expected patient ID of 1 and has a size of 1
        assertEquals(1, mockResults.size());
        assertTrue(mockResults.contains(1));




    }

    @AfterEach
    void tearDown() {
    }
}
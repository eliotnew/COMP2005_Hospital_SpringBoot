package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Three_Day_Discharge_Controller_Integration_Test {


    private Three_Day_Discharge_Controller mockThreeDay;

    @Before
    public void setup(){
        mockThreeDay = mock(Three_Day_Discharge_Controller.class);
    }
    @Test
    public void checkListSize(){
        String startDate = "2020-11-27T17:45:00.000Z";
        String finishDate = "2020-11-29T17:45:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        assertEquals(78,mockAdmissionsArray[0].getPatientID());

        when(mockThreeDay.getAdmissions()).thenReturn(mockAdmissionsArray);

        List<Integer> result = mockThreeDay.getPatients3Days();
        Integer resultGot = result.get(0);

        assertEquals(78,resultGot);

    }


    @Test
    void mockResponseIntegrate3Day(){

        //First i Create a mock Admission[] which i expect to pass through the whole class by forcing it to return in GetAdmissions() and get it's patient id returned in a list
        String startDate = "2020-11-27T17:45:00.000Z";
        String finishDate = "2020-11-29T17:45:00.000Z";

        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate(startDate);
        admissionTest.setDischargeDate(finishDate);

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        assertEquals(78,mockAdmissionsArray[0].getPatientID());

        // Create a mock Three_Day_Discharge_Controller
        Three_Day_Discharge_Controller three_day_discharge_controller = mock(Three_Day_Discharge_Controller.class);
        when(three_day_discharge_controller.getAdmissions()).thenReturn(mockAdmissionsArray);

        // Call the method that returns a list of patient IDs
        List<Integer> patientIds = three_day_discharge_controller.getPatients3Days();

        // Assert that the list contains only the expected patient ID of 1 and has a size of 1
        assertEquals(1, patientIds.size());
        assertEquals(1,patientIds.get(0).intValue());
    }


}
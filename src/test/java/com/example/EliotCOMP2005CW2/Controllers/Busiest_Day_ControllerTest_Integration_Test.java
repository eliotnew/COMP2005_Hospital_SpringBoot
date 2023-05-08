package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// There appears to be something fishy with my integration tests...
class Busiest_Day_ControllerTest_Integration_Test {
    private Busiest_Day_Controller mockBusiest_day_controller = mock(Busiest_Day_Controller.class);

    @Before
    public void setup(){
        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z"); //is Friday
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        try {
            when(mockBusiest_day_controller.getAdmissionsFromApi()).thenReturn(mockAdmissionsArray);
        } catch (IOException e) {
            System.out.println("whoops");
            throw new RuntimeException(e);
        }

    }
    @Test
    public void assessString(){
        String busiestday = mockBusiest_day_controller.busiestDay(); //returns null apparently
        boolean isFriday = busiestday.contains("Friday");
        assertTrue(isFriday);

    }

}
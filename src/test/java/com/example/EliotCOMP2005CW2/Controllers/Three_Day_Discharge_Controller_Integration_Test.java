package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//I beleive it is a correct mock test but again lists are not being made properly
@ExtendWith(MockitoExtension.class)
public class Three_Day_Discharge_Controller_Integration_Test {


    private Three_Day_Discharge_Controller mockThreeDay = mock(Three_Day_Discharge_Controller.class);

    @BeforeEach
    public void setup(){
        Admission admissionTest = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(78);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z");
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[1];
        mockAdmissionsArray[0] = admissionTest;

        when(mockThreeDay.getAdmissions()).thenReturn(mockAdmissionsArray);

    }
    @Test
    public void checkListSize(){

        List<Integer> result = mockThreeDay.getPatients3Days();
        Integer resultGot = result.get(0);

        assertEquals(78,resultGot);
        assertEquals(1,result.size());
    }




}
package com.example.EliotCOMP2005CW2.Controllers;
import com.example.EliotCOMP2005CW2.Admission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Three_Day_Discharge_Controller.class)
class Three_Day_Discharge_ControllerTest_SpringBoot_Integration {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Three_Day_Discharge_Controller threeDayDischargeController;

    @Mock
    private Admission admission;

    @Test
    public void testGetPatients3Days() throws Exception {

        ///-------------------SETUP THE MOCK RESPONSE----------------------
        Admission admissionTest = new Admission();
        Admission admissionTest2 = new Admission();
        Admission admissionTest3 = new Admission();

        admissionTest.setId(1);
        admissionTest.setPatientID(44);
        admissionTest.setAdmissionDate("2020-11-27T17:45:00.000Z");
        admissionTest.setDischargeDate("2020-11-29T17:45:00.000Z");

        admissionTest2.setId(2);
        admissionTest2.setPatientID(45);
        admissionTest2.setAdmissionDate("2020-11-27T17:45:00.000Z");
        admissionTest2.setDischargeDate("2020-11-29T17:45:00.000Z");

        admissionTest3.setId(3);
        admissionTest3.setPatientID(46);
        admissionTest3.setAdmissionDate("2020-11-27T17:45:00.000Z");
        admissionTest3.setDischargeDate("2020-11-29T17:45:00.000Z");

        Admission[] mockAdmissionsArray = new Admission[3];
        mockAdmissionsArray[0] = admissionTest;
        mockAdmissionsArray[1] = admissionTest2;
        mockAdmissionsArray[2] = admissionTest3;

        ///-------------------FINSIH SETUP THE MOCK RESPONSE----------------------

        // Create a list of patient IDs
        List<Integer> patientIDs = Arrays.asList(44, 45, 46);

        // Set up the mock behavior of the controller
        when(threeDayDischargeController.getAdmissions()).thenReturn(mockAdmissionsArray);
        when(threeDayDischargeController.getShortStays(any())).thenReturn(patientIDs);

        // Perform the GET request
        mockMvc.perform(get("/3daydischarge").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(patientIDs.size())))
                .andExpect(jsonPath("$[0]").value(patientIDs.get(0)))
                .andExpect(jsonPath("$[1]").value(patientIDs.get(1)))
                .andExpect(jsonPath("$[2]").value(patientIDs.get(2)));
    }




}
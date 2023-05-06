package com.example.EliotCOMP2005CW2.Controllers;

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
    void getAverageDurationByStaff() {
    }

//    @Test
//    void testfindAdmissionIDsByEmployeeID() throws IOException, URISyntaxException {
//        WireMockServer wireMockServer = new WireMockServer(options().port(8080));
//        wireMockServer.start();
//        WireMock.configureFor("localhost", wireMockServer.port());
//
//        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/some/endpoint"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(200)
//                        .withBody("[\n  {\n    \"id\": 1,\n    \"admissionID\": 1,\n    \"employeeID\": 4,\n    \"startTime\": \"2020-11-28T16:45:00\",\n    \"endTime\": \"2020-11-28T23:56:00\"\n  },\n  {\n    \"id\": 2,\n    \"admissionID\": 3,\n    \"employeeID\": 4,\n    \"startTime\": \"2021-09-23T21:50:00\",\n    \"endTime\": \"2021-09-24T09:50:00\"\n  }\n]")));
//
//
//        List<Integer> admissionIDs = average_duration_by_staff.findAdmissionIDsByEmployeeID(4, "http://localhost:8080/some/endpoint");
//
//
//        Assertions.assertEquals(2, admissionIDs.size());
//        Assertions.assertEquals(1, admissionIDs.get(0));
//        Assertions.assertEquals(3, admissionIDs.get(1));
//
//        wireMockServer.stop();
//    }


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
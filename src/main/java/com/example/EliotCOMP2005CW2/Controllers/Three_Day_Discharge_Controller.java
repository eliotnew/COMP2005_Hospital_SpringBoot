package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class Three_Day_Discharge_Controller {

    //I made this function in the first attempt of this project which was not springboot
    //It returns a list of patient IDS where the stay was >= 3days
    //
    @GetMapping("/3daydischarge")
    public List<Integer> getPatients3Days() {
        String API_URL = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";
        List<Integer> patientIDs = new ArrayList<>();
        try {
            // HTTP GEt request
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(API_URL);
            request.addHeader("accept", "text/plain");
            CloseableHttpResponse response = httpClient.execute(request);

            // Parse JSON into list of objects using Gson
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Gson gson = new Gson();
            Admission[] admissionArray = gson.fromJson(reader, Admission[].class);
            List<Admission> admissionList = Arrays.asList(admissionArray);

            //  Filter each admissions where the stay is less than or equal to 3 days
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            for (Admission admission : admissionList) {

                //Gets the formatted dates per each admission entry and calculates the duration of stay in days

                LocalDateTime admissionDate = LocalDateTime.parse(admission.getAdmissionDate(), formatter);
                LocalDateTime dischargeDate = LocalDateTime.parse(admission.getDischargeDate(), formatter);
                Duration duration = Duration.between(admissionDate, dischargeDate);

                long daysStay = duration.toDays();
                if (daysStay <= 3) {
                    patientIDs.add(admission.getPatientID());
                }
            }

            return patientIDs;
        }catch(Exception e ){
            System.out.println("Went wrong");
            int size = 1;
            List<Integer> failList = new ArrayList<>(Collections.nCopies(size, -1));

            return failList;
        }
    }
}
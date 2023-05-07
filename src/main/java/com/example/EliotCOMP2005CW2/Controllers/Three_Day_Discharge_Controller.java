package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.example.EliotCOMP2005CW2.Allocation;
import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
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

    @GetMapping("/3daydischarge")
    public List<Integer> getPatients3Days() {

        try {

            Admission[] admissions = getAdmissions();//Gets all admissions

            List<Integer> patientsIDs = getShortStays(admissions); // Sorts through them retreiving IDs of patients with a short stay

            return patientsIDs;

        }catch(Exception e ){
            System.out.println("Went wrong");
            int size = 1;
            List<Integer> failList = new ArrayList<>(Collections.nCopies(size, -1));

            return failList;
        }
    }
    public Admission[] getAdmissions(){
        String API_URL = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";
        try{
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(API_URL);
            request.addHeader("accept", "text/plain");
            CloseableHttpResponse response = httpClient.execute(request);

            // Parse JSON into list of objects using Gson
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            Gson gson = new Gson();
            Admission[] admissionArray = gson.fromJson(reader, Admission[].class);

            return admissionArray;

        }catch (Exception e){
            System.out.println("Failed to get admissions.");
            Admission[] failed = new Admission[0];
            return failed;
        }

    }
    public List<Integer> getShortStays(Admission[] admissions){
        //returns list of patient IDs who stayed less than 3 days

        List<Integer> patientIDs  = new ArrayList<>();
        List<Admission> admissionList = Arrays.asList(admissions);

        //  Filter each admissions where the stay is less than or equal to 3 days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
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
    }
}

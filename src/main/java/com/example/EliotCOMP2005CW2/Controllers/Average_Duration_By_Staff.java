package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.example.EliotCOMP2005CW2.Allocation;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Average_Duration_By_Staff {
    @GetMapping("/duration/{id}")
    public String getAverageDurationByStaff(@PathVariable int id) {
        String API_URL_Allocations = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";
        String answer = "Variable not set";

        try{
            Allocation[] allocations = getAllocations(API_URL_Allocations); //Gets all allocations

            List<Integer> admissionIDs = getAdmissionIdsFromEmployeeID(id,allocations);//Finds all admissions linked to staff ID

            if (admissionIDs.size()!=0){
                List<Double> stayDurations = collectStayDurations(admissionIDs); //Collects list of stay durations

                Double meanStayDuration = showAverageHours(stayDurations); //Calculate average stay in hours

                answer = "The average duration a patient stays for staff ID " + id + " is " + meanStayDuration + " hours.";

            }
            else {return "The Employer ("+id+") hasn't treated any patients yet, please try another ID";}


        }catch (IOException e){
            System.out.println("ERROR Something went wrong");
            answer = "ERROR Something went wrong";

        };
        return answer;
    }


    public Allocation[] getAllocations(String allocationURL){


        // apache http client gets the whole table
        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(allocationURL);

            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());

            // Make an array of gson objects from the JSon response using my model "Allocation"
            Gson gson = new Gson();
            Allocation[] allocations = gson.fromJson(responseBody, Allocation[].class);

            return allocations;
        }catch (Exception e){
            System.out.println("error connecting to the api to fetch the allocation table data");
            return new Allocation[0];
        }

    }
    public List<Integer> getAdmissionIdsFromEmployeeID(int employeeID, Allocation[] allocations) {
        List<Integer> admissionIDs = new ArrayList<>();
        for (Allocation allocation : allocations) {
            if (allocation.getEmployeeID() == employeeID) {
                admissionIDs.add(allocation.getAdmissionID());
            }
        }
        return admissionIDs;
    }


    public List<Double> collectStayDurations(List<Integer> admissionIDs) throws IOException {
        /*
            THis function takes a list of admissionIDS from the previous function and needs to be called in the constructor
           It does a for loop through the list performing a get by ID to the admissions endpoint to produce a list of stay duration in hours.

         */

        List<Double> stayDurations = new ArrayList<>(); // A list to store in hours how long the stays are in the admission bodies
        String API_URL_Admissions = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";

        //using a for loop during the apache get by id process
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        for (Integer admissionID : admissionIDs) {
            HttpGet request = new HttpGet(API_URL_Admissions + "/" + admissionID);
            request.addHeader("accept", "application/json");
            CloseableHttpResponse response = httpClient.execute(request);

            // GSON reads the response into its data type which i can extract data  using my model class admission.java
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            JsonParser jsonParser = new JsonParser();
            JsonElement admissionElement = jsonParser.parse(reader).getAsJsonObject();
            Gson gson = new Gson();
            Admission admission = gson.fromJson(admissionElement, Admission.class);

            double stayDurationInHours = calculateStayDurationHours(admission);
            stayDurations.add(stayDurationInHours);
        }

        return stayDurations;
    }


    public Double calculateStayDurationHours(Admission admission){
        //formatting the date so that stay duration is calculated
        LocalDateTime admissionDate = LocalDateTime.parse(admission.getAdmissionDate(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime dischargeDate = LocalDateTime.parse(admission.getDischargeDate(), DateTimeFormatter.ISO_DATE_TIME);

        //Using the java.time library to calculate the time span
        Duration duration = Duration.between(admissionDate, dischargeDate);
        //adjust to hours because the default is seconds and nanoseconds which would probably not pass the functional test.
        //because why would anyone need to know that?
        double stayDurationInHours = (double) duration.getSeconds() / (60 * 60);

        return stayDurationInHours;

    }


    public Double showAverageHours (List<Double> hours){
        double sum = 0.0;
        double meanStay = 0.0;

        if(hours.size()!=0){
            for (double period:hours) {
                sum+=period;
            }
            meanStay += sum/hours.size(); //average stay

        }


        return meanStay;
    }
}

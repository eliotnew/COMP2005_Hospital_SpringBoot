package com.example.EliotCOMP2005CW2;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Using Apache Http Client for Get Fetch
 * And using google's gson to handle the json objects
 * this document also uses a class called 'Allocation'
 * That has variables for each of the values in an object returned
 * via the JSON.
 *
 * From what i can see there only exists entries with employee id 4.
 * */

public class FindPatientByStaff {
    public List<Integer> findPatients(int employeeID) {

        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            // Make an array of gson objects from the JSon response
            Allocation[] allocations = gson.fromJson(responseBody, Allocation[].class);

            // list to store the patients by their admissionIDs
            List<Integer> admissionIDs = new ArrayList<>();

            // for Loop through each object in the array staff member given staff member in the object
            for (Allocation allocation : allocations) {
                if (allocation.getEmployeeID() == employeeID) {
                    // If the same, adds patient to list.
                    admissionIDs.add(allocation.getAdmissionID());
                }
            }

            // Return the list of patients that have the same staff member given.
            return admissionIDs;

        } catch (IOException e) {
            System.err.println("Error executing get request. " + e.getMessage());
            return null;
        }
    }
}

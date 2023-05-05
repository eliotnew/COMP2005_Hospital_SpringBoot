package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.*;
import com.example.EliotCOMP2005CW2.Allocation;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class Get_Staffs_Patients {

    @GetMapping("/getmypatients/{id}")
    public List<Integer> Get_Staffs_Patients(@PathVariable int id) {

        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";

        //Imported the apache http client from my orignal program to save time
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            // Make an array of gson objects from the JSon response using my model "Allocation"
            Allocation[] allocations = gson.fromJson(responseBody, Allocation[].class);

            // list to store the patients by their admissionIDs
            List<Integer> admissionIDs = new ArrayList<>();

            // for Loop through each object in the array staff member given staff member in the object
            for (Allocation allocation : allocations) {
                if (allocation.getEmployeeID() == id) {
                    // If the same, adds patient to list.
                    admissionIDs.add(allocation.getAdmissionID());
                }
            }

            // Return the list of patients that have the same staff member given.
            return admissionIDs;

        } catch (IOException e) {
            System.err.println("Error executing get request. " + e.getMessage());

            //I am returning a dummy value in case of a fail, it will return a list containing "-1"
            int size = 1;
            List<Integer> failList = new ArrayList<>(Collections.nCopies(size, -1));

            return failList;
        }


    }


}

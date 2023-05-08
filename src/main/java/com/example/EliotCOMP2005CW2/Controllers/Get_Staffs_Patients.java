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

import javax.swing.*;
import java.io.Console;
import java.io.IOException;
import java.util.*;

@RestController
public class Get_Staffs_Patients {

    @GetMapping("/getmypatients/{id}")
    public List<Integer> Get_Staffs_Patients(@PathVariable int id) {
        try {
            //Creates an array of models from a GET
            Allocation[] theAllocations = getAllocations();

            //Creates a List of Admission IDs
            List<Integer> patientsAdmissions = createAdmissionIDList(theAllocations, id);

            //Creates a List of Patients
            List<Integer> patientIDs = getPatientIDWithAdmissionID(patientsAdmissions);

            //Ensures that there are no duplicates in the list as there is a many to one relationship between admissions and patient.
            List<Integer> yourPatients = RemoveDuplicates(patientIDs);

            return yourPatients;
        }
        catch (Exception e){
            System.out.println("Went wrong!");
            List<Integer> failList = new ArrayList<>();
            failList.add(-999);
            return failList;
        }
    }
    public Allocation[] getAllocations(){
        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";

        // apache http client gets the whole table
        HttpClient client = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(url);

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

    public List<Integer> createAdmissionIDList(Allocation[] data , int id){

        // list to store the patients by their admissionIDs
        List<Integer> admissionIDs = new ArrayList<>();

        // for Loop through each object in the array staff member given staff member in the object
        for (Allocation allocation : data) {
            if (allocation.getEmployeeID() == id) {
                // If the same, adds patient to list.
                admissionIDs.add(allocation.getAdmissionID());
            }
        }

        // Return the list of patients that have the same staff member given.
        return admissionIDs;

    }

    public List<Integer> getPatientIDWithAdmissionID(List<Integer> admission_ids){

        //Using the list of admissionIds we can get the patientsId from the admission

        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions/";
        HttpClient client = HttpClientBuilder.create().build();

        List<Integer> patientIDs = new ArrayList<>();

        for (Integer admission_id :admission_ids) {
            //apache get by ID
            try {
                HttpGet request = new HttpGet(url + admission_id);
                HttpResponse response = client.execute(request);
                String responseBody = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();
                Admission admission = gson.fromJson(responseBody, Admission.class);
                //using the getter from my admission class i collect the patient ID
                int patientID = admission.getPatientID();
                patientIDs.add(patientID);

            }catch (Exception e){
                patientIDs.add(-999);
            }

        }
        return patientIDs;
    }

    public List<Integer> RemoveDuplicates(List<Integer> patient_ids){
        //I dont really understand how but apparently list to set to list can remove dupes
        Set<Integer> toSet = new LinkedHashSet<>(patient_ids);
        List<Integer> removedDupes = new ArrayList<>(toSet);

        return removedDupes;

    }


}

package com.example.EliotCOMP2005CW2.Controllers;

import com.example.EliotCOMP2005CW2.Admission;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class Busiest_Day_Controller {

    @GetMapping("/busiestday")
    public String busiestDay(){
        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {
            HttpResponse response = client.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();

            // Make an array of gson objects from the JSon response
            Admission[] admissions = gson.fromJson(responseBody, Admission[].class);

            // Hashmap that works as a tally chart counting days.
            Map<DayOfWeek, Integer> dayTally = new HashMap<>();

            // for looping each admission gson object , calculating day and tallying in hashmap.
            for (Admission admission : admissions) {

                LocalDate admission_Date = LocalDate.parse(admission.getAdmissionDate(), DateTimeFormatter.ISO_DATE_TIME);

                //conviniently java can do the hard work for me! :)
                DayOfWeek dayOfWeek = admission_Date.getDayOfWeek();

                //defaulting to 0 if the key isnt found, I increment the day by one in the tally chart (hashmap)
                dayTally.put(dayOfWeek, dayTally.getOrDefault(dayOfWeek, 0) + 1);
            }

            //bool to decide whether i return one or several days.
            boolean moreThanOne = false;
            String multiDays = " ";

            //The answer's number of tally scores
            int mostDays = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
            int numDaysWithHighestCount = 0;

            //a for loop iterating through the tally chart hash map that will let us know if we have multiple answers as well as create a big string containing n days
            for (Map.Entry<DayOfWeek, Integer> entry : dayTally.entrySet()) {
                if (entry.getValue() == mostDays) {
                    numDaysWithHighestCount++;
                    multiDays += entry.getKey().getDisplayName(TextStyle.FULL, Locale.UK);
                    multiDays += " ";
                }
            }
            //if we have multiple answers
            if (numDaysWithHighestCount > 1) {
                moreThanOne = true;
            }

            // calculates the busiest day from within the hashpmap by comparison.
            //This is an enum and not yet usable as string
            DayOfWeek busiestDay = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();


            if (moreThanOne == true) {
                return "There is a joint tie for busiest days: " + multiDays;
            } else {
                // Return the name of the busiest day (e.g. "Monday")
                String answer = busiestDay.getDisplayName(TextStyle.FULL, java.util.Locale.UK);

                return "Busiest day of the week for admissions is " + answer + " . ";
            }

        } catch (IOException e) {
            System.err.println(" error calling get request . " + e.getMessage());
            return "Something Went wrong.";
        }

    }
}

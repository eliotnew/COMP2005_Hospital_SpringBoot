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
        try {
            //Gets admissions
            Admission[] admissions = getAdmissionsFromApi();

            //creates a tally chart in a hasmap
            Map<DayOfWeek, Integer> dayTally = getDayTally(admissions);

            //determines how many days we will return if its one or several answers
            boolean moreThanOne = hasMultipleBusiestDays(dayTally);


            if(moreThanOne==true){
                String busiestDaysStr = determineAllBusyDays(dayTally);
                return "There is a joint Tie for busiest Admission days:"+busiestDaysStr;
            }
            else{
                String busiestDayStr = determineBusiestDay(dayTally);
                return "Busiest day of the week is "+busiestDayStr;
            }
        } catch (IOException e) {
            System.err.println(" error calling get request . " + e.getMessage());
            return "Something Went wrong.";
        }

    }

    private Admission[] getAdmissionsFromApi() throws IOException {
        // Uses the admissions model to build an array of admission gson objects

        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        String responseBody = EntityUtils.toString(response.getEntity());

        Gson gson = new Gson();
        Admission[] admissions = gson.fromJson(responseBody, Admission[].class);

        return admissions;
    }

    private Map<DayOfWeek, Integer> getDayTally(Admission[] admissions) {
        //Creates a hashmap of days of the week and how many tally scores

        Map<DayOfWeek, Integer> dayTally = new HashMap<>();

        for (Admission admission : admissions) {

            LocalDate admission_Date = LocalDate.parse(admission.getAdmissionDate(), DateTimeFormatter.ISO_DATE_TIME);

            //put a score for each day type into the hashmap
            DayOfWeek dayOfWeek = admission_Date.getDayOfWeek();
            dayTally.put(dayOfWeek, dayTally.getOrDefault(dayOfWeek, 0) + 1);
        }
        return dayTally;
    }

    private int getMostDays(Map<DayOfWeek, Integer> dayTally) {
        //returns the count of the most days
        return dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();
    }

    private boolean hasMultipleBusiestDays(Map<DayOfWeek, Integer> dayTally) {
        int mostDays = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        int numDaysWithHighestCount = 0;
        for (Map.Entry<DayOfWeek, Integer> entry : dayTally.entrySet()) {
            if (entry.getValue() == mostDays) {
                numDaysWithHighestCount++;
            }
        }

        return numDaysWithHighestCount > 1;
    }

    private String determineBusiestDay(Map<DayOfWeek, Integer> dayTally) {
        DayOfWeek busiestDay = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return busiestDay.getDisplayName(TextStyle.FULL, Locale.UK);
    }

    private String determineAllBusyDays(Map<DayOfWeek, Integer> dayTally){
        //Should only be called if there are multiple days
        String days = "";
        // checks if it has the same value as the joint modal day value
        int mostDays = dayTally.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue();

        for (Map.Entry<DayOfWeek, Integer> entry : dayTally.entrySet()) {
            if (entry.getValue() == mostDays) {

                days += entry.getKey().getDisplayName(TextStyle.FULL, Locale.UK);
                days += " ";
            }
        }
        return days;
    }
}

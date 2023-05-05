package com.example.EliotCOMP2005CW2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class EliotComp2005Cw2Application {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(EliotComp2005Cw2Application.class, args);

		System.out.println("Hello world!");
		//MyMenu frame = new MyMenu();

		FindPatientByStaff finder = new FindPatientByStaff();
		List<Integer> admissionIDs = finder.findPatients(4);
		System.out.println(admissionIDs);

		//Im not sure that the return types will be easy to test...
//		FindBusiestDay findDay = new FindBusiestDay();
//		String egg = findDay.findBusiestDay();
//		System.out.println(egg);

		FindWithin3DayDischarge shortStay = new FindWithin3DayDischarge();
		List<Integer> shortStays = shortStay.findWithin3DayDischarge();
		System.out.println("Admission who stayed within 3 days are: ");
		System.out.println(shortStays);


		averagePatientDurationByStaff myAverage = new averagePatientDurationByStaff(1);

	}

}

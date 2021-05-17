package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vungcung {

	public static void main(String[] args) {

		String testCurrDate = "08/05/2000";
		String testNewDate = "01/01/2007";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate currDate = LocalDate.parse(testCurrDate, formatter);
		LocalDate newDate = LocalDate.parse(testNewDate, formatter);

		System.out.println(newDate.isBefore(currDate));


//		System.out.println(currentDateString);
//		System.out.println(newDate);
//
//		System.out.println(newDate.isBefore(currDate));
//
//		System.out.println(currDate.getYear() - newDate.getYear());
//		System.out.println(currDate.getDayOfMonth() - newDate.getDayOfMonth());
//		System.out.println(currDate.getMonthValue() - newDate.getMonthValue());

//		System.out.println(monthPrevBtn.getText());
//		System.out.println(monthNextBtn.getText());
//		System.out.println(dayPrevBtn.getText());
//		System.out.println(dayNextBtn.getText());
//		System.out.println(yearPrevBtn.getText());
//		System.out.println(yearNextBtn.getText());
		
		
		int yearDiff = currDate.getYear() - newDate.getYear();
		yearDiff *= -1;
		System.out.println("yr " + yearDiff);
		System.out.println("day " + (currDate.getDayOfMonth() - newDate.getDayOfMonth()));
		System.out.println("month " + (currDate.getMonthValue() - newDate.getMonthValue()));

	}

}

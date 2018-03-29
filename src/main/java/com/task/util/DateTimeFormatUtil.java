package com.task.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatUtil {
	public static String DateAsDDMMYYYY(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//		System.out.println(sdf.format(d));
		String date = sdf.format(d); 
//		Date d1 = new Date(date);
//		System.out.println(d1);
		return date;
	}
	
	public static Date dateFromString(String dateAsString) {
		Date date = null;
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			date = format.parse(dateAsString);
//			System.out.println(date);
		} catch (Exception e) {
			System.out.println(e);
		}
		return date;
	}
	
	public static void main(String[] args) {
		DateTimeFormatUtil.dateFromString("10/10/2014");
	}
}

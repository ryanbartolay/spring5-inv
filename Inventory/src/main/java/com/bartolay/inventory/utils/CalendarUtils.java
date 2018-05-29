package com.bartolay.inventory.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CalendarUtils {

	private static final String CALENDARFORMAT = "yyyy-MM-dd";
	private static final String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat caledarFormat = new SimpleDateFormat(CALENDARFORMAT);;
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIMEFORMAT);
	
	private static final String TRANSACTION_DATEFORMAT = "dd/MM/YYYY"; 
	private static final SimpleDateFormat transaction_timeFormat = new SimpleDateFormat(TRANSACTION_DATEFORMAT);
	
	public static Date toDate(String date) throws ParseException {
		return caledarFormat.parse(date);
	}
	public Time toTime(String date) throws ParseException {
		return new Time(timeFormat.parse(date).getTime());
	}
	public Timestamp toTimestamp(String date) throws ParseException {
		return new Timestamp(timeFormat.parse(date).getTime());
	}
	public static String timestampToString(Timestamp timestamp) {
		return timeFormat.format(timestamp);
	}
	public static String dateToString(Date date) {
		return timeFormat.format(date);
	}

	public static String transactionTimeFormat(Timestamp timestamp) {
		return transaction_timeFormat.format(timestamp);
	}
	
}
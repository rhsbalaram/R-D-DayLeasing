package com.dayLeasing.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc

/**
 * The Class DateUtils.
 *
 * @author Balaram
 */
public class DateUtils {

	/** The date format. */
	static DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Return instant if notnull.
	 *
	 * @param date
	 *            the date
	 * @return the instant
	 */
	public static Instant returnInstantIfNotnull(Date date) {
		if (date != null) {
			Date javaDate = new Date(date.getTime());

			return javaDate.toInstant();
		} else
			return null;

	}

	/**
	 * Generate date from string.
	 *
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date generateDateFromString(String date) {
		if (date == null || date == "") {
			return null;
		}
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Generate string from date.
	 *
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String generateStringFromDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			Date javaDate = new Date(date.getTime());
			return dateFormat.format(javaDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getMidNightTime()
	{
		Calendar date = new GregorianCalendar();
		// reset hour, minutes, seconds and millis
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		Date time = date.getTime();
		return time;
	}

}

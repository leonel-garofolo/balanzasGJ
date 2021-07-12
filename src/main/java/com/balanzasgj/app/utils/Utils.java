package com.balanzasgj.app.utils;

import com.ibm.icu.text.SimpleDateFormat;
import org.apache.log4j.Logger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
	final static Logger logger = Logger.getLogger(Utils.class);

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_HOUR_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static Date convertToDate(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate convertoToLocalDate(Date dateToconvert) {
		return Instant.ofEpochMilli(dateToconvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static String convertDateToString(Date date, String format){
		final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static boolean isDebug() {
		return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString()
				.indexOf("-agentlib:jdwp") > 0;
	}



}

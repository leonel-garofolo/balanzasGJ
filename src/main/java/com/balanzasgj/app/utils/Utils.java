package com.balanzasgj.app.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
	
	public static Date convertToDate(LocalDate dateToConvert) {
	    return java.util.Date.from(dateToConvert.atStartOfDay()
	      .atZone(ZoneId.systemDefault())
	      .toInstant());
	}

	public static LocalDate convertoToLocalDate(Date dateToconvert){
		return Instant.ofEpochMilli(dateToconvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static boolean isDebug(){
		return java.lang.management.ManagementFactory.getRuntimeMXBean().
			    getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	}
}

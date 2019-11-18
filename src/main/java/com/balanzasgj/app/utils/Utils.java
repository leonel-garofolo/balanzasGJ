package com.balanzasgj.app.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
	public static String PATH_MYSQL_INSTALLER = "C:\\mysql\\bin\\";
	
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
	
	public static void generarBackup(String path) {
		try {
			String command = PATH_MYSQL_INSTALLER + "mysqldump -u root sist_pesada";
			System.out.println(command);
			
			
			Process p = Runtime.getRuntime().exec(command);
			 InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream(path + "\\backup_.sql");
		      byte[] buffer = new byte[1000];

		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      }

		      fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void restaurarBackup(String path) {
		try {
			String command = PATH_MYSQL_INSTALLER + "mysqldump -u root sist_pesada < " + path;
			System.out.println(command);				
			Runtime.getRuntime().exec(command);				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

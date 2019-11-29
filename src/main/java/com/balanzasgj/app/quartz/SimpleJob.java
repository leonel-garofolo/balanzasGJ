package com.balanzasgj.app.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.balanzasgj.app.utils.Utils;

public class SimpleJob implements Job {
	private ParametrosGlobalesPersistence parametrosGoblalesPersistence;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();
		 System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());
		 
		 parametrosGoblalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		 ParametrosGlobales pg = new ParametrosGlobales();
		 pg.setId(ParametrosGlobales.P_EMPRESA_BACKUP);
		 parametrosGoblalesPersistence.load(pg);		
		 if(pg!= null) {
			 Utils.generarBackup(pg.getValue());
			 System.out.println("BACKUP GENERADO");
		 }
	}

}

package com.balanzasgj.app.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.balanzasgj.app.utils.Utils;

public class SimpleJob implements Job {
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();
		 System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());
		 
		 parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		 ParametrosGoblales pg = new ParametrosGoblales();
		 pg.setId(ParametrosGoblales.P_EMPRESA_BACKUP);
		 parametrosGoblalesPersistence.load(pg);		
		 if(pg!= null) {
			 Utils.generarBackup(pg.getValue());
			 System.out.println("BACKUP GENERADO");
		 }
	}

}

package com.balanzasgj.app.quartz;

import java.io.IOException;
import java.util.Date;

import com.balanzasgj.app.db.ProcessDB;
import com.balanzasgj.app.db.UtilDB;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.utils.Utils;

public class SimpleJob implements Job {
	final static Logger logger = Logger.getLogger(SimpleJob.class);
		
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 JobKey jobKey = context.getJobDetail().getKey();
		 logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
		 
		 final GlobalParameterService dao = new GlobalParameterService();
		 String pg = dao.get(GlobalParameter.P_EMPRESA_BACKUP);		 
		 if(!pg.isEmpty()) {
			 try {
				 ProcessDB.generarBackup(pg);
				logger.info("BACKUP GENERADO Hora: " + new Date());
			} catch (IOException e) {
				logger.error(e);
			}
		 }
	}
}

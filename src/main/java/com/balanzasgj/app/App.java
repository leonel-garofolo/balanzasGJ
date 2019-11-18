package com.balanzasgj.app;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.balanzasgj.app.quartz.SimpleJob;
import com.balanzasgj.app.utils.MiPrinterJob;

public class App {
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	
	public static final String PATH_ICONO = "images/icono/icono.jpg";
	@SuppressWarnings("static-access")
	private void iniciar(){
		GargareCollection gargare = new GargareCollection();
		gargare.start();
		
		String bkpAutmatico = "";
		parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_AUTOMATICO);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			bkpAutmatico = pg.getValue();
			
			if(bkpAutmatico != null && !bkpAutmatico.isEmpty()) {	
				String[] hm = bkpAutmatico.split(":");
				SchedulerFactory sf = new StdSchedulerFactory();
				try {
					Scheduler sched = sf.getScheduler();
					JobDetail job = JobBuilder.newJob(SimpleJob.class)
						    .withIdentity("job1", "group1")
						    .build();

					CronTrigger trigger = TriggerBuilder.newTrigger()
					    .withIdentity("trigger1", "group1")
					    .withSchedule(CronScheduleBuilder.cronSchedule("0 " + hm[1] + " " + hm[0] + " * * ?"))
					    .build();

					sched.scheduleJob(job, trigger);
					sched.start();
				} catch (SchedulerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}								
		}
		
		MiPrinterJob.preparedPrinter();
		AppClient.iniciar();		
	}
	
	public static void main(String[] args) throws Exception {
		App app = new App();
		app.iniciar();		
	}
}

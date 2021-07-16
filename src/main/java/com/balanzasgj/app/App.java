package com.balanzasgj.app;


import com.balanzasgj.app.db.UpdateDB;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.quartz.SimpleJob;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.utils.MiPrinterJob;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class App {
	final static Logger logger = Logger.getLogger(App.class);
	public static final String STYLE_CSS = "styles/main.css";
	public static final String APP_NAME = "Sistemas de Balanzas v1.31";
	private GlobalParameterService globalParameterService;
	
	public static final String PATH_ICONO = "images/icono/fullserivices.jpg";

	
	private void iniciar(){				
		logger.debug("Debug Message Logged !!!");
		logger.info("Info Message Logged !!!");
		GargareCollection gargare = new GargareCollection();
		gargare.start();
		
		globalParameterService = new GlobalParameterService();
		String bkpAutmatico = globalParameterService.get(GlobalParameter.P_EMPRESA_AUTOMATICO);
		
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
			} catch (ArrayIndexOutOfBoundsException | SchedulerException e) {
				logger.error(e);
			}
			
		}	
		
		UpdateDB updateDb = new UpdateDB();
		updateDb.run();
		MiPrinterJob.preparedPrinter();

		AppClient.iniciar();
	}
	
	public static void main(String[] args) throws Exception {	
		App app = new App();		
		app.iniciar();		
	}
}

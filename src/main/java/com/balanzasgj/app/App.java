package com.balanzasgj.app;

import com.balanzasgj.app.utils.MiPrinterJob;

public class App {
	@SuppressWarnings("static-access")
	private void iniciar(){
		GargareCollection gargare = new GargareCollection();
		gargare.start();
		
		MiPrinterJob.preparedPrinter();
		AppClient.iniciar();		
	}
	
	public static void main(String[] args) throws Exception {
		App app = new App();
		app.iniciar();		
	}
}

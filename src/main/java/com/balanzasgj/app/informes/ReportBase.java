package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import com.balanzasgj.app.services.RemitoFieldService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class ReportBase {
	final static Logger logger = Logger.getLogger(ReportBase.class);
	protected final Map<String, Object> parameter;
	protected JRDesignStyle textStyle;
	protected final JasperDesign jasperDesign;
	protected final PAGE_FORMAT page;

	protected JasperPrint jp;
	protected JasperReport jr;
	protected Map params = new HashMap();
	protected DynamicReport dr;

	public enum PAGE_FORMAT{
		A4("A4", 595,842),
		A4_LANDSCAPE("A4_landscape", 842, 595),
		A5("A5", 570, 595),
		A5_LANDSCAPE("A5_landscape", 595, 421),
		CARTA("CARTA", 226, 226);
		
		public String label;
		public int with;		
		public int height;
		

	    PAGE_FORMAT(String label, int with, int height) {
	    	this.label = label;
	        this.with = with;
	        this.height = height;
	    }
	}

	public enum Unit{
		cm,
		px
	}

	public ReportBase(PAGE_FORMAT page, Map<String, Object> parameter) {
		this.parameter = parameter;
		this.jasperDesign = new JasperDesign();
		if(page == null)
			page = PAGE_FORMAT.A4;
		this.page = page;
		setup();
	}

	private void setup(){
		jasperDesign.setPageWidth(page.with);
		jasperDesign.setPageHeight(page.height);

		Iterator it = parameter.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			JRDesignParameter param = new JRDesignParameter();
			param.setName(String.valueOf(pair.getKey()));
			param.setValueClass(String.class);
			JRDesignExpression expression = new JRDesignExpression();
			param.setDefaultValueExpression(expression);
			try {
				jasperDesign.addParameter(param);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract DynamicReport buildReport() throws Exception;
	
	public abstract JRDataSource getDataSource();
 
	public void generateReport() throws Exception {
 			
		dr = buildReport();
  
		/**
		 * Ontenemos la fuente de datos en base a una colleccion de objetos
		 */
  		JRDataSource ds = getDataSource();
  
	  	/**
		 * Creamos el objeto JasperReport que pasamos como parametro a 
		 * DynamicReport,junto con una nueva instancia de ClassicLayoutManager 
		 * y el JRDataSource
		 */
  		jr = DynamicJasperHelper.generateJasperReport(dr, getLayoutManager(), params);
  
	  	/**
	  	 * Creamos el objeto que imprimiremos pasando como parametro
		 * el JasperReport object, y el JRDataSource
		 */
		logger.debug("Filling the report");
  		if (ds != null){
  			jp = JasperFillManager.fillReport(jr, params, ds);
  		}else{
  			jp = JasperFillManager.fillReport(jr, params);
			logger.debug("Filling done!");
			logger.debug("Exporting the report (pdf, xls, etc)");
  		}
  			
  		exportReport();

		logger.debug("test finished");
		 
	}

	public abstract void build() throws Exception;
 
	protected LayoutManager getLayoutManager() {
		return new ClassicLayoutManager();
	}
 
	
	
	protected void exportReport() throws Exception {
 
			final String path=System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName()+ ".pdf";

		logger.debug("Exporing report to: " + path);
			JRPdfExporter exporter = new JRPdfExporter();
			File outputFile = new File(path);
			File parentFile = outputFile.getParentFile();
			if (parentFile != null)
			  			parentFile.mkdirs();
			
	  		FileOutputStream fos = new FileOutputStream(outputFile);
			  
	  		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
	  		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
	  
	  		exporter.exportReport();
		logger.debug("Report exported: " + path);
			
	}
		 	
	
	protected void exportToJRXML() throws Exception {
		if (this.jr != null){
			DynamicJasperHelper.generateJRXML(this.jr, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName() + ".jrxml");
			
		} else {
			DynamicJasperHelper.generateJRXML(this.dr, this.getLayoutManager(), this.params, "UTF-8",System.getProperty("user.dir")+ "/target/reports/" + this.getClass().getCanonicalName() + ".jrxml");
		}
	}

	public void setTextStyle(JRDesignStyle textStyle) {
		this.textStyle = textStyle;
	}

	protected JRDesignStaticText staticText(Unit unit, double x, double y, Object text){
		final JRDesignStaticText staticText = new JRDesignStaticText();
		switch (unit){
			case cm:
				staticText.setX(toCM(x));
				staticText.setY(toCM(y));
				break;
			case px:
				staticText.setX(new Double(x).intValue());
				staticText.setY(new Double(y).intValue());
				break;
		}
		if(this.textStyle != null)
			staticText.setStyle(this.textStyle);
		staticText.setWidth(100);
		staticText.setHeight(20);
		staticText.setText(text == null? "": text.toString());
		return staticText;
	}

	protected JRDesignStaticText staticText(Unit unit, JRDesignStyle textStyle, double x, double y, Object text){
		JRDesignStaticText staticText = this.staticText(unit, x, y, text);
		staticText.setStyle(textStyle);
		return staticText;
	}

	protected JRDesignStyle style(String name, String font, int size){
		JRDesignStyle textStyle = new JRDesignStyle();
		textStyle.setName(name);
		textStyle.setFontName(font);
		textStyle.setFontSize(Integer.valueOf(size).floatValue());
		return textStyle;
	}
	private int toCM(double pixel) {
		return new Double(( 96 / 2.54 ) * pixel).intValue();
	}

	public boolean show(){
		JasperReport report;
		try {
			logger.info("report dimention:  width: " + this.jasperDesign.getPageWidth() + " | height: " + this.jasperDesign.getPageHeight());
			report = JasperCompileManager.compileReport(this.jasperDesign);

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap(), getDataSource());
			JasperViewer.viewReport(jasperPrint, false);
			return true;
		} catch (JRException e) {
			e.printStackTrace();
			return false;
		}
	}
}
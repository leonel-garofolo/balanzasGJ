package com.balanzasgj.app.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Report;
import com.balanzasgj.app.persistence.impl.jdbc.commons.DataSourceProvider;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ShowJasper {
	final static Logger logger = Logger.getLogger(ShowJasper.class);

    public static void open(String document, HashMap<String, Object>  params) throws JRException {
        DataSource dataSource = DataSourceProvider.getDataSource() ;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }

        if(connection != null){
            InputStream file = ShowJasper.class.getResourceAsStream("/jrxml/" + document + ".jrxml");
            JasperReport report = JasperCompileManager.compileReport(file);
            if(params == null){
                params = new HashMap<String,Object>();
            }

            JasperPrint printedReport = JasperFillManager.fillReport(report, params, connection);
            JasperViewer viewer = new JasperViewer(printedReport, false);
            viewer.setVisible(true);
        }
    }
    
    public static void openBeanDataSource(String document, HashMap<String, Object>  params, JRBeanCollectionDataSource source) throws JRException {
        DataSource dataSource = DataSourceProvider.getDataSource() ;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
        }

        if(connection != null){
            InputStream file = ShowJasper.class.getResourceAsStream("/jrxml/" + document + ".jrxml");
            JasperReport report = JasperCompileManager.compileReport(file);
            if(params == null){
                params = new HashMap<String,Object>();
            }

            JasperPrint printedReport = JasperFillManager.fillReport(report, params, source);
            JasperViewer viewer = new JasperViewer(printedReport, false);
            viewer.setVisible(true);
        }
    }
    
    public static String getReportCopy(Report report) {
    	String reportCopy = "";
		switch (report.getCount()) {
		case 1:
			reportCopy = "ORIGINAL";
			break;
		case 2:
			reportCopy = "DUPLICADO";
			break;
		case 3:
			reportCopy = "TRIPLICADO";
			break;
		case 4:
			reportCopy = "CUATRIPLICADO";
			break;
		case 5:
			reportCopy = "QUINTUPLICADO";
			break;
		case 6:
			reportCopy = "SEXTUPLICADO";
			break;
		case 7:
			reportCopy = "SEPTUPLICADO";
			break;
		case 8:
			reportCopy = "OCTUPLICADO";
			break;		

		default:
			reportCopy = "ORIGINAL";
			break;
		}
		
		return reportCopy;
    }

    public static void main(String[] arts){
        try {
            ShowJasper.open("transacciones", null);
        } catch (JRException e) {
            logger.error(e);
        }
    }
}

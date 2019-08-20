package com.balanzasgj.app.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import com.balanzasgj.app.persistence.impl.jdbc.commons.DataSourceProvider;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ShowJasper {

    public static void open(String document, HashMap<String, Object>  params) throws JRException {
        DataSource dataSource = DataSourceProvider.getDataSource() ;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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

    public static void main(String[] arts){
        try {
            ShowJasper.open("transacciones", null);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}

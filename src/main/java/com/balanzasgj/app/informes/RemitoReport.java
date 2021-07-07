package com.balanzasgj.app.informes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.balanzasgj.app.model.RemitoField;

import ar.com.fdvs.dj.domain.DynamicReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

public class RemitoReport extends ReportBase{
	private final Map<String, String> data;
	private final List<RemitoField> fields;
	private final JasperDesign jasperDesign;
	
    public RemitoReport(List<RemitoField> fields, Map<String, String> data){    	
        this.fields = fields;
        this.data = data;
        this.jasperDesign = new JasperDesign();
    }
    	
	public JasperDesign build() throws Exception {        
        jasperDesign.setName("The dynamically generated report");        
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setLeftMargin(0);
        jasperDesign.setTopMargin(0);

        //Detail
        JRDesignBand band = new JRDesignBand();
        band.setHeight(400);
        
        for (RemitoField f : fields) {
        	JRDesignStaticText staticText = new JRDesignStaticText();
        	int x = toCM(Integer.valueOf(f.getPosX()));        	
        	int y = toCM(Integer.valueOf(f.getPosY()));
            staticText.setX(x);
            staticText.setY(y);
            staticText.setWidth(100);
            staticText.setHeight(20);            
            staticText.setText(data.containsKey(f.getDato())? data.get(f.getDato()): "");
            band.addElement(staticText);
		}
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
        jasperDesign.setPageHeader(null);
        return jasperDesign;
    }

    
    public JRDataSource getDataSource() {
        JRDataSource ds = new JREmptyDataSource();
        return ds;
    }

    public void show(){
    	JasperReport report;
		try {
			report = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap(), getDataSource());    	    
	        JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {			
			e.printStackTrace();
		}    	
    }

	@Override
	public DynamicReport buildReport() throws Exception {
		return null;
	}
	
	private int toCM(double pixel) {
		return new Double(( 96 / 2.54 ) * pixel).intValue(); 
	}
}

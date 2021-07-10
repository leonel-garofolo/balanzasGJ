package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.domain.DynamicReport;
import com.balanzasgj.app.model.RemitoField;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignSection;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RemitoReport extends ReportBase{

    protected final List<RemitoField> fields;

    public RemitoReport(PAGE_FORMAT page, Map<String, Object> parameter, List<RemitoField> fields){
        super(page, parameter);
        this.fields = fields;
    }
    	
	public void build() throws Exception {
        final SimpleDateFormat dtFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
        jasperDesign.setName("remito" + "_" + dtFormat.format(new Date()));
        jasperDesign.setLeftMargin(0);
        jasperDesign.setTopMargin(0);

        //Detail
        JRDesignBand band = new JRDesignBand();
        band.setHeight((new Double(page.height - (page.height * 0.04))).intValue());        
        for (RemitoField f : fields) {
            band.addElement(staticText(Unit.cm, Double.valueOf(f.getPosX().replaceAll(",", ".")), Double.valueOf(f.getPosY().replaceAll(",", ".")), parameter.containsKey(f.getDato())? String.valueOf(parameter.get(f.getDato())): ""));
		}
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
        jasperDesign.setPageHeader(null);
    }

    
    public JRDataSource getDataSource() {
        JRDataSource ds = new JREmptyDataSource();
        return ds;
    }

	@Override
	public DynamicReport buildReport() throws Exception {
		return null;
	}
}

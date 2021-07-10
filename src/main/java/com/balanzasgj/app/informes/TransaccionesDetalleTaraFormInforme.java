package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.balanzasgj.app.model.Tare;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TransaccionesDetalleTaraFormInforme extends ReportBase{
    private List<Tare> taras;
    public TransaccionesDetalleTaraFormInforme(List<Tare> taras){
        super(null, null);
        this.taras = taras;
    }

    @Override
    public DynamicReport buildReport() throws Exception {
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle("taras");
        return drb.build();
    }

    @Override
    public JRDataSource getDataSource() {
        JRDataSource ds = new JRBeanCollectionDataSource(taras);
        return ds;
    }

    @Override
    public void build() throws Exception {

    }

    public void show(){

        JasperViewer.viewReport(this.jp, false);
    }
}

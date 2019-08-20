package com.balanzasgj.app.informes;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.SubReportBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.Subreport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TransaccionesDetalleInforme extends ReportBase{
    private List<Taras> taras;
    public TransaccionesDetalleInforme(List<Taras> taras){
        this.taras = taras;
    }

    public static void main(String[] args){
        TarasPersistence tarasPersistence = new TarasPersistenceJdbc();
        TransaccionesDetalleInforme inf = new TransaccionesDetalleInforme(tarasPersistence.findAll());
        try {
            inf.generateReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
        inf.show();

    }

    @Override
    public DynamicReport buildReport() throws Exception {
        DynamicReportBuilder drb = new DynamicReportBuilder();
        drb.setTitle("TEST");
        drb.setSubtitle("This is a report with many concatenated subreports which should start in a new page");
        drb.setWhenNoDataAllSectionNoDetail();

        /*
        for (int i = 0; i < 3; i++) {
            FastReportTest report = new FastReportTest();

            try {
                report.dr = report.buildReport();
                report.dr.setTitle("REPORT N°: " + i);
                report.dr.setWhenNoDataType(DJConstants.WHEN_NO_DATA_TYPE_ALL_SECTIONS_NO_DETAIL);

                String dataSourcePath = "DataSource" + i;
                JRDataSource dataSource = report.getDataSource();
                params.put(dataSourcePath, dataSource);

                Subreport subreport = new SubReportBuilder()
                        .setStartInNewPage(true)
                        .setDataSource(DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_JRDATASOURCE, dataSourcePath)
                        .setDynamicReport(report.dr,  new ClassicLayoutManager())
                        .build();

                drb.addConcatenatedReport(subreport);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
         */

        for(Taras tara: taras){

        }
       
        return drb.build();
    }

    @Override
    public JRDataSource getDataSource() {
        JRDataSource ds = new JRBeanCollectionDataSource(taras);
        return ds;
    }

    public void show(){

        JasperViewer.viewReport(this.jp, false);
    }
}

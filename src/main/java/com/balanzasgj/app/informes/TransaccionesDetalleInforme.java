package com.balanzasgj.app.informes;

import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class TransaccionesDetalleInforme extends ReportBase{
	final static Logger logger = Logger.getLogger(TransaccionesDetalleInforme.class);
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
            logger.error(e);
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
                logger.error(e);
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

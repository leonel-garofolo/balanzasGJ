package com.balanzasgj.app.services;

import com.balanzasgj.app.informes.csv.ExportTaraCsv;
import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.model.Reports;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ReportsPersistence;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ReportsPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import com.balanzasgj.app.utils.ShowJasper;
import com.balanzasgj.app.view.InformesController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportService implements  Runnable {
    final static Logger logger = Logger.getLogger(ReportService.class);

    private ParamConfigurationService paramConfigurationService;
    private TarasPersistence tarasPersistence;
    private ReportsPersistence reportsPersistence;
    private HashMap<String, Object> params;

    private REPORT modeReport;
    private enum REPORT {
        EXPORT_CSV,
        TICKET
    };

    public ReportService(REPORT modeReport, HashMap<String, Object> params){
        this.paramConfigurationService = new ParamConfigurationService();
        this.tarasPersistence = new TarasPersistenceJdbc();
        this.reportsPersistence = new ReportsPersistenceJdbc();
        this.modeReport = modeReport;
        this.params = params;
    }

    public static void exportCsv(){
        runReport(REPORT.EXPORT_CSV, new HashMap<>());
    }

    public static void ticket(String modalidad, long idTaras){
        final HashMap<String, Object> params = new HashMap<>();
        params.put("idtaras", idTaras);
        params.put("modalidad", modalidad);
        runReport(REPORT.TICKET, params);
    }

    private static void runReport(REPORT mode, HashMap<String, Object> params){
        ReportService reportService = new ReportService(mode, params);
        Thread t = new Thread(reportService);
        t.run();
    }

    @Override
    public void run() {
        switch (this.modeReport){
            case TICKET:
                buildTicket();
                break;
            case EXPORT_CSV:
                buildExportCsv();
                break;
        }
    }

    private void buildExportCsv(){
        final String path = "C:\\SistemaDePesaje\\csv\\";
        final String fileName = "prueba.csv";
        List<Taras> tarasList = this.tarasPersistence.findAll();
        final ExportTaraCsv exportTaraCsv = ExportTaraCsv
                .newBuild()
                .setTaras(tarasList)
                .setPath(path)
                .setNameFile(fileName)
                .build();

        exportTaraCsv.export();
    }

    private void buildTicket(){
        List<Taras> taras = new ArrayList<>();
        taras.add(tarasPersistence.findById((long)params.get("idtaras")));
        params.put(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_DIR_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_DIR_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_TEL_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_TEL_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_EMAIL_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_EMAIL_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_LOC_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_LOC_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_PROV_BAL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_PROV_BAL));
        params.put(ParametrosGlobales.P_EMPRESA_NOMBRE, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_NOMBRE));
        params.put(ParametrosGlobales.P_EMPRESA_DIR, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_DIR));
        params.put(ParametrosGlobales.P_EMPRESA_TEL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_TEL));
        params.put(ParametrosGlobales.P_EMPRESA_EMAIL, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_EMAIL));
        params.put(ParametrosGlobales.P_EMPRESA_LOC, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_LOC));
        params.put(ParametrosGlobales.P_EMPRESA_PROV, paramConfigurationService.get(ParametrosGlobales.P_EMPRESA_PROV));
        params.put("USUARIO", Usuarios.getUsuarioLogeado());


        /* Aduana */
        final String modalidad = (String) params.get("modalidad");
        if (modalidad != null
                && modalidad.equals(Taras.MODO.M_ADUANA.label)) {
            params.put(ParametrosGlobales.A_CODIGO_ADUANA, paramConfigurationService.get(ParametrosGlobales.A_CODIGO_ADUANA));
            params.put(ParametrosGlobales.A_CODIGO_LOG, paramConfigurationService.get(ParametrosGlobales.A_CODIGO_LOG));
            params.put(ParametrosGlobales.A_CERTIFICADO, paramConfigurationService.get(ParametrosGlobales.A_CERTIFICADO));
            params.put(ParametrosGlobales.A_VENCIMIENTO, paramConfigurationService.get(ParametrosGlobales.A_VENCIMIENTO));
        }

        final Blob pEmpresaImg = paramConfigurationService.getBlob(ParametrosGlobales.P_EMPRESA_IMG);
        if (pEmpresaImg != null) {
            try {
                byte[] img = new byte[new Long(pEmpresaImg.length()).intValue()];
                Image image = ImageIO.read(new ByteArrayInputStream(img));

                params.put(ParametrosGlobales.P_EMPRESA_IMG, image);
            } catch (SQLException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            }
        } else {
            params.put(ParametrosGlobales.P_EMPRESA_IMG, null);
        }
        try {
            if (modalidad != null
                    && modalidad.equals(Taras.MODO.M_ADUANA.label)) {
                updateReportCount(params);

                ShowJasper.openBeanDataSource(InformesController.TICKET_ADUANA, params, new JRBeanCollectionDataSource(taras));
            } else {
                final boolean ticketEt = Boolean.valueOf(paramConfigurationService.get(ParametrosGlobales.P_TICKET_ETIQUETADORA));
                updateReportCount(params);
                if (ticketEt) {
                    ShowJasper.openBeanDataSource("ticketEtiquetadora", params,
                            new JRBeanCollectionDataSource(taras));
                } else {
                    ShowJasper.openBeanDataSource("ticket", params, new JRBeanCollectionDataSource(taras));
                }
            }
        } catch (JRException e) {
            logger.error(e);
        }
    }

    private void updateReportCount(HashMap<String, Object> params) {
        final long idTaras = (long)params.get("idtaras");
        Reports report =  reportsPersistence.fintByTaraId(idTaras);
        if(report == null){
            report = new Reports();
            report.setTaraId(idTaras);
            report.setCount(1);
            reportsPersistence.save(report);
        } else {
            report.setCount(report.getCount() + 1);
            reportsPersistence.save(report);
        }
        params.put(ParametrosGlobales.P_REPORT_COPY, ShowJasper.getReportCopy(report));
    }
}

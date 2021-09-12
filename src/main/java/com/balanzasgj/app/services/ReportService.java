package com.balanzasgj.app.services;

import com.balanzasgj.app.informes.*;
import com.balanzasgj.app.informes.ReportBase.PAGE_FORMAT;
import com.balanzasgj.app.informes.csv.ExportTaraCsv;
import com.balanzasgj.app.informes.model.RemitoFieldType;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.Report;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.model.User;
import com.balanzasgj.app.persistence.ReportDao;
import com.balanzasgj.app.persistence.impl.jdbc.ReportDaoImpl;
import com.balanzasgj.app.utils.ShowJasper;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService implements  Runnable {
    final static Logger logger = Logger.getLogger(ReportService.class);

    private GlobalParameterService paramConfigurationService;
    private RemitoFieldService remitoFieldService;
    private TareService tareService;
    private ReportDao reportDao;
    private HashMap<String, Object> params;

    private REPORT modeReport;

    private enum REPORT {
        EXPORT_CSV,
        TICKET,
        TICKET_PRE_PRINT,
        REMITO,
        TARE,
        TARE_DETALLE,
    };

    public ReportService() {
    	 this.paramConfigurationService = new GlobalParameterService();
         this.tareService = new TareService();
         this.remitoFieldService = new RemitoFieldService();
         try {			
 			this.reportDao = new ReportDaoImpl();
 		} catch (SQLException e) {
 			logger.error(e);
 		}
    }
    
    public void exportCsv(String fileName){
    	this.params = new HashMap<>();
    	this.params.put("fileName", fileName);
        runReport(REPORT.EXPORT_CSV);
    }

    public void ticket(String modalidad, long idTaras){
        this.params = new HashMap<>();
        this.params.put("idtaras", idTaras);
        this.params.put("modalidad", modalidad);
        runReport(REPORT.TICKET);
    }
    
    public void remito(long idTaras) {
    	this.params = new HashMap<>();
    	this.params.put("idtaras", idTaras);
        runReport(REPORT.REMITO);
    }

    public void tare(ObservableList<Tare> items) {
    	this.params = new HashMap<>();
    	this.params.put("tares", items);
        runReport(REPORT.TARE);
    }

    public void tareDetalle(ObservableList<Tare> items) {
    	this.params = new HashMap<>();
    	this.params.put("tares", items);
        runReport(REPORT.TARE_DETALLE);
    }

    private  void runReport(REPORT mode){
    	this.modeReport = mode;    	
        Thread t = new Thread(this);
        t.run();
    }

    @Override
    public void run() {
        switch (this.modeReport){
        	case TICKET_PRE_PRINT:
        	case TICKET:
                buildTicket(this.modeReport);
                break;           
            case EXPORT_CSV:
                buildExportCsv();
                break;
            case REMITO:
            	buildRemito();
                break;
            case TARE:
                buildTare();
                break;
            case TARE_DETALLE:
                buildTareDetalle();
                break;
        }
    }

    private void buildExportCsv(){
        final String path = paramConfigurationService.get(GlobalParameter.P_CSV_EXPORT_PATH);
        if(path.isEmpty())
            return;
        final String userWin = paramConfigurationService.get(GlobalParameter.P_USER_WINDOWS);
        final String passWind = paramConfigurationService.get(GlobalParameter.P_PASS_WINDOWS);
        final String fileName = (String) params.get("fileName");
        List<Tare> tarasList = this.tareService.findAll();
        final ExportTaraCsv exportTaraCsv = ExportTaraCsv
                .newBuild()
                .setTaras(tarasList)
                .setPath(path)
                .setNameFile(fileName)
                .build();

        exportTaraCsv.export();
    }

    private void buildTicket(REPORT modeReport){
        List<Tare> taras = new ArrayList<>();
        taras.add(tareService.findById((long)params.get("idtaras")));
        params.put(GlobalParameter.P_EMPRESA_NOMBRE_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL));
        params.put(GlobalParameter.P_EMPRESA_DIR_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR_BAL));
        params.put(GlobalParameter.P_EMPRESA_TEL_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL_BAL));
        params.put(GlobalParameter.P_EMPRESA_EMAIL_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_EMAIL_BAL));
        params.put(GlobalParameter.P_EMPRESA_LOC_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC_BAL));
        params.put(GlobalParameter.P_EMPRESA_PROV_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV_BAL));
        params.put(GlobalParameter.P_EMPRESA_NOMBRE, paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE));
        params.put(GlobalParameter.P_EMPRESA_DIR, paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR));
        params.put(GlobalParameter.P_EMPRESA_TEL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL));
        params.put(GlobalParameter.P_EMPRESA_EMAIL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_EMAIL));
        params.put(GlobalParameter.P_EMPRESA_LOC, paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC));
        params.put(GlobalParameter.P_EMPRESA_PROV, paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV));
        params.put("USUARIO", User.getUsuarioLogeado());


        /* Aduana */
        final String modalidad = (String) params.get("modalidad");
        if (modalidad != null
                && modalidad.equals(Tare.MODO.M_ADUANA.label)) {
            params.put(GlobalParameter.A_CODIGO_ADUANA, paramConfigurationService.get(GlobalParameter.A_CODIGO_ADUANA));
            params.put(GlobalParameter.A_CODIGO_LOG, paramConfigurationService.get(GlobalParameter.A_CODIGO_LOG));
            params.put(GlobalParameter.A_CERTIFICADO, paramConfigurationService.get(GlobalParameter.A_CERTIFICADO));
            params.put(GlobalParameter.A_VENCIMIENTO, paramConfigurationService.get(GlobalParameter.A_VENCIMIENTO));
        }

        final Blob pEmpresaImg = paramConfigurationService.getBlob(GlobalParameter.P_EMPRESA_IMG);
        if (pEmpresaImg != null) {
            try {
                byte[] img = new byte[new Long(pEmpresaImg.length()).intValue()];
                Image image = ImageIO.read(new ByteArrayInputStream(img));

                params.put(GlobalParameter.P_EMPRESA_IMG, image);
            } catch (SQLException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            }
        } else {
            params.put(GlobalParameter.P_EMPRESA_IMG, null);
        }

        updateReportCount(params);
        ReportBase report = null;
        if (modalidad != null
                && modalidad.equals(Tare.MODO.M_ADUANA.label)) {
            report = new TicketAduana(PAGE_FORMAT.A4, params, taras);
        } else {
            final String typeTicket = paramConfigurationService.get(GlobalParameter.P_TICKET_ETIQUETADORA);
            boolean ticketEt = false;
            if(typeTicket.equals(GlobalParameter.TYPE_TICKET.FORMATO_ETIQUETADORA.label))
                ticketEt = true;
            else if(typeTicket.equals(GlobalParameter.TYPE_TICKET.NORMAL.label) || (typeTicket.equals(GlobalParameter.TYPE_TICKET.REMITO.label)))
                ticketEt = false;
            else {
                try {
                    ticketEt= Boolean.valueOf(typeTicket);
                }catch (Exception e) {
                }
            }

            if (ticketEt) {
                report = new TicketPrinter(PAGE_FORMAT.CARTA, params, taras);
            } else {
                report = new Ticket(RemitoReport.PAGE_FORMAT.A4, params, taras);
            }
        }
        try {
            report.build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        report.show();
    }
    
    private void buildRemito() {
    	Tare tare =tareService.findById((long)params.get("idtaras"));
    	String remitoPageFormat = paramConfigurationService.get(GlobalParameter.P_REMITO_PAGE_FORMAT);
    	PAGE_FORMAT page = RemitoReport.PAGE_FORMAT.A4;
    	if(!remitoPageFormat.isEmpty()) {
    		if(!page.label.equals(remitoPageFormat))
    			page =  RemitoReport.PAGE_FORMAT.A5;
    	}
    	
    	Map<String, Object> data = new HashMap();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        data.put(RemitoFieldType.FECHA.label, dateFormat.format(tare.getFechaEntrada()));
		data.put(RemitoFieldType.DENOMINACION.label, (tare.getCliente() != null && tare.getCliente().getDenominacion() != null)? tare.getCliente().getDenominacion():"");
		data.put(RemitoFieldType.DOMICILIO.label, (tare.getCliente() != null && tare.getCliente().getDireccion() != null)? tare.getCliente().getDireccion():"");
		data.put(RemitoFieldType.LOCALIDAD.label, (tare.getCliente() != null && tare.getCliente().getLocalidad() != null)? tare.getCliente().getLocalidad():"");
		data.put(RemitoFieldType.PROVINCIA.label, (tare.getCliente() != null && tare.getCliente().getProvincia() != null)? tare.getCliente().getProvincia():"");
		data.put(RemitoFieldType.CUIT.label, (tare.getCliente() != null && tare.getCliente().getCuit() != null)? tare.getCliente().getCuit():"");
		data.put(RemitoFieldType.CONDUCTOR.label, (tare.getConductor() != null)? tare.getConductor():"");
        data.put(RemitoFieldType.CONDUCTOR_DNI.label, (tare.getNumDoc() != null)? tare.getNumDoc():"");
        data.put(RemitoFieldType.CHASIS.label,  (tare.getPatente() != null && tare.getPatente().getCodigo() != null? tare.getPatente().getCodigo():""));
		data.put(RemitoFieldType.ACOPLADO.label,  (tare.getPatenteAceptado() != null)? tare.getPatenteAceptado():"");
        data.put(RemitoFieldType.TRANSPORTE.label,  (tare.getTransporte() != null && tare.getTransporte().getNombre() != null? tare.getTransporte().getNombre():""));
        data.put(RemitoFieldType.PRODUCTO.label,  (tare.getProducto() != null && tare.getProducto().getNombre() != null? tare.getProducto().getNombre():""));
		data.put(RemitoFieldType.PESO_ENTRADA.label,  tare.getPesoEntrada()!= null ? tare.getPesoEntrada().toString(): "");
		if(tare.getPesoSalida() != null)
		    data.put(RemitoFieldType.PESO_SALIDA.label, tare.getPesoSalida() != null ? tare.getPesoSalida().toString(): "");
        if(tare.getPesoNeto() != null)
		    data.put(RemitoFieldType.PESO_NETO.label, tare.getPesoNeto()!= null ? tare.getPesoNeto().toString(): "");
    	RemitoReport remito = new RemitoReport(page, data, remitoFieldService.findAll());
		try {
			remito.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		remito.show();
    }

    private void buildTare() {
        TransaccionesInforme informe = new TransaccionesInforme((List<Tare>) params.get("tares"));
        try {
            informe.generateReport();
            informe.show();
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private void buildTareDetalle(){
        /*PROPIETARIO DE LA BALANZA*/
        params.put(GlobalParameter.P_EMPRESA_NOMBRE_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL));
        params.put(GlobalParameter.P_EMPRESA_DIR_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR_BAL));
        params.put(GlobalParameter.P_EMPRESA_TEL_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL_BAL));
        params.put(GlobalParameter.P_EMPRESA_EMAIL_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_EMAIL_BAL));
        params.put(GlobalParameter.P_EMPRESA_LOC_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC_BAL));
        params.put(GlobalParameter.P_EMPRESA_PROV_BAL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV_BAL));

        /* EMPRESA*/
        params.put(GlobalParameter.P_EMPRESA_NOMBRE, paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE));
        params.put(GlobalParameter.P_EMPRESA_DIR, paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR));
        params.put(GlobalParameter.P_EMPRESA_TEL, paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL));
        params.put(GlobalParameter.P_EMPRESA_LOC, paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC));
        params.put(GlobalParameter.P_EMPRESA_PROV, paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV));
        params.put("USUARIO", User.getUsuarioLogeado());

        Blob pg = paramConfigurationService.getBlob(GlobalParameter.P_EMPRESA_IMG);
        if(pg != null) {
            try {
                byte[] img = new byte[new Long(pg.length()).intValue()];
                Image image = ImageIO.read(new ByteArrayInputStream(img));

                params.put(GlobalParameter.P_EMPRESA_IMG, image);
            } catch (SQLException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            }
        }

        try {
            ShowJasper.openBeanDataSource("transaccionesDetalles", params, new JRBeanCollectionDataSource((List<Tare>) params.get("tares")));
        } catch (JRException e) {
            logger.error(e);
        }
    }

    private void updateReportCount(HashMap<String, Object> params) {
        final long idTaras = (long)params.get("idtaras");       
        Report report = getReportByTaraId(idTaras);
        if(report == null){
            report = new Report();
            report.setTaraId(idTaras);
            report.setCount(1);
            reportDao.save(report);
        } else {
            report.setCount(report.getCount() + 1);
            reportDao.save(report);
        }
        params.put(GlobalParameter.P_REPORT_COPY, ShowJasper.getReportCopy(report));
    }
    
    public Report getReportByTaraId(long idTare) {
    	 List<Report> reports= new ArrayList<Report>(); 		
    	try {
			reports = reportDao.queryForEq("tara_id", idTare);
			if(!reports.isEmpty()) {
				return reports.get(0);
			}
		} catch (SQLException e) {
			logger.error(e);
		}
    	return null; 
    }

	public void save(Report report) {
		this.reportDao.save(report);		
	}
}

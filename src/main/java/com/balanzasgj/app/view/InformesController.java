package com.balanzasgj.app.view;

import com.balanzasgj.app.model.*;
import com.balanzasgj.app.services.*;
import com.balanzasgj.app.services.TareService.ReportFilter;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.ShowJasper;
import com.balanzasgj.app.utils.Utils;
import com.balanzasgj.app.view.columns.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.apache.log4j.Logger;
import org.javafx.controls.customs.ComboBoxAutoComplete;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class InformesController {
	final static Logger logger = Logger.getLogger(InformesController.class);
	
	public static final String TICKET_ADUANA ="ticketAduanaA4";
	 
	private ComboBoxAutoComplete<Product> cbxProducto;
	private ComboBoxAutoComplete<Client> cbxCliente;
	private ComboBoxAutoComplete<Transport> cbxTransporte;
	private ComboBoxAutoComplete<Origin> cbxProcedencia;
	private TextField txtFiltroBuscar;

	@FXML
    private Label lblFiltrar;
	
    @FXML
    private TableColumn<Tare, String> colTransaccion;
    @FXML
    private TableColumn<Tare, String> colFecha;
    @FXML
    private TableColumn<Tare, Patent> colChasis;
    @FXML
    private TableColumn<Tare, BigDecimal> colEntrada;
    @FXML
    private TableColumn<Tare, BigDecimal> colSalida;
    @FXML
    private TableColumn<Tare, BigDecimal> colNeto;
    @FXML
    private TableColumn<Tare, String> colBalanza;
    @FXML
    private TableColumn<Tare, Product> colProducto;
    @FXML
    private TableColumn<Tare, Client> colCliente;
    @FXML
    private TableColumn<Tare, Transport> colTransporte;
    @FXML
    private TableColumn<Tare, Origin> colProcedencia;
    @FXML
    private TableView<Tare> tblPesajes;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private HBox filterCombo;
    
    @FXML
    private ComboBox<String> cbxFiltroBuscar;

    
    @FXML
    private Button btnTicket;
    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnImprimirDetalle;
    
    @FXML
    private DatePicker timeFechaDesde;
    @FXML
    private DatePicker timeFechaHasta;
    
    private TareService tareService;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private ClientService clientService;
	private OriginService originService;
	private ProductService productService;
	private TransportService transportService;
	private GlobalParameterService globalParameterService;
	private ReportService reportService;
	
    @FXML
    private void handleBuscar(ActionEvent event) {
        if(cbxFiltroBuscar.getSelectionModel().isEmpty()){
            Message.error("Debe seleccionar el criterio de busqueda.");
        }else{
        	Calendar cDesde = Calendar.getInstance();
        	cDesde.setTime(Utils.convertToDate(timeFechaDesde.getValue()));
        	cDesde.set(Calendar.HOUR_OF_DAY, 0);
        	cDesde.set(Calendar.MINUTE, 0);
        	
        	Calendar cHasta = Calendar.getInstance();
        	cHasta.setTime(Utils.convertToDate(timeFechaHasta.getValue()));
        	cHasta.set(Calendar.HOUR_OF_DAY, 23);
        	cHasta.set(Calendar.MINUTE, 59);
        	
            tblPesajes.getItems().clear();
            tblPesajes.getItems().addAll(tareService.findByFieldInformes(
            		cbxFiltroBuscar.getSelectionModel().getSelectedItem(), 
            		getComboElement(), 
            		cDesde.getTime(), 
            		cHasta.getTime()));
        }
    }
    
    private String getComboElement() {
    	String filtro = "";
    	String filtroTipo = cbxFiltroBuscar.getSelectionModel().getSelectedItem();
    	if(filtroTipo.equals(ReportFilter.B_NUMERO.label)
    			|| filtroTipo.equals(ReportFilter.B_PATENTE.label)) {
    		filtro = this.txtFiltroBuscar.getText();
    	}
    	
    	if(filtroTipo.equals(ReportFilter.B_PRODUCTO.label) && this.cbxProducto.getSelectionModel().getSelectedItem() != null) {
    		filtro = this.cbxProducto.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(ReportFilter.B_CLIENTE.label) && this.cbxCliente.getSelectionModel().getSelectedItem() != null) {
    		filtro = this.cbxCliente.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(ReportFilter.B_PROCEDENCIA.label) && this.cbxProcedencia.getSelectionModel().getSelectedItem() != null) {
    		filtro = this.cbxProcedencia.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(ReportFilter.B_TRANSPORTE.label) && this.cbxTransporte.getSelectionModel().getSelectedItem() != null) {
    		filtro = this.cbxTransporte.getSelectionModel().getSelectedItem().getNombre();
    	}    	
    	return filtro;
    }

    @FXML
    private void handleLimpiar(ActionEvent event) {        
        cleanSearch();
        handleBuscar(null);
    }   
    
    private void cleanSearch() {
    	cbxFiltroBuscar.getSelectionModel().clearSelection();
    	cbxFiltroBuscar.setValue("Patente"); 
    	txtFiltroBuscar.setText("");
        
        Calendar cDesde = Calendar.getInstance();
        cDesde.set(Calendar.HOUR_OF_DAY, 0);
        cDesde.set(Calendar.MINUTE, 0);
    	timeFechaDesde.setValue(Utils.convertoToLocalDate(cDesde.getTime()));
    	Calendar cHasta = Calendar.getInstance();
    	cHasta.set(Calendar.HOUR_OF_DAY, 23);
    	cHasta.set(Calendar.MINUTE, 59);
    	timeFechaHasta.setValue(Utils.convertoToLocalDate(cHasta.getTime()));
    }
    
    @FXML
    private void handleImprimirTicket(ActionEvent event) {
    	if(tblPesajes.getSelectionModel().getSelectedItem() != null) {
    		Tare tare = tblPesajes.getSelectionModel().getSelectedItem();
    		String modalidad = Tare.MODO.M_ESTANDAR.label;
			if(tare.getImpExp() != null ? true: false)
				modalidad = Tare.MODO.M_ADUANA.label;

			String value = globalParameterService.get(GlobalParameter.P_TICKET_ETIQUETADORA);
			if(value.equals(GlobalParameter.TYPE_TICKET.REMITO.label))
				reportService.remito(tare.getIdtaras());
			else
				reportService.ticket(modalidad, tare.getIdtaras());
    	} else {
    		Message.error("Debe seleccionar un Pesaje.");
    	}
    }

    private void updateReportCount(HashMap<String, Object> params) {
		Report report =  reportService.getReportByTaraId(tblPesajes.getSelectionModel().getSelectedItem().getIdtaras().longValue());
		if(report == null){
			report = new Report();
			report.setTaraId(tblPesajes.getSelectionModel().getSelectedItem().getIdtaras());
			report.setCount(1);
			reportService.save(report);
		} else {
			report.setCount(report.getCount() + 1);
			reportService.save(report);
		}
			
		params.put(GlobalParameter.P_REPORT_COPY, ShowJasper.getReportCopy(report));
	}
    
    @FXML
    private void handleImprimir(ActionEvent event) {
		reportService.tare(tblPesajes.getItems());
    }

    @FXML
    private void handleImprimirDetalle(ActionEvent event) {
    	reportService.tareDetalle(tblPesajes.getItems());
    }

    public void initialize() {  
    	txtFiltroBuscar = new TextField("");
    	txtFiltroBuscar.setId("txtFiltroBuscar");
        cbxCliente = new ComboBoxAutoComplete<Client>();
        cbxCliente.setId("cbxCliente");
        cbxProcedencia= new ComboBoxAutoComplete<Origin>();
        cbxProcedencia.setId("cbxProcedencia");
        cbxProducto = new ComboBoxAutoComplete<Product>();
        cbxProducto.setId("cbxProducto");
        cbxTransporte = new ComboBoxAutoComplete<Transport>();
        cbxTransporte.setId("cbxTransporte");
        lblFiltrar.setVisible(false);
        
        initValues();
        cleanSearch();
        initPersistence();
        handleBuscar(null);
        
        cbxFiltroBuscar.valueProperty().addListener(new ChangeListener<String>() {
            @Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	txtFiltroBuscar.setText("");
            	filterCombo.getChildren().remove(txtFiltroBuscar);
            	filterCombo.getChildren().remove(cbxCliente);
            	filterCombo.getChildren().remove(cbxProcedencia);
            	filterCombo.getChildren().remove(cbxProducto);
            	filterCombo.getChildren().remove(cbxProcedencia);
            	filterCombo.getChildren().remove(cbxTransporte);
            	boolean vFiltrar = false;
				if(newValue.equals(ReportFilter.B_NUMERO.label) || newValue.equals(ReportFilter.B_PATENTE.label)) {
					vFiltrar= true;
					filterCombo.getChildren().add(txtFiltroBuscar);
				}
				if(newValue.equals(ReportFilter.B_CLIENTE.label)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxCliente);
				}
				if(newValue.equals(ReportFilter.B_PRODUCTO.label)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxProducto);
				}
				if(newValue.equals(ReportFilter.B_PROCEDENCIA.label)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxProcedencia);
				}
				
				if(newValue.equals(ReportFilter.B_TRANSPORTE.label)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxTransporte);
				}
				lblFiltrar.setVisible(vFiltrar);
			}
        });
        cbxFiltroBuscar.setValue(ReportFilter.B_TODO.label);
    }

    private void initPersistence() {        
        this.clientService = new ClientService();
		this.originService = new OriginService();
		this.productService = new ProductService();
		this.transportService = new TransportService();
		this.globalParameterService = new GlobalParameterService();
		this.tareService = new TareService();
		this.reportService= new ReportService();
		
        colTransaccion.setCellValueFactory(new PropertyValueFactory<>("transaccion"));
        colFecha.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

            @Override
            public String getValue() {
                if(cellData.getValue().getFechaEntrada() != null){
                    return format.format(cellData.getValue().getFechaEntrada());
                }
                return "";

            }
        });
        colChasis.setCellValueFactory(new PropertyValueFactory<>("patente"));     
    	colChasis.setCellFactory(col -> new PatenteTableCell<>());
        colEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
        colSalida.setCellValueFactory(new PropertyValueFactory<>("pesoSalida"));

        colNeto.setCellValueFactory(cellData -> new ObservableValueBase<BigDecimal>() {

            @Override
            public BigDecimal getValue() {
                if(cellData.getValue().getPesoEntrada() != null
                        && cellData.getValue().getPesoSalida() != null){
                    return new BigDecimal(cellData.getValue().getPesoEntrada().doubleValue() - cellData.getValue().getPesoSalida().doubleValue());
                }
                return  new BigDecimal(0);

            }
        });
        colBalanza.setCellValueFactory(new PropertyValueFactory<>("balanza"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colCliente.setCellFactory(col -> new ClientesTableCell<>());

        colTransporte.setCellValueFactory(new PropertyValueFactory<>("transporte"));
		colTransporte.setCellFactory(col -> new TransportesTableCell<>());
		colProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedencias"));
		colProcedencia.setCellFactory(col -> new ProcedenciasTableCell<>());
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colProducto.setCellFactory(col -> new ProductosTableCell<>());
                
		cbxProducto.getItems().addAll(productService.findAll());
		cbxProducto.reload();
		cbxCliente.getItems().addAll(clientService.findAll());
		cbxCliente.reload();
		cbxTransporte.getItems().addAll(transportService.findAll());
		cbxTransporte.reload();
		cbxProcedencia.getItems().addAll(originService.findAll());
		cbxProcedencia.reload();
    }

    private void initValues() {
        cbxFiltroBuscar.getItems().addAll(new String[]{
        		ReportFilter.B_TODO.label, 
        		ReportFilter.B_NUMERO.label, 
        		ReportFilter.B_PATENTE.label, 
        		ReportFilter.B_PRODUCTO.label, 
        		ReportFilter.B_CLIENTE.label, 
        		ReportFilter.B_TRANSPORTE.label, 
        		ReportFilter.B_PROCEDENCIA.label
        });        
        txtFiltroBuscar.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));        
    }
}

package com.balanzasgj.app.view;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.javafx.controls.customs.ComboBoxAutoComplete;

import com.balanzasgj.app.informes.TransaccionesInforme;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProcedenciasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TransportesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.ShowJasper;
import com.balanzasgj.app.utils.Utils;
import com.balanzasgj.app.view.columns.ClientesTableCell;
import com.balanzasgj.app.view.columns.ProcedenciasTableCell;
import com.balanzasgj.app.view.columns.ProductosTableCell;
import com.balanzasgj.app.view.columns.TransportesTableCell;
import com.ibm.icu.util.Calendar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class InformesController {
	final static Logger logger = Logger.getLogger(InformesController.class);
	private static final String B_TODO = "TODO";
	private static final String B_NUMERO = "Número de Transacción";
	private static final String B_PATENTE = "Patente";
	private static final String B_PRODUCTO = "Producto";
	private static final String B_CLIENTE = "Cliente";
	private static final String B_TRANSPORTE = "Transporte";
	private static final String B_PROCEDENCIA = "Procedencia";
	 
	private ComboBoxAutoComplete<Productos> cbxProducto;
	private ComboBoxAutoComplete<Clientes> cbxCliente;
	private ComboBoxAutoComplete<Transportes> cbxTransporte;
	private ComboBoxAutoComplete<Procedencias> cbxProcedencia;
	private TextField txtFiltroBuscar;

	@FXML
    private Label lblFiltrar;
	
    @FXML
    private TableColumn<Taras, String> colTransaccion;
    @FXML
    private TableColumn<Taras, String> colFecha;
    @FXML
    private TableColumn<Taras, String> colChasis;
    @FXML
    private TableColumn<Taras, BigDecimal> colEntrada;
    @FXML
    private TableColumn<Taras, BigDecimal> colSalida;
    @FXML
    private TableColumn<Taras, BigDecimal> colNeto;
    @FXML
    private TableColumn<Taras, String> colBalanza;
    @FXML
    private TableColumn<Taras, Productos> colProducto;
    @FXML
    private TableColumn<Taras, Clientes> colCliente;
    @FXML
    private TableColumn<Taras, Transportes> colTransporte;
    @FXML
    private TableColumn<Taras, Procedencias> colProcedencia;
    @FXML
    private TableView<Taras> tblPesajes;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnLimpiar;

/*
    @FXML
    private TextField txtFiltroBuscar;
    */
    
    @FXML
    private HBox filterCombo;
    
    @FXML
    private ComboBox<String> cbxFiltroBuscar;

    @FXML
    private Button btnImprimir;
    @FXML
    private Button btnImprimirDetalle;
    
    @FXML
    private DatePicker timeFechaDesde;
    @FXML
    private DatePicker timeFechaHasta;
    
    private TarasPersistence tarasPersistence;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private ClientesPersistence clientesPersistence;
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	

    @FXML
    private void handleTblEntidadesSelected(ActionEvent event) {
    }

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
            tblPesajes.getItems().addAll(tarasPersistence.findByFieldInformes(
            		cbxFiltroBuscar.getSelectionModel().getSelectedItem(), 
            		getComboElement(), 
            		cDesde.getTime(), 
            		cHasta.getTime()));
        }
    }
    
    private String getComboElement() {
    	String filtro = "";
    	String filtroTipo = cbxFiltroBuscar.getSelectionModel().getSelectedItem();
    	if(filtroTipo.equals(B_NUMERO)
    			|| filtroTipo.equals(B_PATENTE)) {
    		filtro = this.txtFiltroBuscar.getText();
    	}
    	
    	if(filtroTipo.equals(B_PRODUCTO)) {
    		filtro = this.cbxProducto.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(B_CLIENTE)) {
    		filtro = this.cbxCliente.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(B_PROCEDENCIA)) {
    		filtro = this.cbxProcedencia.getSelectionModel().getSelectedItem().getNombre();
    	}
    	
    	if(filtroTipo.equals(B_TRANSPORTE)) {
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
    private void handleImprimir(ActionEvent event) {
        TransaccionesInforme informe = new TransaccionesInforme(tblPesajes.getItems());
        try {
            informe.generateReport();
            informe.show();
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @FXML
    private void handleImprimirDetalle(ActionEvent event) {
    	HashMap<String, Object> params = new HashMap<>();
    	/*PROPIETARIO DE LA BALANZA*/
		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL, (pg.getValue()== null?"":pg.getValue()));
        	        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_DIR_BAL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_DIR_BAL, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TEL_BAL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_TEL_BAL, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_LOC_BAL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_LOC_BAL, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_PROV_BAL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_PROV_BAL, (pg.getValue()== null?"":pg.getValue()));	
        
        /* EMPRESA*/
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_NOMBRE, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_DIR);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_DIR, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TEL);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_TEL, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_LOC);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_LOC, (pg.getValue()== null?"":pg.getValue()));
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_PROV);	
		parametrosGlobalesPersistence.load(pg);
        params.put(ParametrosGlobales.P_EMPRESA_PROV, (pg.getValue()== null?"":pg.getValue()));	               
        params.put("USUARIO", Usuarios.getUsuarioLogeado());
        
        pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_IMG);	
		parametrosGlobalesPersistence.load(pg);
		if(pg.getValueByte() != null) {
			try {
				byte[] img = new byte[new Long(pg.getValueByte().length()).intValue()];
				Image image = ImageIO.read(new ByteArrayInputStream(img));
	            
	            params.put(ParametrosGlobales.P_EMPRESA_IMG, image);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
			
		}
		
		try {
			ShowJasper.openBeanDataSource("transaccionesDetalles", params, new JRBeanCollectionDataSource(tblPesajes.getItems()) );
		} catch (JRException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
    }

    public void initialize() {  
    	txtFiltroBuscar = new TextField("");
    	txtFiltroBuscar.setId("txtFiltroBuscar");
        cbxCliente = new ComboBoxAutoComplete<Clientes>();
        cbxCliente.setId("cbxCliente");
        cbxProcedencia= new ComboBoxAutoComplete<Procedencias>();
        cbxProcedencia.setId("cbxProcedencia");
        cbxProducto = new ComboBoxAutoComplete<Productos>();
        cbxProducto.setId("cbxProducto");
        cbxTransporte = new ComboBoxAutoComplete<Transportes>();
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
				if(newValue.equals(B_NUMERO) || newValue.equals(B_PATENTE)) {
					vFiltrar= true;
					filterCombo.getChildren().add(txtFiltroBuscar);
				}
				if(newValue.equals(B_CLIENTE)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxCliente);
				}
				if(newValue.equals(B_PRODUCTO)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxProducto);
				}
				if(newValue.equals(B_PROCEDENCIA)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxProcedencia);
				}
				
				if(newValue.equals(B_TRANSPORTE)) {
					vFiltrar= true;
					filterCombo.getChildren().add(cbxTransporte);
				}
				lblFiltrar.setVisible(vFiltrar);
			}
        });
        cbxFiltroBuscar.setValue(B_TODO);
    }

    private void initPersistence() {
        this.tarasPersistence = new TarasPersistenceJdbc();
        this.clientesPersistence = new ClientesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		
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
        colProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedencia"));
        colProcedencia.setCellFactory(col -> new ProcedenciasTableCell<>());
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colProducto.setCellFactory(col -> new ProductosTableCell<>());
                
		cbxProducto.getItems().addAll(productosPersistence.findAll());
		cbxProducto.reload();
		cbxCliente.getItems().addAll(clientesPersistence.findAll());
		cbxCliente.reload();
		cbxTransporte.getItems().addAll(transportesPersistence.findAll());
		cbxTransporte.reload();
		cbxProcedencia.getItems().addAll(procedenciasPersistence.findAll());
		cbxProcedencia.reload();
    }

    private void initValues() {
        cbxFiltroBuscar.getItems().addAll(new String[]{
        		B_TODO, B_NUMERO, B_PATENTE, B_PRODUCTO, B_CLIENTE, B_TRANSPORTE, B_PROCEDENCIA
        });        
        txtFiltroBuscar.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));        
    }
}

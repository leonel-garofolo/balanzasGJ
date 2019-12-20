package com.balanzasgj.app.view;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import javafx.application.Platform;
import org.javafx.controls.customs.ComboBoxAutoComplete;
import org.jfree.util.Log;

import com.balanzasgj.app.conn.serial.SocketConnection;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Comunicaciones;
import com.balanzasgj.app.model.Ejes;
import com.balanzasgj.app.model.Entidades;
import com.balanzasgj.app.model.ImportadoresExportadores;
import com.balanzasgj.app.model.Indicadores;
import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.model.Patentes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.EjesPersistence;
import com.balanzasgj.app.persistence.ImportadoresExportadoresPersistence;
import com.balanzasgj.app.persistence.IndicadoresPersistence;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.PatentesPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.EjesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ImportadoresExportadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.IndicadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.PatentesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProcedenciasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TarasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TransportesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.ShowJasper;
import com.balanzasgj.app.view.columns.ClientesTableCell;
import com.balanzasgj.app.view.columns.ProcedenciasTableCell;
import com.balanzasgj.app.view.columns.ProductosTableCell;
import com.balanzasgj.app.view.columns.TransportesTableCell;
import com.balanzasgj.app.view.custom.AduanaDialog;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PesarEntradaSalidaController extends AnchorPane implements IView, Initializable, SerialPortEventListener, EventHandler<KeyEvent> {		
	final static Logger logger = Logger.getLogger(PesarEntradaSalidaController.class);
	private static final String T_NORMAL = "NORMAL";
	private static final String T_CON_TARA = "CON TARA";
	private static final String T_TOMAR_TARA = "TOMAR TARA";
	private static final String M_ESTANDAR = "ESTANDAR";
	private static final String M_ADUANA = "ADUANA";
	private static final String C_COMPLETO = "COMPLETO";
	private static final String C_POR_EJE = "POR EJE";
	
	
	@FXML
	private HBox layout1;
	
	/* PESAJE */
	@FXML
	private ComboBox<String> cbxModoTara;
	@FXML
	private ComboBox<String> cbxModalidad;
	@FXML
	private ComboBox<String> cbxModoChasis;
	@FXML
	private Button btnEliminarEje;	
	
	/* TOMAR PESAJE*/
	@FXML
	private TextField txtNumberSerial;	
	@FXML
	private Button btnIngresoManual;
	@FXML
	private Label lblTara;	
	@FXML
	private Label lblDias;	
	@FXML
	private Label lblUltima;	
	@FXML
	private Label lblImpExp;	
	@FXML
	private TextField txtUltima;	
	@FXML
	private Button btnTomar;	
	@FXML
	private Button btnPesarEntrada;
	@FXML
	private Button btnPesarSalida;	
	@FXML
	private TextField txtEntrada;
	@FXML
	private TextField txtSalida;
	@FXML
	private TextField txtNeto;
	@FXML
	private TextField txtTransaccion;
	@FXML
	private TextField txtFecha;
	
	/* FORMULARIO */
	@FXML
	private ComboBoxAutoComplete<Productos> cbxProducto;
	@FXML
	private ComboBoxAutoComplete<Clientes> cbxCliente;
	@FXML
	private ComboBoxAutoComplete<Transportes> cbxTransporte;
	@FXML
	private ComboBoxAutoComplete<Procedencias> cbxProcedencia;
	@FXML
	private ComboBoxAutoComplete<ImportadoresExportadores> cbxImpExp;
	@FXML
	private Button btnAccesoProducto;
	@FXML
	private Button btnAccesoCliente;
	@FXML
	private Button btnAccesoTransporte;
	@FXML
	private Button btnAccesoProcedencia;
	@FXML
	private Button btnAccesoImpExp;		
	@FXML
	private Button btnNuevoPesaje;	
	@FXML
	private Button btnAplicar;
	@FXML
	private Button btnTicket;		
	
	/*	
	private ComboBox<Patentes> cbxPatente;
	*/
	@FXML
	private TextField txtPatente;
	@FXML
	private TextField txtNumDoc;
	@FXML
	private TextField txtConductor;
	
	@FXML
	private TextField txtFactura;
	@FXML
	private TextArea txtObservaciones;
	
	/*Tabla de ejes*/
	@FXML
	private TableView<Ejes> tblEjes;	
	@FXML
	private TableColumn<Ejes, Integer> colNroEje;
	@FXML
	private TableColumn<Ejes, Double> colPesoEntrada;
	@FXML
	private TableColumn<Ejes, Double> colPesoSalida;
	
	/*Tabla de pesajes*/
	@FXML
	private TableView<Taras> tblPesajes;
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
	private Button btnBuscar;
	@FXML
	private CheckBox chkSalidasPendientes;
	
	@FXML
	private Label lblProducto;
	@FXML
	private Label lblCliente;
	@FXML
	private Label lblTransporte;
	@FXML
	private Label lblProcedencia;
	@FXML
	private Label lblObservaciones;
	@FXML
	private Label lblFactura;	
	@FXML
	private Label lblConductor;
	@FXML
	private Label lblDocumento;
	
	@FXML
	private HBox hEntrada;
	@FXML
	private HBox hSalida;
	@FXML
	private HBox hNeto;
	
	private char statusTara;

	private ClientesPersistence clientesPersistence;
	private PatentesPersistence patentesPersistence;
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private IndicadoresPersistence indicadoresPersistence;
	private ComunicacionesPersistence comunicacionesPersistence;
	private ImportadoresExportadoresPersistence impExpPersistence;
	private EjesPersistence ejesPersistence;
	private TarasPersistence tarasPersistence;
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	private long idTaraEdit = -1;
	private Taras taraEdit;	
	
	private Indicadores indicadorConfig;
	
	private boolean ingManual;	
	private SocketConnection socket;
	private String sBufferConnection;	
	private Stage stage;
	
	private int posicionInicioDato;
	private int longitudDato;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
	@FXML
	private TextField txtTara;
	
	@FXML
	private TextField txtDiasVenc;
	
	
	@FXML
	private void handleTomar(ActionEvent event) {		
		if(taraEdit.getPesoEntrada() == null || taraEdit.getPesoSalida() == null) {
			if(cbxModoTara.getSelectionModel().getSelectedItem().equals(T_CON_TARA)) {
				txtTara.setText(txtNumberSerial.getText());		
			}
			if(cbxModoChasis.getSelectionModel().getSelectedItem().equals(C_POR_EJE)) {
				Ejes eje = new Ejes();								
				eje.setNroEje(tblEjes.getItems().size() + 1);
				if(statusTara == 'E') {
					eje.setPesoEntrada(Double.valueOf(txtNumberSerial.getText()));
					tblEjes.getItems().add(eje);
				}
				if(statusTara == 'S') {
					List<Ejes> ejes = new ArrayList<>();
					ejes.addAll(tblEjes.getItems());
							
					for(int i= 0; i < ejes.size(); i++) {
						if(ejes.get(i).getPesoSalida() == 0d) {
							ejes.get(i).setPesoSalida(Double.valueOf(txtNumberSerial.getText()));
							break;
						}
					}
					tblEjes.getItems().clear();
					tblEjes.getItems().addAll(ejes);
				}
				
			}
		}
	}
	
	@FXML
	private void handleEliminarEje(ActionEvent event) {		
		if(!tblEjes.getItems().isEmpty()) {
			this.ejesPersistence.deleteById(tblEjes.getItems().get(tblEjes.getItems().size()- 1).getIdEje());
			tblEjes.getItems().remove(tblEjes.getItems().size()- 1);			
		}
	}
	
	@FXML
	private void handleNuevoPesaje(ActionEvent event) {		
		clearForm();
		idTaraEdit = -1;
		activarEndrada();		
		taraEdit = new Taras();
		txtFecha.setText(format.format(new Date()));
		btnIngresoManual.setDisable(false);
		cbxModoTara.setValue(T_NORMAL);
		cbxModalidad.setValue(M_ESTANDAR);
		cbxModoChasis.setValue(C_COMPLETO);
		editableLayout(true);
	}

	@FXML
	private void handleBuscar(ActionEvent event) {
		tblPesajes.getItems().clear();
		tblPesajes.getItems().addAll(tarasPersistence
				.findByField("All", "", chkSalidasPendientes.isSelected()));
	}
	
	@FXML
	private void handleTicket(ActionEvent event) {
		if (taraEdit != null) {
			List<Taras> taras = new ArrayList<>();
			taras.add(taraEdit);
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
					logger.error(e);
				} catch (IOException e) {
					logger.error(e);
				}				
			} else {
				 params.put(ParametrosGlobales.P_EMPRESA_IMG, null);
			}
			try {
				ShowJasper.openBeanDataSource("ticket", params, new JRBeanCollectionDataSource(taras));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}
	}
	
	@FXML
	private void handleIngManual(ActionEvent event) {
		if(Usuarios.getPerfilLogeado().equals(Usuarios.P_SUPERVISOR)
				|| Usuarios.getPerfilLogeado().equals(Usuarios.P_ADMINISTRADOR)) {
			enabledIngManual();
		} else {
			String value = Message.optionSecurity();
			ParametrosGlobales pg = new ParametrosGlobales();
			pg.setId("EMPRESA_ING_MANUAL");
			parametrosGlobalesPersistence.load(pg);	
			String clave = pg.getValue();
			if(clave == null) {
				clave = "123";
			}
			if(value.equals(clave)) {
				enabledIngManual();				
			}
		}
	}
	
	private void enabledIngManual() {
		this.ingManual = true;
		txtNumberSerial.setEditable(ingManual);
		txtNumberSerial.setDisable(!ingManual);
		txtNumberSerial.requestFocus();
	}

	@FXML
	private void handleAplicar(ActionEvent event) {
		if(cbxModoTara.getSelectionModel().getSelectedItem().equals(T_TOMAR_TARA)) {
			if (!txtTara.getText().isEmpty() && !txtDiasVenc.getText().isEmpty() ) {
				Patentes p = new Patentes();
				p.setCodigo(txtPatente.getText());
				p.setTara(Double.valueOf(txtTara.getText()));
				p.setDiasVenc(Integer.valueOf(txtDiasVenc.getText()));
				this.patentesPersistence.save(p);					
				Message.info("Los datos se guardaron correctamente.");
				clearForm();
			} else {
				Message.error("Debe Completar la tara y los dias de vencimiento.");
			}
		} else {
			if (cbxProducto.getValue() != null && cbxCliente.getValue() != null && cbxTransporte.getValue() != null
					&& cbxProcedencia.getValue() != null
					&& !txtPatente.getText().isEmpty()
					&& txtTransaccion.getText() != null && !txtTransaccion.getText().isEmpty() 
					&& !txtFecha.getText().isEmpty()
					&& !cbxModoTara.getSelectionModel().isEmpty()
					&& !cbxModalidad.getSelectionModel().isEmpty()
					&& !cbxModoChasis.getSelectionModel().isEmpty()) {

				if (statusTara == 'S' || statusTara == 'E') {
					boolean isEje = cbxModoChasis.getSelectionModel().getSelectedItem().equals(C_POR_EJE);				
					boolean isConTara = cbxModoTara.getSelectionModel().getSelectedItem().equals(T_CON_TARA);
					Taras tara = new Taras();
					if (idTaraEdit >= 0) {
						tara.setIdtaras(idTaraEdit);
						tarasPersistence.load(tara);
					}
					tara.setTransaccion(txtTransaccion.getText());
					try {
						if(statusTara == 'E') {
							tara.setFechaEntrada(format.parse(txtFecha.getText()));
						} else {
							tara.setFechaSalida(new Date());
						}
						
					} catch (ParseException e) {
						System.out.println("error de formato");
					}
					tara.setProducto(cbxProducto.getValue());
					tara.setCliente(cbxCliente.getValue());
					tara.setTransporte(cbxTransporte.getValue());
					tara.setProcedencias(cbxProcedencia.getValue());
					if(cbxImpExp.isVisible()) {
						tara.setImpExp(cbxImpExp.getValue());
					}
					tara.setPatente(txtPatente.getText());
					tara.setNumDoc(txtNumDoc.getText());
					tara.setConductor(txtConductor.getText());
					tara.setComprobanteNun1(txtFactura.getText());
					tara.setObservacion(txtObservaciones.getText());
					tara.setModoTara(cbxModoTara.getSelectionModel().getSelectedItem());
					tara.setModalidad(cbxModalidad.getSelectionModel().getSelectedItem());
					tara.setModoChasis(cbxModoChasis.getSelectionModel().getSelectedItem());				
					if(indicadorConfig != null && !stage.getTitle().contains("ERROR")) {
						tara.setBalanza(indicadorConfig.getNombre());
					}else {
						tara.setBalanza("ING. MANUAL");
					}
					
					double totalPesaje = 0d;					
					if(isEje) {
						int count = tblEjes.getItems().size();
						for(int i = 0; i < count; i++ ) {
							if(statusTara == 'E') {
								totalPesaje +=tblEjes.getItems().get(i).getPesoEntrada();
							}							
							if(statusTara == 'S') {
								totalPesaje +=tblEjes.getItems().get(i).getPesoSalida();
							}
						}
					}
					
					double totalPeso = 0d;
					if(isEje) {
						totalPeso = totalPesaje;						
					} else {
						totalPeso = Double.valueOf(txtNumberSerial.getText());						
					}					
					if(isConTara) {
						totalPeso = totalPeso - Double.valueOf(txtTara.getText());
					}
					if (statusTara == 'S') {					
						txtSalida.setText(String.valueOf(totalPeso));
						tara.setPesoSalida(new BigDecimal(txtSalida.getText()));
						calcularNeto();
					} else if (statusTara == 'E') {
											
						txtEntrada.setText(String.valueOf(totalPeso));
						
						tara.setPesoEntrada(new BigDecimal(txtEntrada.getText()));
					}
					//comprobar si existe una patente con pesaje de salida pendiente
					boolean existPending = tarasPersistence.checkPending(tara.getPatente());
					if(tara.getIdtaras() == null && existPending) {
						Message.error("Error al guardar, ya existe un pesaje pendiente con la patente " + tara.getPatente() + ".");
						return;
					}
					
					Taras insertTara = tarasPersistence.save(tara);
					if (idTaraEdit == -1) {
						idTaraEdit = insertTara.getIdtaras();
					}
					
					// guardo todos los ejes cargados
					if(isEje) {
						Ejes eje = null;
						for(int i = 0; i < tblEjes.getItems().size(); i ++) {
							eje = tblEjes.getItems().get(i);
							eje.setIdTaras(idTaraEdit);
							ejesPersistence.save(eje);
						}
					}
									
					refleshTableTaras();
					saveContadorTransaccion();
					Message.info("Los datos se guardaron correctamente.");
					clearForm();
					btnIngresoManual.setDisable(true);
					handleNuevoPesaje(event);
				}
			} else {
				Message.error("Debe haber alguna entrada seleccionada.");
			}
		}
	}

	private void saveContadorTransaccion() {
		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId("EMPRESA_TRANSACCION");
		pg.setValue(txtTransaccion.getText());
		parametrosGlobalesPersistence.save(pg);
	}

	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblPesajes.getSelectionModel().isEmpty()) {
			btnPesarEntrada.setStyle("");
			btnPesarSalida.setStyle("");	
			layout1.setDisable(true);
			editableLayout(false);
			loadTara();	
		}
	}
	
	private void editableLayout(boolean edit) {
		txtPatente.setEditable(edit);
		txtNumDoc.setEditable(edit);
		txtConductor.setEditable(edit);
		txtFactura.setEditable(edit);
		txtObservaciones.setEditable(edit);

		cbxTransporte.setDisable(!edit);	
		cbxProcedencia.setDisable(!edit);
		cbxCliente.setDisable(!edit);
		cbxProducto.setDisable(!edit);
		cbxImpExp.setDisable(!edit);
	}

	private void loadTara() {
		clearForm();
		statusTara = '-';
		txtNumberSerial.setText("0");
		taraEdit = tblPesajes.getSelectionModel().getSelectedItem();
		idTaraEdit = taraEdit.getIdtaras();
		txtTransaccion.setText(taraEdit.getTransaccion());
		txtFecha.setText(format.format(taraEdit.getFechaEntrada()));
		txtPatente.setText(this.patentesPersistence.findById(taraEdit.getPatente()).getCodigo());
		txtEntrada.setText(taraEdit.getPesoEntrada().toString());
		
		txtNumDoc.setText(taraEdit.getNumDoc());
		txtConductor.setText(taraEdit.getConductor());
		txtFactura.setText(taraEdit.getComprobanteNun1());
		txtObservaciones.setText(taraEdit.getObservacion());

		cbxTransporte.setValue(taraEdit.getTransporte());
		cbxProcedencia.setValue(taraEdit.getProcedencias());
		cbxCliente.setValue(taraEdit.getCliente());
		cbxProducto.setValue(taraEdit.getProducto());	
		cbxImpExp.setValue(taraEdit.getImpExp());
		
		cbxModoTara.setValue(taraEdit.getModoTara());
		cbxModalidad.setValue(taraEdit.getModalidad());
		cbxModoChasis.setValue(taraEdit.getModoChasis());
		
		if(taraEdit.getPesoEntrada() != null && taraEdit.getPesoSalida() != null) {
			btnPesarEntrada.setDisable(true);
			btnPesarSalida.setDisable(true);
			txtSalida.setText(taraEdit.getPesoSalida().toString());
			activarSalida();
		}else if (taraEdit.getPesoSalida() != null) {
			txtSalida.setText(taraEdit.getPesoSalida().toString());
		} else {
			activarSalida();
		}
		if (taraEdit.getPesoNeto() != null) {
			txtNeto.setText(taraEdit.getPesoNeto().toString());
		}				
		
		if(taraEdit.getPesoEntrada() != null && taraEdit.getPesoSalida() != null) {
			btnAplicar.setDisable(true);
			btnTomar.setDisable(true);
			btnIngresoManual.setDisable(true);			
		} else {
			btnAplicar.setDisable(false);
			btnTomar.setDisable(false);
			btnIngresoManual.setDisable(false);
		}
		
	}

	@FXML
	private void handlePesarEntrada(ActionEvent event) {
		activarEndrada();
	}

	private void activarEndrada() {
		// Marcar entrada
		statusTara = 'E';
		btnPesarEntrada.setDisable(false);
		btnPesarSalida.setDisable(true);
		btnPesarEntrada.setStyle("-fx-background-color: #7fffd4; ");
		btnPesarSalida.setStyle("");
		layout1.setDisable(false);
		editableLayout(false);
		btnIngresoManual.setDisable(false);
				
		
		if (idTaraEdit == -1) {
			ParametrosGlobales pg = new ParametrosGlobales();
			pg.setId("EMPRESA_TRANSACCION");
			parametrosGlobalesPersistence.load(pg);
			if (pg != null) {
				txtTransaccion.setText(Integer.valueOf(pg.getValue()) + 1 + "");
			}
		} else {
			if(taraEdit.getIdtaras() != null) {
				enabledTara(cbxModoTara.getValue());
				enabledAduana(cbxModalidad.getValue());
				enabledTableEjes(cbxModoChasis.getValue());
				layout1.setDisable(true);
			}			
		}
	}
	
	private void activarSalida() {
		// Marcar salida
		statusTara = 'S';
		btnPesarEntrada.setDisable(true);
		btnPesarSalida.setDisable(false);
		btnPesarEntrada.setStyle("");
		btnPesarSalida.setStyle("-fx-background-color: #7fffd4; ");
		btnIngresoManual.setDisable(false);
		if (taraEdit.getIdtaras() >= 0) {
			enabledTara(cbxModoTara.getValue());
			enabledTableEjes(cbxModoChasis.getValue());							
		}
	}

	private void calcularNeto() {
		if (!txtEntrada.getText().equals("") && !txtSalida.getText().equals("")) {
			txtNeto.setText(String.valueOf(Double.valueOf(txtSalida.getText()).doubleValue()
					- Double.valueOf(txtEntrada.getText()).doubleValue()));
		}
	}
	
	@FXML
	private void handleEditPatente(ActionEvent event) {
		String value = Message.addElement("Ingrese la nueva patente:");
		if(!value.equals("")) {
			Patentes pat = new Patentes();
			pat.setCodigo(value);
			pat.setTara(0d);
			pat.setUpdate(new Date());
			patentesPersistence.save(pat );
		}
	}

	@FXML
	private void handleEditProducto(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.PRODUCTOS);
		if(!value.equals("")) {
			Productos prod = (Productos)buildObject(ConfiguracionesController.PRODUCTOS, value);			
			prod = productosPersistence.save(prod );
			cbxProducto.getItems().clear();
			cbxProducto.getItems().addAll(productosPersistence.findAll());	
			cbxProducto.reload();
			cbxProducto.setValue(prod);
		}
	}

	@FXML
	private void handleEditCliente(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.CLIENTE);
		if(!value.equals("")) {
			Clientes cli = (Clientes)buildObject(ConfiguracionesController.CLIENTE, value);			
			cli = clientesPersistence.save(cli );
			cbxCliente.getItems().clear();
			cbxCliente.getItems().addAll(clientesPersistence.findAll());
			cbxCliente.reload();
			cbxCliente.setValue(cli);
		}
	}
	
	@FXML
	private void handleEditImpExp(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.IMPORTADORES);
		if(!value.equals("")) {
			ImportadoresExportadores ie = (ImportadoresExportadores)buildObject(ConfiguracionesController.IMPORTADORES, value);			
			ie = impExpPersistence.save(ie);
			cbxImpExp.getItems().clear();
			cbxImpExp.getItems().addAll(impExpPersistence.findAll());
			cbxImpExp.reload();
			cbxImpExp.setValue(ie);
		}
	}
	
	private Entidades buildObject(String type, String values) {
		String[] result = values.split(AduanaDialog.SPLIT);
		switch (type) {
		case ConfiguracionesController.CLIENTE:
			Clientes c = new Clientes();
			c.setNombre(result[0]);
			return c;
		case ConfiguracionesController.IMPORTADORES:
			ImportadoresExportadores ie = new ImportadoresExportadores();
			ie.setNombre(result[0]);
			if(result.length > 1) {
				ie.setCuit(result[1]);
			}
			return ie;
		case ConfiguracionesController.PROCEDENCIAS:
			Procedencias pro = new Procedencias();
			pro.setNombre(result[0]);
			return pro;
		case ConfiguracionesController.PRODUCTOS:
			Productos p = new Productos();
			p.setNombre(result[0]);
			if(result.length > 1) {
				p.setAlias(result[1]);
			}
			return p;
		case ConfiguracionesController.TRANSPORTES:
			Transportes t = new Transportes();
			t.setNombre(result[0]);
			if(result.length > 1) {
				t.setCuit(result[1]);
			}
			return t;	
		default:
			break;
		}
		return null;
	}

	@FXML
	private void handleEditTransporte(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.TRANSPORTES);
		if(!value.equals("")) {
			Transportes tra = (Transportes)buildObject(ConfiguracionesController.TRANSPORTES, value);
			tra =transportesPersistence.save(tra );
			cbxTransporte.getItems().clear();
			cbxTransporte.getItems().addAll(transportesPersistence.findAll());	
			cbxTransporte.reload();
			cbxTransporte.setValue(tra);
		}
	}

	@FXML
	private void handleEditProcedencia(ActionEvent event) {
		String value  = Message.addElementAduana(ConfiguracionesController.PROCEDENCIAS);
		if(!value.equals("")) {
			Procedencias pro = (Procedencias)buildObject(ConfiguracionesController.PROCEDENCIAS, value);			
			pro = procedenciasPersistence.save(pro );
			cbxProcedencia.getItems().clear();
			cbxProcedencia.getItems().addAll(procedenciasPersistence.findAll());	
			cbxProcedencia.reload();
			cbxProcedencia.setValue(pro);
		}
	}

	@FXML
	private void handleSubir(ActionEvent event) {
		Long number = Long.valueOf(txtNumberSerial.getText());
		txtNumberSerial.setText(String.valueOf(number + 1000));
	}

	@FXML
	private void handleBajar(ActionEvent event) {
		Long number = Long.valueOf(txtNumberSerial.getText());
		txtNumberSerial.setText(String.valueOf(number - 1000));
	}

	@FXML
	private void handleCancelar(ActionEvent event) {
	}
	
	@FXML
	private void handleModoTara(ActionEvent event) {
		if(cbxModoTara.getSelectionModel().getSelectedItem() != null && 
				cbxModoTara.getSelectionModel().getSelectedItem().equals(T_TOMAR_TARA)) {
			modoTomarTara();
		}else if(cbxModoTara.getSelectionModel().getSelectedItem() != null && 
			cbxModoTara.getSelectionModel().getSelectedItem().equals(T_CON_TARA)) {
			modoConTara();
		} else {
			modoNormal();
		}
	}
	@FXML
	private void handleModalidad(ActionEvent event) {
		if(cbxModalidad.getSelectionModel().getSelectedItem() != null && 
				cbxModalidad.getSelectionModel().getSelectedItem().equals(M_ADUANA)) {
			modoAduana(true);
		}else { 
			modoAduana(false);
		} 
	}
	
	private void modoAduana(boolean isVisible) {
		lblImpExp.setVisible(isVisible);
		cbxImpExp.setVisible(isVisible);
		btnAccesoImpExp.setVisible(isVisible);
	}
	
	private void addPatente() {
		String pantente = txtPatente.getText();
		if(!pantente.isEmpty()) {
			Patentes p =patentesPersistence.findById(pantente);
			if(p == null) {
				boolean desicion= Message.option("La patente no existe, desea agregarla?");
				if(desicion) {
					Patentes newPatente = new Patentes();
					newPatente.setCodigo(pantente);
					newPatente.setTara(Double.valueOf(txtTara.getText()));
					patentesPersistence.save(newPatente);
				} else {					
					txtPatente.requestFocus();
					txtPatente.setText("");
				}
			} else {//cargo tara
				if(!cbxModoTara.getSelectionModel().getSelectedItem().equals(T_TOMAR_TARA) ) {
					boolean existPending = tarasPersistence.checkPending(pantente);				
					if(existPending) {
						Message.error("Error al guardar, ya existe un pesaje pendiente con la patente " + pantente + ".");
						txtPatente.setText("");
						txtPatente.requestFocus();
						return;
					}
				}
				txtTara.setText(p.getTara().toString());
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				txtUltima.setText(format.format(p.getUpdate()));
				txtDiasVenc.setText(String.valueOf(p.getDiasVenc()));
				cbxProducto.requestFocus();
			}
		}
	}

	private void initValues() {		
		txtFecha.setDisable(true);
		txtFecha.setStyle("-fx-opacity: 1");
		cbxProducto.setStyle("-fx-opacity: 1");
		cbxProducto.getEditor().setStyle("-fx-opacity: 1");		
		cbxCliente.setStyle("-fx-opacity: 1");
		cbxCliente.getEditor().setStyle("-fx-opacity: 1");
		cbxTransporte.setStyle("-fx-opacity: 1");
		cbxTransporte.getEditor().setStyle("-fx-opacity: 1");
		cbxProcedencia.setStyle("-fx-opacity: 1");
		cbxProcedencia.getEditor().setStyle("-fx-opacity: 1");
		cbxImpExp.setStyle("-fx-opacity: 1");
		cbxImpExp.getEditor().setStyle("-fx-opacity: 1");
		
		tblEjes.setVisible(false);
		btnEliminarEje.setVisible(false);
		btnTomar.setVisible(false);
		txtTara.setVisible(false);
		txtDiasVenc.setVisible(false);
		lblTara.setVisible(false);
		lblDias.setVisible(false);
		lblUltima.setVisible(false);
		txtUltima.setVisible(false);
		
		this.ingManual = false;
		btnIngresoManual.setDisable(true);
		layout1.setDisable(true);
		editableLayout(false);
		cbxModoTara.getItems().addAll(new String[] { T_NORMAL, T_CON_TARA, T_TOMAR_TARA});
		cbxModalidad.getItems().addAll(new String[] { M_ESTANDAR, M_ADUANA});
		cbxModoChasis.getItems().addAll(new String[] { C_COMPLETO, C_POR_EJE});
		txtPatente.setOnKeyReleased(this);		
		txtNumDoc.setOnKeyReleased(this);
		txtConductor.setOnKeyReleased(this);
		txtFactura.setOnKeyReleased(this);
		txtObservaciones.setOnKeyReleased(this);
		
		
		txtPatente.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
		    @Override
		    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
		    {
		        if (!newPropertyValue)
		        {
		        	addPatente();
		        }
		    }
		});
		txtPatente.setOnKeyTyped(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {	           
	        	if (Character.isWhitespace(ke.getCharacter().charAt(0))) {
        		   ke.consume();
        		}
	        }
	    });
		txtPatente.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		txtNumDoc.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		txtConductor.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		txtFactura.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		txtObservaciones.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));		
		cbxModoTara.valueProperty().addListener(new ChangeListener<String>() {
	      	@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	      		enabledTara(newValue);
			}
	    });
		
		cbxModoChasis.valueProperty().addListener(new ChangeListener<String>() {
	      	@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	      		enabledTableEjes(newValue);
			}
	    });
		txtNumberSerial.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNumberSerial.setText(newValue.toUpperCase());
		});
		txtEntrada.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEntrada.setText(newValue.toUpperCase());
		});
		txtSalida.textProperty().addListener((ov, oldValue, newValue) -> {
			txtSalida.setText(newValue.toUpperCase());
		});
		txtNeto.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNeto.setText(newValue.toUpperCase());
		});
		txtTransaccion.textProperty().addListener((ov, oldValue, newValue) -> {
			txtTransaccion.setText(newValue.toUpperCase());
		});
		txtNumDoc.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNumDoc.setText(newValue.toUpperCase());
		});		
		txtConductor.textProperty().addListener((ov, oldValue, newValue) -> {
			if(newValue != null) {
				txtConductor.setText(newValue.toUpperCase());
			}
		});
		
		txtNumberSerial.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("-?\\\\d+(.\\\\d+)?")) {
		        	txtNumberSerial.setText(newValue.replaceAll("-?\\\\d+(.\\\\d+)?", ""));
		        }
		    }
		});
		
		txtDiasVenc.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtDiasVenc.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	private void enabledAduana(String newValue) {
		if(newValue != null && newValue.equals(M_ADUANA)) {
			modoAduana(true);
		}
		modoAduana(false);
	}
	
	private void enabledTara(String newValue) {
		if(newValue != null && statusTara != '-') {
  			if(newValue.equals(T_TOMAR_TARA)) {
  				btnTomar.setText("Tomar Tara");
				btnTomar.setVisible(true);
				txtTara.setVisible(true);
				txtDiasVenc.setVisible(true);
				lblTara.setVisible(true);
				lblDias.setVisible(true);
				lblUltima.setVisible(true);
  				txtUltima.setVisible(true);
			}else {
				btnTomar.setVisible(false);
				lblTara.setVisible(false);
				txtTara.setVisible(false);
				lblDias.setVisible(false);
				txtDiasVenc.setVisible(false);								
				lblUltima.setVisible(false);
  				txtUltima.setVisible(false);
			}
  		} if(newValue != null && newValue.equals(T_CON_TARA)) {
  			Patentes p = new Patentes();
  			p.setCodigo(taraEdit.getPatente());
  			this.patentesPersistence.load(p);
  			if(p.getTara() != null) {
  				lblTara.setVisible(true);
  				txtTara.setVisible(true);
  				txtTara.setText(p.getTara().toString());
  				lblDias.setVisible(true);
  				txtDiasVenc.setVisible(true);
  				txtDiasVenc.setText(String.valueOf(p.getDiasVenc()));							
  				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
  				lblUltima.setVisible(true);
  				txtUltima.setVisible(true);
  				txtUltima.setText(format.format(p.getUpdate()));					
  			}
  		} else {
  			btnTomar.setVisible(false);
  			lblTara.setVisible(false);
  			txtTara.setVisible(false);			
			lblDias.setVisible(false);
			txtDiasVenc.setVisible(false);
			lblUltima.setVisible(false);
			txtUltima.setVisible(false);
  		}
	}
	
	private void enabledTableEjes(String newValue) {
		if(newValue != null && statusTara != '-') {
  			if(newValue.equals(C_POR_EJE)) {
  				tblEjes.getItems().clear();
  				if(taraEdit.getIdtaras() != null) {  				
  					tblEjes.getItems().addAll(ejesPersistence.findAll(taraEdit.getIdtaras()));
  				}
  				btnTomar.setText("Pesar EJE");
  				btnTomar.setVisible(true);
				tblEjes.setVisible(true);
				btnEliminarEje.setVisible(true);
			}else {
				btnTomar.setVisible(false);
				tblEjes.setVisible(false);
				btnEliminarEje.setVisible(false);
			}
  		} else {
  			tblEjes.setVisible(false);
			btnEliminarEje.setVisible(false);
  		}
	}

	private void initSerialConnector() {	
		stage.setTitle("Tomar Pesajes");
		socket= new SocketConnection();
		List<Comunicaciones> comunicaciones= comunicacionesPersistence.findAll();
		for(Comunicaciones comunicacion: comunicaciones) {
			indicadorConfig= indicadoresPersistence.findById(comunicacion.getIdindicadores().longValue());
			this.posicionInicioDato = indicadorConfig.getPosicionInicioDato();
			this.longitudDato = indicadorConfig.getLongitudDato();
			int paridad = 0;
			if(indicadorConfig.getParidad().equals("n")) {
				paridad = SerialPort.PARITY_NONE;
			}
			try {
				socket.conectar("COM" + indicadorConfig.getPuerto(), 
						indicadorConfig.getVelocidad(), 
						indicadorConfig.getBitsDeDatos(), 
						Integer.valueOf(indicadorConfig.getBitsDeParada()), 
						paridad, 
						2000);
				socket.addEventSocket(this);			
				stage.setTitle("Tomar Pesajes: Indicador Conectado -> " + indicadorConfig.getNombre() + " | Puerto: COM" + indicadorConfig.getPuerto() + " | Velocidad: " + indicadorConfig.getVelocidad());
			}catch (Exception e) {
				stage.setTitle("Tomar Pesajes: ERROR DE CONEXION CON EL INDICADOR ");
			}
			break;
		}	
	}
	
	private void modoTomarTara() {
		cambiarModo(false);	
		lblTara.setVisible(true);
		txtTara.setVisible(true);
		lblDias.setVisible(true);
		txtDiasVenc.setVisible(true);
		txtDiasVenc.setEditable(true);		
		lblUltima.setVisible(true);
		txtUltima.setVisible(true);
	}
	
	private void modoConTara() {
		cambiarModo(true);
		lblTara.setVisible(true);
		txtTara.setVisible(true);
		lblDias.setVisible(true);
		txtDiasVenc.setVisible(true);
		txtDiasVenc.setEditable(false);
		lblUltima.setVisible(true);
		txtUltima.setVisible(true);
	}
	
	private void modoNormal() {
		cambiarModo(true);
		lblTara.setVisible(false);
		txtTara.setVisible(false);
		lblDias.setVisible(false);
		txtDiasVenc.setVisible(false);
		lblUltima.setVisible(false);
		txtUltima.setVisible(false);
	}
	
	private void cambiarModo(boolean visible) {
		lblProducto.setVisible(visible);
		cbxProducto.setVisible(visible);
		lblCliente.setVisible(visible);
		cbxCliente.setVisible(visible);
		lblTransporte.setVisible(visible);
		cbxTransporte.setVisible(visible);
		lblProcedencia.setVisible(visible);
		cbxProcedencia.setVisible(visible);
		
		lblFactura.setVisible(visible);
		txtFactura.setVisible(visible);
		lblObservaciones.setVisible(visible);
		txtObservaciones.setVisible(visible);
		lblConductor.setVisible(visible);
		txtConductor.setVisible(visible);
		lblDocumento.setVisible(visible);
		txtNumDoc.setVisible(visible);
		
		btnAccesoProducto.setVisible(visible);		
		btnAccesoCliente.setVisible(visible);
		btnAccesoTransporte.setVisible(visible);
		btnAccesoProcedencia.setVisible(visible);
		
		hEntrada.setVisible(visible);
		hSalida.setVisible(visible);
		hNeto.setVisible(visible);
	}

	private void initPersistence() {		
		this.patentesPersistence = new PatentesPersistenceJdbc();
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.indicadoresPersistence = new IndicadoresPersistenceJdbc();
		this.comunicacionesPersistence = new ComunicacionesPersistenceJdbc();
		this.impExpPersistence = new ImportadoresExportadoresPersistenceJdbc();
		this.ejesPersistence = new EjesPersistenceJdbc();
		this.tarasPersistence = new TarasPersistenceJdbc();
		this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		
		
		cbxProducto.getItems().addAll(productosPersistence.findAll());
		cbxProducto.reload();
		cbxCliente.getItems().addAll(clientesPersistence.findAll());
		cbxCliente.reload();
		cbxTransporte.getItems().addAll(transportesPersistence.findAll());
		cbxTransporte.reload();
		cbxProcedencia.getItems().addAll(procedenciasPersistence.findAll());
		cbxProcedencia.reload();
		cbxImpExp.getItems().addAll(impExpPersistence.findAll());
		cbxImpExp.reload();
		
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
				if (cellData.getValue().getPesoEntrada() != null && cellData.getValue().getPesoSalida() != null) {
					return new BigDecimal(cellData.getValue().getPesoEntrada().doubleValue()
							- cellData.getValue().getPesoSalida().doubleValue());
				}
				return new BigDecimal(0);

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
		
		/*Tabla de ejes*/
		colNroEje.setCellValueFactory(new PropertyValueFactory<>("nroEje"));
		colPesoEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
		colPesoSalida.setCellValueFactory(new PropertyValueFactory<>("pesoSalida"));

		refleshTableTaras();
	}

	private void refleshTableTaras() {
		tblPesajes.getItems().clear();
		tblPesajes.getItems().addAll(tarasPersistence.findByField("All", "", chkSalidasPendientes.isSelected()));
	}

	private void clearForm() {
		txtFecha.setText("");
		txtEntrada.setText("");
		txtSalida.setText("");
		txtNeto.setText("");
		txtNumberSerial.setText("0");
		txtTransaccion.setText("");
		cbxProducto.setValue(null);
		cbxCliente.setValue(null);
		cbxTransporte.setValue(null);
		cbxProcedencia.setValue(null);		
		cbxImpExp.setValue(null);
		
		txtNumDoc.setText("");
		txtConductor.setText("");
		txtPatente.setText("");
		txtTara.setText("0");
		txtDiasVenc.setText("");							
		txtUltima.setText("");	
		txtFactura.setText("");
		txtObservaciones.setText("");
		
		cbxModoTara.setValue(null);
		cbxModalidad.setValue(null);
		cbxModoChasis.setValue(null);
		btnAplicar.setDisable(false);
		btnTomar.setDisable(false);
		btnIngresoManual.setDisable(false);
		modoAduana(false);
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {		
		initValues();
		initPersistence();		
		clearForm();
		
		// longrunning operation runs on different thread
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                    	if(statusTara == 'E') {
                    		txtFecha.setText(format.format(new Date()));
                    	}
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
	}
	
	public void closeSocket() {
		socket.close();
	}

	private String data;
	@Override
	public void serialEvent(SerialPortEvent event) {
		if(!ingManual) {
			if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {		
				try {
					int available = socket.getInput().available();
					byte[] chunk = new byte[available];					
					socket.getInput().read(chunk, 0, available);
					data = new String(chunk);
					Log.info("Rx ->" + data);
					data = data.trim();
					data = data.substring(this.posicionInicioDato, this.longitudDato);
					sBufferConnection = data.trim().replaceAll("[^\\d.]", "");		
					Log.info("Transfor ->" + sBufferConnection);
					System.out.println("Transfor ->" + sBufferConnection);
					if(!sBufferConnection.isEmpty() && 
							!txtNumberSerial.getText().trim().equals(sBufferConnection.trim())) {
						try {
							Double.valueOf(sBufferConnection);
							txtNumberSerial.setText(sBufferConnection);
						}catch ( NumberFormatException e) {													
							logger.error("ERROR DE CONVERSION. ", e);
						}
					}
				} catch (IOException e) {
					logger.error("IO Error Occurred: ", e);
				}
			}
		}		
	}
		
	@Override
	public void setStage(Stage stage) {
		this.stage =stage;
		initSerialConnector();
		KeyCombination an = new KeyCodeCombination(KeyCode.N, KeyCombination.ALT_DOWN);
		KeyCombination aa = new KeyCodeCombination(KeyCode.A, KeyCombination.ALT_DOWN);
		KeyCombination at = new KeyCodeCombination(KeyCode.T, KeyCombination.ALT_DOWN);				
		
		Mnemonic m = new Mnemonic(btnNuevoPesaje, an);
		this.stage.getScene().addMnemonic(m);
		
		m = new Mnemonic(btnAplicar, aa);
		this.stage.getScene().addMnemonic(m);
		
		m = new Mnemonic(btnTicket, at);
		this.stage.getScene().addMnemonic(m);
	}

	@Override
	public void handle(KeyEvent event) {	
		if(event.getCode().equals(KeyCode.ENTER) 
				|| event.getCode().equals(KeyCode.TAB)) {
			if(event.getSource() == txtPatente) {
				txtConductor.requestFocus();
				return;
			}
			if(event.getSource() == txtConductor) {
				txtNumDoc.requestFocus();
				return;
			}
			if(event.getSource() == txtNumDoc) {
				txtFactura.requestFocus();
				return;
			}
			
			if(event.getSource() == cbxProducto) {
				cbxTransporte.requestFocus();
				return;
			}
			if(event.getSource() == cbxTransporte) {
				txtObservaciones.requestFocus();
				return;
			}
			if(event.getSource() == txtObservaciones) {
				cbxCliente.requestFocus();
				return;
			}
			if(event.getSource() == cbxCliente) {
				cbxProcedencia.requestFocus();
				return;
			}
			
			if(event.getSource() == cbxProcedencia) {
				btnBuscar.requestFocus();
				return;
			}
		}		
	} 
}

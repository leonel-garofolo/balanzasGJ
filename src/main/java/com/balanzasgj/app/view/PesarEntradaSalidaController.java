package com.balanzasgj.app.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.javafx.controls.customs.ComboBoxAutoComplete;

import com.balanzasgj.app.conn.serial.SocketConnection;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Comunicaciones;
import com.balanzasgj.app.model.Ejes;
import com.balanzasgj.app.model.Indicadores;
import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.model.Patentes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.EjesPersistence;
import com.balanzasgj.app.persistence.IndicadoresPersistence;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.PatentesPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.EjesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.IndicadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.PatentesPersistenceJdbc;
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

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PesarEntradaSalidaController extends AnchorPane implements IView, Initializable, SerialPortEventListener, EventHandler<KeyEvent> {		
	@FXML
	private HBox layout1;
	@FXML
	private HBox layout2;
	
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
	private Button btnTomarTara;	
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
	private DatePicker dateFecha;
	
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
	private Button btnAccesoProducto;
	@FXML
	private Button btnAccesoCliente;
	@FXML
	private Button btnAccesoTransporte;
	@FXML
	private Button btnAccesoProcedencia;
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
	private TextField txtFactura;
	@FXML
	private TextArea txtObservaciones;
	
	/*Tabla de ejes*/
	@FXML
	private TableView<Ejes> tblEjes;	
	@FXML
	private TableColumn<Ejes, Integer> colNroEje;
	@FXML
	private TableColumn<Ejes, Double> colPeso;
	
	/*Tabla de pesajes*/
	@FXML
	private TableView<Taras> tblPesajes;
	@FXML
	private TableColumn<Taras, String> colTransaccion;
	@FXML
	private TableColumn<Taras, Date> colFecha;
	@FXML
	private TableColumn<Taras, String> colChasis;
	@FXML
	private TableColumn<Taras, String> colAcoplado;
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
	private Button btnLimpiar;
	@FXML
	private TextField txtFiltroBuscar;
	@FXML
	private ComboBox<String> cbxFiltroBuscar;
	
	@FXML
	private CheckBox chkSalidasPendientes;
	
	private char statusTara;

	private ClientesPersistence clientesPersistence;
	private PatentesPersistence patentesPersistence;
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private IndicadoresPersistence indicadoresPersistence;
	private ComunicacionesPersistence comunicacionesPersistence;
	private EjesPersistence ejesPersistence;
	private TarasPersistence tarasPersistence;
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	private long idTaraEdit = -1;
	private Taras taraEdit;	
	
	private Indicadores indicadorConfig;
	
	private boolean ingManual;	
	private SocketConnection socket;
	private String sBufferConnection;	
	private Stage stage;
		
	@FXML
	private TextField txtTara;
	
	
	@FXML
	private void handleTomarTara(ActionEvent event) {
		txtTara.setText(txtNumberSerial.getText());
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
		dateFecha.setValue(Utils.convertoToLocalDate(new Date()));
		btnIngresoManual.setDisable(false);
		cbxModoTara.setValue("NORMAL");
		cbxModalidad.setValue("ESTANDAR");
		cbxModoChasis.setValue("COMPLETO");
	}

	@FXML
	private void handleBuscar(ActionEvent event) {
		if (cbxFiltroBuscar.getSelectionModel().isEmpty()) {
			tblPesajes.getItems().clear();
			tblPesajes.getItems().addAll(tarasPersistence
					.findByField("All", txtFiltroBuscar.getText(), chkSalidasPendientes.isSelected()));
		} else {
			tblPesajes.getItems().clear();
			tblPesajes.getItems().addAll(tarasPersistence
					.findByField(cbxFiltroBuscar.getSelectionModel().getSelectedItem(), txtFiltroBuscar.getText(), chkSalidasPendientes.isSelected()));
		}
	}

	@FXML
	private void handleLimpiar(ActionEvent event) {
		cbxFiltroBuscar.getSelectionModel().clearSelection();
		txtFiltroBuscar.setText("");
		chkSalidasPendientes.setSelected(false);
		refleshTableTaras();
	}

	@FXML
	private void handleTicket(ActionEvent event) {
		if (taraEdit != null) {
			List<Taras> taras = new ArrayList<>();
			taras.add(taraEdit);
			HashMap<String, Object> params = new HashMap<>();
			try {
				ShowJasper.openBeanDataSource("ticket", params, new JRBeanCollectionDataSource(taras));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void handleIngManual(ActionEvent event) {
		if(Usuarios.getPerfilLogeado().equals("SUPERVISOR")
				|| Usuarios.getPerfilLogeado().equals("ADMINISTRADOR")) {
			enabledIngManual();
		} else {
			String value = Message.optionSecurity();
			if(value.equals("123")) {
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
		if (cbxProducto.getValue() != null && cbxCliente.getValue() != null && cbxTransporte.getValue() != null
				&& cbxProcedencia.getValue() != null
				//&& !cbxPatente.getSelectionModel().isEmpty()
				&& !txtPatente.getText().isEmpty()
				&& txtTransaccion.getText() != null && !txtTransaccion.getText().isEmpty() 
				&& dateFecha.getValue() != null
				&& !cbxModoTara.getSelectionModel().isEmpty()
				&& !cbxModalidad.getSelectionModel().isEmpty()
				&& !cbxModoChasis.getSelectionModel().isEmpty()) {

			if (statusTara == 'S' || statusTara == 'E') {
				boolean isEje = cbxModoChasis.getSelectionModel().getSelectedItem().equals("POR EJE");				
				
				Taras tara = new Taras();
				if (idTaraEdit >= 0) {
					tara.setIdtaras(idTaraEdit);
					tarasPersistence.load(tara);
				}
				tara.setTransaccion(txtTransaccion.getText());
				tara.setFecha(Utils.convertToDate(dateFecha.getValue()));
				tara.setProducto(cbxProducto.getValue());
				tara.setCliente(cbxCliente.getValue());
				tara.setTransporte(cbxTransporte.getValue());
				tara.setProcedencias(cbxProcedencia.getValue());
				//tara.setPatente(cbxPatente.getSelectionModel().getSelectedItem().getCodigo());
				tara.setPatente(txtPatente.getText());
				tara.setNumDoc(txtNumDoc.getText());
				tara.setComprobanteNun1(txtFactura.getText());
				tara.setObservacion(txtObservaciones.getText());
				tara.setModoTara(cbxModoTara.getSelectionModel().getSelectedItem());
				tara.setModalidad(cbxModalidad.getSelectionModel().getSelectedItem());
				tara.setModoChasis(cbxModoChasis.getSelectionModel().getSelectedItem());
				
				double totalPesaje = 0d;
				int lastEje = 0;
				if(isEje) {
					int count = tblEjes.getItems().size();
					for(int i = 0; i < count; i++ ) {
						totalPesaje +=tblEjes.getItems().get(i).getPeso(); 
						if(i == (count-1) ) {
							lastEje = tblEjes.getItems().get(i).getNroEje();
						}
					}
					
					totalPesaje += Double.valueOf(txtNumberSerial.getText());
				}
				
				double totalPeso = 0d;
				if(isEje) {
					totalPeso = totalPesaje;						
				} else {
					totalPeso = Double.valueOf(txtNumberSerial.getText());						
				}					
				if (cbxModoTara.getSelectionModel().getSelectedItem().equals("CON TARA") 
						&& !txtTara.getText().isEmpty()) {
					totalPeso = totalPeso - Double.valueOf(txtTara.getText());
					Patentes p = new Patentes();
					p.setCodigo(txtPatente.getText());
					p.setTara(Double.valueOf(txtTara.getText()));
					this.patentesPersistence.save(p);					
				}
				if (statusTara == 'S') {					
					txtSalida.setText(String.valueOf(totalPeso));
					tara.setPesoSalida(new BigDecimal(txtSalida.getText()));
					calcularNeto();
				} else if (statusTara == 'E') {
										
					txtEntrada.setText(String.valueOf(totalPeso));
					
					tara.setPesoEntrada(new BigDecimal(txtEntrada.getText()));
				}
				Taras insertTara = tarasPersistence.save(tara);
				if (idTaraEdit == -1) {
					idTaraEdit = insertTara.getIdtaras();
				}
				
				// guardo todos los ejes cargados
				if(isEje) {
					Ejes eje = new Ejes();					
					eje.setIdTaras(insertTara.getIdtaras());
					eje.setType(String.valueOf(statusTara));
					eje.setNroEje(lastEje + 1);
					eje.setPeso(Double.valueOf(txtNumberSerial.getText()));
					tblEjes.getItems().add(eje);
					ejesPersistence.save(eje);
				}
								
				refleshTableTaras();
				saveContadorTransaccion();
				Message.info("Los datos se guardaron correctamente.");
				clearForm();
				btnIngresoManual.setDisable(true);
			}
		} else {
			Message.error("Debe haber alguna entrada seleccionada.");
		}
	}

	private void saveContadorTransaccion() {
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId("EMPRESA_TRANSACCION");
		pg.setValue(txtTransaccion.getText());
		parametrosGoblalesPersistence.save(pg);
	}

	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblPesajes.getSelectionModel().isEmpty()) {
			btnPesarEntrada.setStyle("");
			btnPesarSalida.setStyle("");	
			layout1.setDisable(true);
			//editableLayout(false);
			layout2.setDisable(true);			
			loadTara();	
		}
	}
	
	private void editableLayout(boolean edit) {
		ObservableList<Node> nodes = layout2.getChildren();
		for(Node n : nodes) {			
			if(n instanceof GridPane) {
				GridPane nodeGrid = (GridPane)n;
				ObservableList<Node> gridNodes = nodeGrid.getChildren();
				for(Node gridN : gridNodes) {
					if(gridN instanceof TextField) {
						((TextField) gridN).setEditable(edit);
						continue;
					}
					if(gridN instanceof ComboBox) {
						((ComboBox) gridN).setEditable(edit);
						continue;
					}
					if(gridN instanceof TextArea) {
						((TextArea) gridN).setEditable(edit);
						continue;
					}
					if(gridN instanceof Button) {
						((Button) gridN).setDisable(edit);
						continue;
					}
					
				}
			}
		}
	}

	private void loadTara() {
		clearForm();
		statusTara = '-';
		txtNumberSerial.setText("0");
		taraEdit = tblPesajes.getSelectionModel().getSelectedItem();
		idTaraEdit = taraEdit.getIdtaras();
		txtTransaccion.setText(taraEdit.getTransaccion());
		dateFecha.setValue(Utils.convertoToLocalDate(taraEdit.getFecha()));
		txtPatente.setText(this.patentesPersistence.findById(taraEdit.getPatente()).getCodigo());
		txtEntrada.setText(taraEdit.getPesoEntrada().toString());
		if (taraEdit.getPesoSalida() != null) {
			txtSalida.setText(taraEdit.getPesoSalida().toString());
		} else {
			activarSalida();
		}
		if (taraEdit.getPesoNeto() != null) {
			txtNeto.setText(taraEdit.getPesoNeto().toString());
		}
		txtNumDoc.setText(taraEdit.getNumDoc());
		txtFactura.setText(taraEdit.getComprobanteNun1());
		txtObservaciones.setText(taraEdit.getObservacion());

		cbxTransporte.setValue(taraEdit.getTransporte());
		cbxProcedencia.setValue(taraEdit.getProcedencias());
		cbxCliente.setValue(taraEdit.getCliente());
		cbxProducto.setValue(taraEdit.getProducto());			
		
		cbxModoTara.setValue(taraEdit.getModoTara());
		cbxModalidad.setValue(taraEdit.getModalidad());
		cbxModoChasis.setValue(taraEdit.getModoChasis());
		
		tblEjes.getItems().clear();		
	}

	@FXML
	private void handlePesarEntrada(ActionEvent event) {
		activarEndrada();
	}

	private void activarEndrada() {
		// Marcar entrada
		statusTara = 'E';
		btnPesarEntrada.setStyle("-fx-background-color: #7fffd4; ");
		btnPesarSalida.setStyle("");
		layout1.setDisable(false);
		//editableLayout(false);
		layout2.setDisable(false);
		btnIngresoManual.setDisable(false);
				
		
		if (idTaraEdit == -1) {
			ParametrosGoblales pg = new ParametrosGoblales();
			pg.setId("EMPRESA_TRANSACCION");
			parametrosGoblalesPersistence.load(pg);
			if (pg != null) {
				txtTransaccion.setText(Integer.valueOf(pg.getValue()) + 1 + "");
			}
		} else {
			if(taraEdit.getIdtaras() != null) {
				tblEjes.getItems().clear();
				tblEjes.getItems().addAll(ejesPersistence.findAll(taraEdit.getIdtaras(), String.valueOf(statusTara)));	
				enabledTara(cbxModoTara.getValue());
				enabledTableEjes(cbxModoChasis.getValue());
				layout1.setDisable(true);
			}			
		}
	}
	
	private void activarSalida() {
		// Marcar salida
		statusTara = 'S';
		btnPesarEntrada.setStyle("");
		btnPesarSalida.setStyle("-fx-background-color: #7fffd4; ");
		btnIngresoManual.setDisable(false);
		if (idTaraEdit >= 0) {
			tblEjes.getItems().clear();
			tblEjes.getItems().addAll(ejesPersistence.findAll(taraEdit.getIdtaras(), String.valueOf(statusTara)));	
			enabledTara(cbxModoTara.getValue());
			enabledTableEjes(cbxModoChasis.getValue());			
		}
	}

	@FXML
	private void handlePesarSalida(ActionEvent event) {
		activarSalida();
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
			/*
			cbxPatente.getItems().clear();
			cbxPatente.getItems().addAll(patentesPersistence.findAll());			
			cbxPatente.setValue(pat);
			*/
			
		}
	}

	@FXML
	private void handleEditProducto(ActionEvent event) {
		String value = Message.addElement("Ingrese el nombre del nuevo Producto:");
		if(!value.equals("")) {
			Productos prod = new Productos();
			prod.setNombre(value);
			productosPersistence.save(prod );
			cbxProducto.getItems().clear();
			cbxProducto.getItems().addAll(productosPersistence.findAll());	
			cbxProducto.reload();
			cbxProducto.setValue(prod);
		}
	}

	@FXML
	private void handleEditCliente(ActionEvent event) {
		String value = Message.addElement("Ingrese el nombre del nuevo Cliente:");
		if(!value.equals("")) {
			Clientes cli = new Clientes();
			cli.setNombre(value);
			clientesPersistence.save(cli );
			cbxCliente.getItems().clear();
			cbxCliente.getItems().addAll(clientesPersistence.findAll());
			cbxCliente.reload();
			cbxCliente.setValue(cli);
		}
	}

	@FXML
	private void handleEditTransporte(ActionEvent event) {
		String value = Message.addElement("Ingrese el nombre del nuevo Transporte:");
		if(!value.equals("")) {
			Transportes tra = new Transportes();
			tra.setNombre(value);
			transportesPersistence.save(tra );
			cbxTransporte.getItems().clear();
			cbxTransporte.getItems().addAll(transportesPersistence.findAll());	
			cbxTransporte.reload();
			cbxTransporte.setValue(tra);
		}
	}

	@FXML
	private void handleEditProcedencia(ActionEvent event) {
		String value = Message.addElement("Ingrese el nombre de la nueva Procedencia:");
		if(!value.equals("")) {
			Procedencias pro = new Procedencias();
			pro.setNombre(value);
			procedenciasPersistence.save(pro );
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
				txtTara.setText(p.getTara().toString());
				cbxProducto.requestFocus();
			}
		}
	}

	private void initValues() {
		tblEjes.setVisible(false);
		btnEliminarEje.setVisible(false);
		btnTomarTara.setVisible(false);
		txtTara.setVisible(false);
		lblTara.setVisible(false);
		
		this.ingManual = false;
		btnIngresoManual.setDisable(true);
		layout1.setDisable(true);
		//editableLayout(false);
		layout2.setDisable(true);
		cbxModoTara.getItems().addAll(new String[] { "NORMAL", "CON TARA"});
		cbxModalidad.getItems().addAll(new String[] { "ESTANDAR", "ADUANA"});
		cbxModoChasis.getItems().addAll(new String[] { "COMPLETO", "POR EJE"});
		txtPatente.setOnKeyReleased(this);		
		txtNumDoc.setOnKeyReleased(this);
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
		txtFactura.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		txtObservaciones.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));		
		txtFiltroBuscar.setTextFormatter(new TextFormatter<>((change) -> {
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
				
		cbxFiltroBuscar.getItems().addAll(new String[] { "Número de Transacción", "Patente Chasis", "Producto",
				"Cliente", "Transporte", "Procedencia" });
		cbxFiltroBuscar.setValue("Patente Chasis");

		dateFecha.setValue(LocalDate.now());
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
		
		
		
		txtNumberSerial.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtNumberSerial.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	private void enabledTara(String newValue) {
		if(newValue != null && statusTara != '-') {
  			if(newValue.equals("CON TARA")) {
				btnTomarTara.setVisible(true);
				txtTara.setVisible(true);
				lblTara.setVisible(true);
			}else {
				btnTomarTara.setVisible(false);
				txtTara.setVisible(false);
				lblTara.setVisible(false);
			}
  		} else {
  			btnTomarTara.setVisible(false);
  			txtTara.setVisible(false);
			lblTara.setVisible(false);
  		}
	}
	
	private void enabledTableEjes(String newValue) {
		if(newValue != null && statusTara != '-') {
  			if(newValue.equals("POR EJE")) {
				tblEjes.setVisible(true);
				btnEliminarEje.setVisible(true);
			}else {
				tblEjes.setVisible(false);
				btnEliminarEje.setVisible(false);
			}
  		} else {
  			tblEjes.setVisible(false);
			btnEliminarEje.setVisible(false);
  		}
	}

	private void initSerialConnector() {		
		socket= new SocketConnection();
		List<Comunicaciones> comunicaciones= comunicacionesPersistence.findAll();
		for(Comunicaciones comunicacion: comunicaciones) {
			indicadorConfig= indicadoresPersistence.findById(comunicacion.getIdindicadores().longValue());
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
				stage.setTitle("Taras: Indicador Conectado -> " + indicadorConfig.getNombre() + " | Puerto: COM" + indicadorConfig.getPuerto() + " | Velocidad: " + indicadorConfig.getVelocidad());
			}catch (Exception e) {
				stage.setTitle("Taras: ERROR DE CONEXION CON EL INDICADOR ");
			}
			break;
		}	
	}

	private void initPersistence() {		
		this.patentesPersistence = new PatentesPersistenceJdbc();
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.indicadoresPersistence = new IndicadoresPersistenceJdbc();
		this.comunicacionesPersistence = new ComunicacionesPersistenceJdbc();
		this.ejesPersistence = new EjesPersistenceJdbc();
		this.tarasPersistence = new TarasPersistenceJdbc();
		this.parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		
		
		cbxProducto.getItems().addAll(productosPersistence.findAll());
		cbxProducto.reload();
		cbxCliente.getItems().addAll(clientesPersistence.findAll());
		cbxCliente.reload();
		cbxTransporte.getItems().addAll(transportesPersistence.findAll());
		cbxTransporte.reload();
		cbxProcedencia.getItems().addAll(procedenciasPersistence.findAll());
		cbxProcedencia.reload();
		
		colTransaccion.setCellValueFactory(new PropertyValueFactory<>("transaccion"));
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		colChasis.setCellValueFactory(new PropertyValueFactory<>("patente"));
		colAcoplado.setCellValueFactory(new PropertyValueFactory<>("contenedorNum"));
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
		colProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedencia"));
		colProcedencia.setCellFactory(col -> new ProcedenciasTableCell<>());
		colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
		colProducto.setCellFactory(col -> new ProductosTableCell<>());
		
		/*Tabla de ejes*/
		colNroEje.setCellValueFactory(new PropertyValueFactory<>("nroEje"));
		colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));

		refleshTableTaras();
	}

	private void refleshTableTaras() {
		tblPesajes.getItems().clear();
		tblPesajes.getItems().addAll(tarasPersistence.findByField("All", "", chkSalidasPendientes.isSelected()));
	}

	private void clearForm() {
		dateFecha.setValue(Utils.convertoToLocalDate(new Date()));
		txtEntrada.setText("");
		txtSalida.setText("");
		txtNeto.setText("");
		txtNumberSerial.setText("0");
		txtTransaccion.setText("");
		dateFecha.setValue(null);		
		cbxProducto.setValue(null);
		cbxCliente.setValue(null);
		cbxTransporte.setValue(null);
		cbxProcedencia.setValue(null);		
		txtNumDoc.setText("");
		txtPatente.setText("");
		txtTara.setText("0");
		txtFactura.setText("");
		txtObservaciones.setText("");
		
		cbxModoTara.setValue(null);
		cbxModalidad.setValue(null);
		cbxModoChasis.setValue(null);
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {		
		initValues();
		initPersistence();		
		clearForm();
	}
	
	public void closeSocket() {
		socket.close();
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if(!ingManual) {
			if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {		
				try {
					int available = socket.getInput().available();
					byte[] chunk = new byte[available];
					socket.getInput().read(chunk, 0, available);
					sBufferConnection = new String(chunk);
					txtNumberSerial.setText(sBufferConnection);
				} catch (IOException e) {
					System.out.println("IO Error Occurred: " + e.toString());
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
				cbxProducto.requestFocus();
				return;
			}
			if(event.getSource() == cbxProducto) {
				cbxCliente.requestFocus();
				return;
			}
			if(event.getSource() == cbxCliente) {
				cbxTransporte.requestFocus();
				return;
			}
			if(event.getSource() == cbxTransporte) {
				cbxProcedencia.requestFocus();
				return;
			}
			if(event.getSource() == cbxProcedencia) {
				txtNumDoc.requestFocus();
				return;
			}
			if(event.getSource() == txtNumDoc) {
				txtFactura.requestFocus();
				return;
			}
			if(event.getSource() == txtFactura) {
				txtObservaciones.requestFocus();
				return;
			}
			if(event.getSource() == txtObservaciones) {
				btnAplicar.requestFocus();
				return;
			}
		}		
	} 
}

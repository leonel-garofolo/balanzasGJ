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

import com.balanzasgj.app.basic.bean.ParametrosGoblales;
import com.balanzasgj.app.conn.serial.SocketConnection;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.IndicadoresPersistence;
import com.balanzasgj.app.persistence.OperacionesPersistence;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.IndicadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.OperacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
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

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PesarEntradaSalidaController implements Initializable, SerialPortEventListener {	
	@FXML
	private HBox layout1;
	@FXML
	private HBox layout2;
	@FXML
	private TextField txtNumberSerial;
	@FXML
	private Button btnSubir;
	@FXML
	private Button btnBajar;
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
	@FXML
	private TextField txtBalanza;
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
	private TextField txtConductor;
	@FXML
	private ComboBox cbxTipoDoc;
	@FXML
	private TextField txtNumDoc;
	@FXML
	private TextField txtPatenteChasis;
	@FXML
	private TextField txtPatenteAceptado;
	@FXML
	private ComboBox<String> cbxComprobante;
	@FXML
	private TextField txtComprobante1;
	@FXML
	private TextField txtComprobante2;
	@FXML
	private TextField txtProcedenciaDestino;
	@FXML
	private TextField txtNumContenedor;
	@FXML
	private Button btnPesarEntrada;
	@FXML
	private Button btnPesarSalida;
	@FXML
	private Button btnNuevoPesaje;
	@FXML
	private Button btnAplicar;
	@FXML
	private Button btnIngresoManual;	
	
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
	private TableView<Taras> tblPesajes;

	@FXML
	private Button btnBuscar;
	@FXML
	private Button btnLimpiar;
	@FXML
	private TextField txtFiltroBuscar;
	@FXML
	private ComboBox<String> cbxFiltroBuscar;

	private char statusTara;

	private ClientesPersistence clientesPersistence;
	private OperacionesPersistence operacionesPersistence;
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private IndicadoresPersistence indicadoresPersistence;
	private ComunicacionesPersistence comunicacionesPersistence;
	private TarasPersistence tarasPersistence;
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	private long idTaraEdit = -1;
	private Taras taraEdit;	
	
	private boolean ingManual;	
	private SocketConnection socket;
	@FXML
	private void handleNuevoPesaje(ActionEvent event) {		
		clearForm();
		activarEndrada();
		idTaraEdit = -1;
		taraEdit = new Taras();
		dateFecha.setValue(Utils.convertoToLocalDate(new Date()));
		btnIngresoManual.setDisable(false);
	}

	@FXML
	private void handleBuscar(ActionEvent event) {
		if (cbxFiltroBuscar.getSelectionModel().isEmpty() || txtFiltroBuscar.getText().isEmpty()) {
			Message.error("Debe completar los datos de filtrado.");
		} else {
			tblPesajes.getItems().clear();
			tblPesajes.getItems().addAll(tarasPersistence
					.findByField(cbxFiltroBuscar.getSelectionModel().getSelectedItem(), txtFiltroBuscar.getText()));
		}
	}

	@FXML
	private void handleLimpiar(ActionEvent event) {
		cbxFiltroBuscar.getSelectionModel().clearSelection();
		txtFiltroBuscar.setText("");
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
		String value = Message.optionSecurity();
		if(value.equals("123")) {
			this.ingManual = true;
			btnSubir.setDisable(!ingManual);
			btnBajar.setDisable(!ingManual);
		}
		System.out.println(value);
	}

	@FXML
	private void handleAplicar(ActionEvent event) {
		if (cbxProducto.getValue() != null && cbxCliente.getValue() != null && cbxTransporte.getValue() != null
				&& cbxProcedencia.getValue() != null && txtPatenteChasis.getText() != null
				&& !txtPatenteChasis.getText().isEmpty() && txtTransaccion.getText() != null
				&& !txtTransaccion.getText().isEmpty() && dateFecha.getValue() != null) {

			if (statusTara == 'S' || statusTara == 'E') {
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
				tara.setPatente(txtPatenteChasis.getText());
				if (statusTara == 'S') {
					txtSalida.setText(txtNumberSerial.getText());
					tara.setPesoSalida(new BigDecimal(txtSalida.getText()));
					calcularNeto();
				} else if (statusTara == 'E') {
					txtEntrada.setText(txtNumberSerial.getText());
					tara.setPesoEntrada(new BigDecimal(txtEntrada.getText()));
				}
				tarasPersistence.save(tara);
				refleshTableTaras();
				saveContadorTransaccion();
				Message.info("Los datos se guardaron correctamente.");
				handleNuevoPesaje(event);
				
				btnSubir.setDisable(true);
				btnBajar.setDisable(true);
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
			if (taraEdit == null) {
				loadTara();
			} else {
				if (statusTara == '-') {
					loadTara();
				} else {
					if (Message.option("Desea cancelar la tara actual por la seleccionada?")) {
						btnPesarEntrada.setStyle("");
						btnPesarSalida.setStyle("");
						layout1.setDisable(true);
						layout2.setDisable(true);
						loadTara();
						btnIngresoManual.setDisable(false);
					}
				}
			}
		}
	}

	private void loadTara() {
		statusTara = '-';
		taraEdit = tblPesajes.getSelectionModel().getSelectedItem();
		idTaraEdit = taraEdit.getIdtaras();
		txtTransaccion.setText(taraEdit.getTransaccion());
		dateFecha.setValue(Utils.convertoToLocalDate(taraEdit.getFecha()));
		if (taraEdit.getBalanza() != null) {
			txtBalanza.setText(taraEdit.getBalanza());
		}
		txtPatenteChasis.setText(taraEdit.getPatente());
		if (taraEdit.getPatenteAceptado() != null) {
			txtPatenteAceptado.setText(taraEdit.getPatenteAceptado());
		}
		txtEntrada.setText(taraEdit.getPesoEntrada().toString());
		if (taraEdit.getPesoSalida() != null) {
			txtSalida.setText(taraEdit.getPesoSalida().toString());
		}

		cbxTransporte.setValue(taraEdit.getTransporte());
		cbxProcedencia.setValue(taraEdit.getProcedencias());
		cbxCliente.setValue(taraEdit.getCliente());
		cbxProducto.setValue(taraEdit.getProducto());
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
		layout2.setDisable(false);
		btnIngresoManual.setDisable(false);
		
		if (idTaraEdit == -1) {
			ParametrosGoblales pg = new ParametrosGoblales();
			pg.setId("EMPRESA_TRANSACCION");
			parametrosGoblalesPersistence.load(pg);
			if (pg != null) {
				txtTransaccion.setText(Integer.valueOf(pg.getValue()) + 1 + "");
			}
		}
	}

	@FXML
	private void handlePesarSalida(ActionEvent event) {
		// Marcar salida
		statusTara = 'S';
		btnPesarEntrada.setStyle("");
		btnPesarSalida.setStyle("-fx-background-color: #7fffd4; ");
		btnIngresoManual.setDisable(false);
	}

	private void calcularNeto() {
		if (!txtEntrada.getText().equals("") && !txtSalida.getText().equals("")) {
			txtNeto.setText(String.valueOf(Double.valueOf(txtSalida.getText()).doubleValue()
					- Double.valueOf(txtEntrada.getText()).doubleValue()));
		}
	}

	@FXML
	private void handleEditProducto(ActionEvent event) {
	}

	@FXML
	private void handleEditCliente(ActionEvent event) {
	}

	@FXML
	private void handleEditTransporte(ActionEvent event) {
	}

	@FXML
	private void handleEditProcedencia(ActionEvent event) {
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
	private void handleEntradaManual(ActionEvent event) {
	}

	@FXML
	private void handleSalidaManual(ActionEvent event) {
	}

	@FXML
	private void handleCancelar(ActionEvent event) {
	}

	private void initValues() {
		this.ingManual = false;
		btnSubir.setDisable(!ingManual);
		btnBajar.setDisable(!ingManual);		
		btnIngresoManual.setDisable(true);
		
		layout1.setDisable(true);
		layout2.setDisable(true);
		cbxComprobante.getItems().addAll(new String[] { "FAC", "REM", "CP", "GUIA" });
		cbxFiltroBuscar.getItems().addAll(new String[] { "Número de Transacción", "Patente Chasis", "Producto",
				"Cliente", "Transporte", "Procedencia" });

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
		txtBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			txtBalanza.setText(newValue.toUpperCase());
		});
		txtConductor.textProperty().addListener((ov, oldValue, newValue) -> {
			txtConductor.setText(newValue.toUpperCase());
		});
		txtNumDoc.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNumDoc.setText(newValue.toUpperCase());
		});
		txtPatenteChasis.textProperty().addListener((ov, oldValue, newValue) -> {
			txtPatenteChasis.setText(newValue.toUpperCase());
		});
		txtPatenteAceptado.textProperty().addListener((ov, oldValue, newValue) -> {
			txtPatenteAceptado.setText(newValue.toUpperCase());
		});
		txtComprobante1.textProperty().addListener((ov, oldValue, newValue) -> {
			txtComprobante1.setText(newValue.toUpperCase());
		});
		txtComprobante2.textProperty().addListener((ov, oldValue, newValue) -> {
			txtComprobante2.setText(newValue.toUpperCase());
		});
		txtProcedenciaDestino.textProperty().addListener((ov, oldValue, newValue) -> {
			txtProcedenciaDestino.setText(newValue.toUpperCase());
		});
		txtNumContenedor.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNumContenedor.setText(newValue.toUpperCase());
		});
	}

	private void initSerialConnector() {
		socket= new SocketConnection();
		socket.conectar();
		socket.addEventSocket(this);		
	}

	private void initPersistence() {
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.operacionesPersistence = new OperacionesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.indicadoresPersistence = new IndicadoresPersistenceJdbc();
		this.comunicacionesPersistence = new ComunicacionesPersistenceJdbc();
		this.tarasPersistence = new TarasPersistenceJdbc();
		this.parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();

		cbxProducto.getItems().addAll(productosPersistence.findAll());
		cbxCliente.getItems().addAll(clientesPersistence.findAll());
		cbxTransporte.getItems().addAll(transportesPersistence.findAll());
		cbxProcedencia.getItems().addAll(procedenciasPersistence.findAll());

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

		refleshTableTaras();
	}

	private void refleshTableTaras() {
		tblPesajes.getItems().clear();
		tblPesajes.getItems().addAll(tarasPersistence.findByField("All", ""));
	}

	private void clearForm() {
		txtNumberSerial.setText("0");
		txtTransaccion.setText("");
		dateFecha.setValue(null);
		txtBalanza.setText("");
		cbxProducto.setValue(null);
		cbxCliente.setValue(null);
		cbxTransporte.setValue(null);
		cbxProcedencia.setValue(null);
		cbxComprobante.setValue(null);
		txtComprobante1.setText("");
		txtComprobante2.setText("");
		txtProcedenciaDestino.setText("");
		txtNumContenedor.setText("");
		txtConductor.setText("");
		cbxTipoDoc.setValue(null);
		txtNumDoc.setText("");
		txtPatenteChasis.setText("");
		txtPatenteAceptado.setText("");
	}

	@Override
	public void initialize(URL url, ResourceBundle resource) {		
		initValues();
		initPersistence();
		initSerialConnector();
		clearForm();
	}
	
	public void closeSocket() {
		socket.close();
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {		
			try {
				int available = socket.getInput().available();
				byte[] chunk = new byte[available];
				socket.getInput().read(chunk, 0, available);
				txtNumberSerial.setText(new String(chunk));
			} catch (IOException e) {
				System.out.println("IO Error Occurred: " + e.toString());
			}
		}		
	}
}

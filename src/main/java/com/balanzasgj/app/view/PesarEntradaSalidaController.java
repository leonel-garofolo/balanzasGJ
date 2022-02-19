package com.balanzasgj.app.view;

import com.balanzasgj.app.conn.serial.JSocketConnection;
import com.balanzasgj.app.conn.serial.SocketConnection;
import com.balanzasgj.app.model.*;
import com.balanzasgj.app.services.*;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.view.columns.*;
import com.balanzasgj.app.view.custom.AduanaDialog;
import com.balanzasgj.app.view.dashboard.DashboardActions;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.javafx.controls.customs.ComboBoxAutoComplete;

import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PesarEntradaSalidaController extends AnchorPane
		implements Initializable, SerialPortDataListener, EventHandler<KeyEvent>, IHome {
	final static Logger logger = Logger.getLogger(PesarEntradaSalidaController.class);

	private static final String STYLE_BOLD_LABEL = "-fx-font-weight:bold;";
	private static final String STYLE_NOMAL_LABEL = "-fx-font-weight:normal;";

	private DashboardActions actions;
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

	/* TOMAR PESAJE */
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
	@FXML
	private TextField txtPatenteChasis;
	@FXML
	private TextField txtNacionalidad;

	/* FORMULARIO */
	@FXML
	private ComboBoxAutoComplete<Product> cbxProducto;
	@FXML
	private ComboBoxAutoComplete<Client> cbxCliente;
	@FXML
	private ComboBoxAutoComplete<Transport> cbxTransporte;
	@FXML
	private ComboBoxAutoComplete<Origin> cbxProcedencia;
	@FXML
	private ComboBoxAutoComplete<ImportAndExport> cbxImpExp;
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

	/* Tabla de ejes */
	@FXML
	private TableView<Axis> tblEjes;
	@FXML
	private TableColumn<Axis, Integer> colNroEje;
	@FXML
	private TableColumn<Axis, Double> colPesoEntrada;
	@FXML
	private TableColumn<Axis, Double> colPesoSalida;

	/* Tabla de pesajes */
	@FXML
	private TableView<Tare> tblPesajes;
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
	private TableColumn<Tare, ImportAndExport> colImpExp;

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
	private Label lblNacionalidad;
	@FXML
	private Label lblPatente;	
	@FXML
	private Label lblChasis;
	@FXML
	private Label lblDocumento;

	@FXML
	private Label lblObservacionesAduana;

	@FXML
	private HBox hEntrada;
	@FXML
	private HBox hSalida;
	@FXML
	private HBox hNeto;

	// ADUANA
	@FXML
	private Label lbllATA;
	@FXML
	private Label lblContenedor;
	@FXML
	private Label lblTaraContenedor;
	@FXML
	private Label lblManifiesto;	
	@FXML
	private Label lblDestinatario;
	@FXML
	private Label lblMercaderia;
	@FXML
	private ComboBoxAutoComplete<Ata> cbxATA;
	@FXML
	private TextField txtContenedor;
	@FXML
	private TextField txtTaraContenedor;
	@FXML
	private TextField txtManifiesto;	
	@FXML
	private TextField txtDestinatario;
	@FXML
	private TextField txtMercaderia;

	@FXML
	private TextArea txtObservacionesAduana;
	@FXML
	private Button btnAccesoATA;

	@FXML
	private ComboBox<Indicator> cbxIndicador;

	private char statusTara;

	private ClientService clientService;
	private PatentService pantentService;
	private OriginService originService;
	private ProductService productService;
	private TransportService transportService;
	private IndicatorService indicatorService;
	private ComunicationService comunicationService;
	private ImportAndExportService importAndExportService;
	private AxisService axiService;
	private TareService tareService;
	private GlobalParameterService globalParameterService;
	private ReportService reportService;
	private AtaService ataServices;
	private long idTaraEdit = -1;
	private Tare taraEdit;

	private Indicator indicadorConfig;

	private boolean ingManual;
	private JSocketConnection socket;
	private String sBufferConnection;	

	private String[] caracterControl;
	private int posicionInicioDato;
	private int longitudDato;

	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@FXML
	private TextField txtTara;

	@FXML
	private TextField txtDiasVenc;

	private boolean isDebug;

	@FXML
	private void handleIndicador() {
		closeSocket();
		initSerialConnector();
	}

	@FXML
	private void handleTomar(ActionEvent event) {
		if (taraEdit.getPesoEntrada() == null || taraEdit.getPesoSalida() == null) {
			if (cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label)) {
				txtTara.setText(txtNumberSerial.getText());
			}
			if (cbxModoChasis.getSelectionModel().getSelectedItem().equals(Tare.TIPO.C_POR_EJE.label)) {
				Axis eje = new Axis();
				eje.setNroEje(tblEjes.getItems().size() + 1);
				if (statusTara == 'E') {
					eje.setPesoEntrada(Double.valueOf(txtNumberSerial.getText()));
					tblEjes.getItems().add(eje);
				}
				if (statusTara == 'S') {
					List<Axis> ejes = new ArrayList<>();
					ejes.addAll(tblEjes.getItems());

					for (int i = 0; i < ejes.size(); i++) {
						if (ejes.get(i).getPesoSalida() == null) {
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
		if (!tblEjes.getItems().isEmpty()) {
			this.axiService.deleteById(tblEjes.getItems().get(tblEjes.getItems().size() - 1).getIdEje());
			tblEjes.getItems().remove(tblEjes.getItems().size() - 1);
		}
	}

	@FXML
	private void handleNuevoPesaje(ActionEvent event) {
		clearForm();
		statusTara = 'E';
		idTaraEdit = -1;
		activarEndrada();
		taraEdit = new Tare();

		long timestamp = System.currentTimeMillis();
		txtFecha.setText(format.format(new Date(timestamp)));
		btnIngresoManual.setDisable(false);
		cbxModoTara.setValue(Tare.ACTION.T_NORMAL.label);
		cbxModalidad.setValue(Tare.MODO.M_ESTANDAR.label);
		cbxModoChasis.setValue(Tare.TIPO.C_COMPLETO.label);
		
		final Indicator indicador = cbxIndicador.getSelectionModel().getSelectedItem();
		if(indicador.isEje()) 
			cbxModoChasis.setValue(Tare.TIPO.C_POR_EJE.label);
		
		btnTicket.setDisable(true);
		cbxIndicador.setDisable(false);
		if (cbxIndicador.getItems().size() > 0) {
			cbxIndicador.setValue(cbxIndicador.getItems().get(0));
			handleIndicador();
		}
	}

	@FXML
	private void handleBuscar(ActionEvent event) {
		tblPesajes.getItems().clear();
		tblPesajes.getItems().addAll(tareService.findByField("All", "", chkSalidasPendientes.isSelected()));
	}

	@FXML
	private void handleTicket(ActionEvent event) {
		if (taraEdit != null) {
			final String modalidad = cbxModalidad.getSelectionModel().getSelectedItem();
			if (modalidad != null
					&& modalidad.equals(Tare.MODO.M_ADUANA.label)) {
				ImportAndExport ie = cbxImpExp.getSelectionModel().getSelectedItem();
				if (ie != null && ie.getCodigo() != null) {
					importAndExportService.findById(ie.getCodigo());
					taraEdit.setImpExp(ie);
				}

				Patent p = pantentService.findById(txtPatente.getText());
				taraEdit.setPatente(p);
				Ata ata = cbxATA.getSelectionModel().getSelectedItem();
				if (ata != null && ata.getCodigo() != null) {
					ataServices.findById(ata.getCodigo());
					taraEdit.setAta(ata);
				}
			}

			String ticketFormat = globalParameterService.get(GlobalParameter.P_TICKET_ETIQUETADORA);			
			if(ticketFormat.equals(GlobalParameter.TYPE_TICKET.REMITO.label)) {
				reportService.remito(taraEdit.getIdtaras().longValue());
			}else
				reportService.ticket(modalidad, taraEdit.getIdtaras().longValue());		
			
			reportService.exportCsv("sistema_balanzas_taras.csv");
		}
	}

	@FXML
	private void handleIngManual(ActionEvent event) {
		if (User.getPerfilLogeado().equals(User.P_SUPERVISOR)
				|| User.getPerfilLogeado().equals(User.P_ADMINISTRADOR)) {
			enabledIngManual();
		} else {
			String value = Message.optionSecurity();			
			String clave = globalParameterService.get(GlobalParameter.P_EMPRESA_ING_MANUAL);
			if (clave == null) {
				clave = "123";
			}
			if (value.equals(clave)) {
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
		if (cbxModoTara.getSelectionModel().getSelectedItem() != null
				&& cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_TOMAR_TARA.label)) {
			if (!txtTara.getText().isEmpty() && !txtDiasVenc.getText().isEmpty()) {
				Patent p = new Patent();
				p.setCodigo(txtPatente.getText());
				p.setTara(Double.valueOf(txtTara.getText()));
				p.setDiasVenc(Integer.valueOf(txtDiasVenc.getText()));
				this.pantentService.save(p);
				Message.info("Los datos se guardaron correctamente.");
				clearForm();
			} else {
				Message.error("Debe Completar la tara y los dias de vencimiento.");
			}
		} else {
			if (validateForm()) {
				if (statusTara == 'S' || statusTara == 'E') {	
					boolean isEje = cbxModoChasis.getSelectionModel().getSelectedItem().equals(Tare.TIPO.C_POR_EJE.label);
					boolean isConTara = cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label);
					Tare tara = setEntities(isEje, isConTara);
					
					// comprobar si existe una patente con pesaje de salida pendiente
					boolean existPending = tareService.checkPending(tara.getPatente().getCodigo());
					if (tara.getIdtaras() == null && existPending) {
						Message.error("Error al guardar, ya existe un pesaje pendiente con la patente "
								+ tara.getPatente() + ".");
						return;
					}

					boolean processOK = saveTareProcess(tara, isEje);
					if(processOK){
						boolean ticket = Message
								.optionYesNo("Los datos se guardaron correctamente. Desea Imprimir el ticket?");
						if (ticket) {
							taraEdit = tareService.findById(idTaraEdit);
							handleTicket(null);
						}
						reportService.exportCsv("sistema_balanzas_taras.csv");

						clearForm();
						btnIngresoManual.setDisable(true);
						handleNuevoPesaje(event);
					} else {
						Message.error("Ooops! Se ha producido un error al guardar.");
					}
				}
			} else {
				Message.error("Por favor, ingrese los campos requeridos.");
			}
		}
	}
	
	private Tare setEntities(boolean isEje, boolean isConTara) {
		
		Tare tara = new Tare();
		if (idTaraEdit >= 0) {
			tara = tareService.findById(idTaraEdit);
		}
		tara.setTransaccion(txtTransaccion.getText());
		try {
			if (statusTara == 'E') {
				tara.setFechaEntrada(format.parse(txtFecha.getText()));
			} else {							
				if(cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label)) {
					tara.setFechaEntrada(format.parse(txtFecha.getText()));
				}
				tara.setFechaSalida(format.parse(txtFecha.getText()));
			}

		} catch (ParseException e) {
			System.out.println("error de formato");
		}
		
		if (cbxProducto.isVisible() && cbxProducto.getValue() != null) {
			tara.setProducto(cbxProducto.getValue());
		}
		if (cbxCliente.isVisible() && cbxCliente.getValue() != null) {
			tara.setCliente(cbxCliente.getValue());
		}
		if (cbxTransporte.isVisible() && cbxTransporte.getValue() != null) {
			tara.setTransporte(cbxTransporte.getValue());
		}
		if (cbxProcedencia.isVisible() && cbxProcedencia.getValue() != null) {
			tara.setProcedencias(cbxProcedencia.getValue());
		}					
		if (cbxImpExp.isVisible() && cbxImpExp.getValue() != null) {
			tara.setImpExp(cbxImpExp.getValue());
		}
		if (cbxATA.isVisible() && cbxATA.getValue() != null) {
			tara.setAta(cbxATA.getValue());
		}
		if (txtContenedor.isVisible() && txtContenedor.getText() != null) {
			tara.setContenedor(txtContenedor.getText());
		}
		if (txtTaraContenedor.isVisible() && txtTaraContenedor.getText() != null) {
			tara.setContenedorNum(txtTaraContenedor.getText());
		}
		if (txtManifiesto.isVisible() && txtManifiesto.getText() != null) {
			tara.setManifiesto(txtManifiesto.getText());
		}					

		if (txtDestinatario.isVisible() && txtDestinatario.getText() != null) {
			tara.setDestino(txtDestinatario.getText());
		}

		if (txtMercaderia.isVisible() && txtMercaderia.getText() != null) {
			tara.setMercaderia(txtMercaderia.getText());
		}

		if (txtObservacionesAduana.isVisible() && txtObservacionesAduana.getText() != null) {
			tara.setObservacionAduana(txtObservacionesAduana.getText());
		}

		Patent p = new Patent();
		p.setCodigo(txtPatente.getText());
		tara.setPatente(p);
		tara.setNumDoc(txtNumDoc.getText());
		tara.setConductor(txtConductor.getText());
		tara.setComprobanteNun1(txtFactura.getText());
		tara.setObservacion(txtObservaciones.getText());
		tara.setModoTara(cbxModoTara.getSelectionModel().getSelectedItem());
		tara.setModalidad(cbxModalidad.getSelectionModel().getSelectedItem());
		tara.setModoChasis(cbxModoChasis.getSelectionModel().getSelectedItem());
		tara.setPatenteAceptado(txtPatenteChasis.getText());
		tara.setNacionalidad(txtNacionalidad.getText());
		//if (indicadorConfig != null && !stage.getTitle().contains("ERROR")) {
		if (indicadorConfig != null) {
			tara.setBalanza(indicadorConfig.getNombre());
		} else {
			tara.setBalanza("ING. MANUAL");
		}

		double totalPesaje = 0d;
		if (isEje) {
			int count = tblEjes.getItems().size();
			for (int i = 0; i < count; i++) {
				if (statusTara == 'E') {
					totalPesaje += tblEjes.getItems().get(i).getPesoEntrada();
				}
				if (statusTara == 'S') {
					totalPesaje += tblEjes.getItems().get(i).getPesoSalida();
				}
			}
		}

		double totalPeso = 0d;
		if (isEje) {
			totalPeso = totalPesaje;
		} else {
			totalPeso = Double.valueOf(txtNumberSerial.getText());
		}					
		if (statusTara == 'S') {
			if(cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label)) {
				tara.setPesoEntrada(new BigDecimal(txtEntrada.getText()));
			}						
			
			txtSalida.setText(String.valueOf(totalPeso));
			tara.setPesoSalida(new BigDecimal(txtSalida.getText()));
			calcularNeto();
		} else if (statusTara == 'E') {
			txtEntrada.setText(String.valueOf(totalPeso));
			tara.setPesoEntrada(new BigDecimal(txtEntrada.getText()));

			if (cbxModalidad.getSelectionModel().getSelectedItem().equals(Tare.MODO.M_PUBLICA.label)) {
				tara.setPesoSalida(new BigDecimal(0));
				tara.setPesoNeto(new BigDecimal(0));
			}
		}
		return tara;
	}

	private boolean saveTareProcess(Tare tara, boolean isEje){
		try{
			idTaraEdit = tareService.save(tara);

			// guardo todos los ejes cargados
			if (isEje) {
				Axis eje = null;
				for (int i = 0; i < tblEjes.getItems().size(); i++) {
					eje = tblEjes.getItems().get(i);
					eje.setIdTaras(idTaraEdit);
					axiService.save(eje);
				}
			}

			refleshTableTaras();
			if (taraEdit == null || (taraEdit != null && taraEdit.getIdtaras() == null)) {
				saveContadorTransaccion();
			}
		}catch (Exception e){
			return false;
		}
		return true;
	}

	private boolean validateForm() {
		boolean isValid;
		if (!txtPatente.getText().isEmpty()
				&& txtTransaccion.getText() != null && !txtTransaccion.getText().isEmpty()
				&& !txtFecha.getText().isEmpty() && !cbxModoTara.getSelectionModel().isEmpty()
				&& !cbxModalidad.getSelectionModel().isEmpty() && !cbxModoChasis.getSelectionModel().isEmpty()) {
			isValid = validar(statusTara);
		} else {
			isValid = false;
		}
		return isValid;
	}

	private void saveContadorTransaccion() {	
		globalParameterService.save(GlobalParameter.P_EMPRESA_TRANSACCION, txtTransaccion.getText());
	}

	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblPesajes.getSelectionModel().isEmpty()) {
			btnPesarEntrada.setStyle("");
			btnPesarSalida.setStyle("");
			if (cbxModalidad != null && cbxModalidad.getSelectionModel().getSelectedItem() != null
					&& cbxModalidad.getSelectionModel().getSelectedItem().equals(Tare.MODO.M_PUBLICA.label)) {
				layout1.setDisable(false);
				editableLayout(false);
				editableAduana(true);
			} else {
				layout1.setDisable(true);
				editableLayout(true);
			}
			loadTara();
			btnTicket.setDisable(false);

			if (taraEdit.getPesoSalida() != null) {
				cbxIndicador.setDisable(false);
			}
			editableNoRequeridos(btnAplicar.isDisable());
		}
	}

	private void editableLayout(boolean edit) {
		txtPatente.setEditable(edit);
		txtNumDoc.setEditable(edit);
		txtConductor.setEditable(edit);
		txtNacionalidad.setEditable(edit);
		txtPatenteChasis.setEditable(edit);
		txtFactura.setEditable(edit);
		txtObservaciones.setEditable(edit);
		cbxIndicador.setDisable(edit);

		cbxTransporte.setDisable(!edit);
		btnAccesoTransporte.setDisable(!edit);
		cbxProcedencia.setDisable(!edit);
		btnAccesoProcedencia.setDisable(!edit);
		cbxCliente.setDisable(!edit);
		btnAccesoCliente.setDisable(!edit);
		cbxProducto.setDisable(!edit);
		btnAccesoProducto.setDisable(!edit);
	}

	private void editableAduana(boolean edit) {
		txtContenedor.setEditable(edit);
		txtTaraContenedor.setEditable(edit);
		txtManifiesto.setEditable(edit);
		txtDestinatario.setEditable(edit);
		txtMercaderia.setEditable(edit);
		txtObservacionesAduana.setEditable(edit);

		cbxImpExp.setDisable(!edit);
		btnAccesoImpExp.setDisable(!edit);
		cbxATA.setDisable(!edit);
		btnAccesoATA.setDisable(!edit);
	}

	private void editableNoRequeridos(boolean edit) {
		txtPatenteChasis.setEditable(!edit);
		txtFactura.setEditable(!edit);
		txtObservaciones.setEditable(!edit);
		txtNumDoc.setEditable(!edit);
		txtConductor.setEditable(!edit);
		txtNacionalidad.setEditable(!edit);
	}

	private void loadTara() {
		clearForm();
		statusTara = '-';
		txtNumberSerial.setText("0");
		taraEdit = tblPesajes.getSelectionModel().getSelectedItem();
		idTaraEdit = taraEdit.getIdtaras();
		long timestamp = System.currentTimeMillis();
		txtFecha.setText(format.format(new Date(timestamp)));
		txtTransaccion.setText(taraEdit.getTransaccion());
		txtPatente.setText(taraEdit.getPatente().getCodigo());
		txtPatenteChasis.setText(taraEdit.getPatenteAceptado());
		txtEntrada.setText(taraEdit.getPesoEntrada().toString());

		txtNumDoc.setText(taraEdit.getNumDoc());
		txtConductor.setText(taraEdit.getConductor());
		txtNacionalidad.setText(taraEdit.getNacionalidad());
		txtFactura.setText(taraEdit.getComprobanteNun1());
		txtObservaciones.setText(taraEdit.getObservacion());
		txtObservacionesAduana.setText(taraEdit.getObservacionAduana());

		if(taraEdit.getTransporte() != null)
			cbxTransporte.setValue(taraEdit.getTransporte());

		if(taraEdit.getProcedencias() != null)
			cbxProcedencia.setValue(taraEdit.getProcedencias());

		if(taraEdit.getCliente() != null)
			cbxCliente.setValue(taraEdit.getCliente());

		if(taraEdit.getProducto() != null)
			cbxProducto.setValue(taraEdit.getProducto());

		if(taraEdit.getImpExp() != null)
			cbxImpExp.setValue(taraEdit.getImpExp());

		if(taraEdit.getAta() != null)
			cbxATA.setValue(taraEdit.getAta());

		txtContenedor.setText(taraEdit.getContenedor());
		txtTaraContenedor.setText(taraEdit.getContenedorNum());
		txtManifiesto.setText(taraEdit.getManifiesto());
		txtDestinatario.setText(taraEdit.getDestino());
		txtMercaderia.setText(taraEdit.getMercaderia());

		cbxModoTara.setValue(taraEdit.getModoTara());
		cbxModalidad.setValue(taraEdit.getModalidad());
		cbxModoChasis.setValue(taraEdit.getModoChasis());

		if (taraEdit.getPesoEntrada() != null && taraEdit.getPesoSalida() != null) {
			btnPesarEntrada.setDisable(true);
			btnPesarSalida.setDisable(true);
			txtSalida.setText(taraEdit.getPesoSalida().toString());
			activarSalida();
		} else if (taraEdit.getPesoSalida() != null) {
			txtSalida.setText(taraEdit.getPesoSalida().toString());
		} else {
			activarSalida();
		}
		if (taraEdit.getPesoNeto() != null) {
			txtNeto.setText(taraEdit.getPesoNeto().toString());
		}

		if (taraEdit.getPesoEntrada() != null && taraEdit.getPesoSalida() != null) {
			btnAplicar.setDisable(true);
			btnTomar.setDisable(true);
			btnIngresoManual.setDisable(true);
		} else {
			btnAplicar.setDisable(false);
			btnTomar.setDisable(false);
			btnIngresoManual.setDisable(false);
		}

	}

	private void activarEndrada() {
		// Marcar entrada
		statusTara = 'E';
		btnPesarEntrada.setDisable(false);
		btnPesarSalida.setDisable(true);
		btnPesarEntrada.setStyle("-fx-background-color: #7fffd4; ");
		btnPesarSalida.setStyle("");
		layout1.setDisable(false);
		editableLayout(true);
		btnIngresoManual.setDisable(false);
		clearValidation();
		initValidationsEntrada();

		String value = globalParameterService.get(GlobalParameter.P_EMPRESA_TRANSACCION);
		if (!value.isEmpty()) {
			txtTransaccion.setText(Integer.valueOf(value) + 1 + "");
		}
		if (taraEdit != null && taraEdit.getIdtaras() != null) {
			enabledTara(cbxModoTara.getValue());
			enabledAduana(cbxModalidad.getValue());
			enabledTableEjes(cbxModoChasis.getValue());
			layout1.setDisable(true);
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

		clearValidation();
		initValidationsSalida();
		if (taraEdit.getIdtaras() >= 0) {
			enabledTara(cbxModoTara.getValue());
			enabledTableEjes(cbxModoChasis.getValue());
		}

	}

	private void calcularNeto() {
		if (!txtEntrada.getText().equals("") && !txtSalida.getText().equals("")) {
			txtNeto.setText(String.valueOf(Double.valueOf(txtEntrada.getText()).doubleValue()
					- Double.valueOf(txtSalida.getText()).doubleValue()));
		}
	}

	@FXML
	private void handleEditPatente(ActionEvent event) {
		String value = Message.addElement("Ingrese la nueva patente:");
		if (!value.equals("")) {
			Patent pat = new Patent();
			pat.setCodigo(value);
			pat.setTara(0d);
			long timestamp = System.currentTimeMillis();
			pat.setUpdate(new Date(timestamp));
			pantentService.save(pat);
		}
	}

	@FXML
	private void handleEditProducto(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.PRODUCTOS);
		if (!value.equals("")) {
			Product prod = (Product) buildObject(ConfiguracionesController.PRODUCTOS, value);
			prod.setCodigo(productService.save(prod));
			cbxProducto.getItems().clear();
			cbxProducto.getItems().addAll(productService.findAll());
			cbxProducto.reload();
			cbxProducto.setValue(prod);
		}
	}

	@FXML
	private void handleEditCliente(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.CLIENTE);
		if (!value.equals("")) {
			Client cli = (Client) buildObject(ConfiguracionesController.CLIENTE, value);
			cli.setCodigo(clientService.save(cli));
			cbxCliente.getItems().clear();
			cbxCliente.getItems().addAll(clientService.findAll());
			cbxCliente.reload();
			cbxCliente.setValue(cli);
		}
	}

	@FXML
	private void handleEditImpExp(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.IMPORTADORES);
		if (!value.equals("")) {
			ImportAndExport ie = (ImportAndExport) buildObject(ConfiguracionesController.IMPORTADORES,
					value);
			ie.setCodigo(importAndExportService.save(ie));
			cbxImpExp.getItems().clear();
			cbxImpExp.getItems().addAll(importAndExportService.findAll());
			cbxImpExp.reload();
			cbxImpExp.setValue(ie);
		}
	}

	@FXML
	private void handleEditAta(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.ATA_TRANSPORTISTA);
		if (!value.equals("")) {
			Ata ata = (Ata) buildObject(ConfiguracionesController.ATA_TRANSPORTISTA, value);
			ata.setCodigo(ataServices.save(ata));
			cbxATA.getItems().clear();
			cbxATA.getItems().addAll(ataServices.findAll());
			cbxATA.reload();
			cbxATA.setValue(ata);
		}
	}

	private Entity buildObject(String type, String values) {
		String[] result = values.split(AduanaDialog.SPLIT);
		switch (type) {
		case ConfiguracionesController.CLIENTE:
			Client c = new Client();
			c.setNombre(result[0]);
			if (result.length > 1) {
				c.setCuit(result[1]);
			}
			return c;
		case ConfiguracionesController.IMPORTADORES:
			ImportAndExport ie = new ImportAndExport();
			ie.setNombre(result[0]);
			if (result.length > 1) {
				ie.setCuit(result[1]);
			}
			return ie;
		case ConfiguracionesController.ATA_TRANSPORTISTA:
			Ata ata = new Ata();
			ata.setNombre(result[0]);
			if (result.length > 1) {
				ata.setCuit(result[1]);
			}
			if (result.length > 2) {
				ata.setNacionalidad(result[2]);
			}
			return ata;
		case ConfiguracionesController.PROCEDENCIAS:
			Origin pro = new Origin();
			pro.setNombre(result[0]);
			return pro;
		case ConfiguracionesController.PRODUCTOS:
			Product p = new Product();
			p.setNombre(result[0]);
			if (result.length > 1) {
				p.setAlias(result[1]);
			}
			return p;
		case ConfiguracionesController.TRANSPORTES:
			Transport t = new Transport();
			t.setNombre(result[0]);
			if (result.length > 1) {
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
		if (!value.equals("")) {
			Transport tra = (Transport) buildObject(ConfiguracionesController.TRANSPORTES, value);
			tra.setCodigo(transportService.save(tra)); 
			cbxTransporte.getItems().clear();
			cbxTransporte.getItems().addAll(transportService.findAll());
			cbxTransporte.reload();
			cbxTransporte.setValue(tra);
		}
	}

	@FXML
	private void handleEditProcedencia(ActionEvent event) {
		String value = Message.addElementAduana(ConfiguracionesController.PROCEDENCIAS);
		if (!value.equals("")) {
			Origin pro = (Origin) buildObject(ConfiguracionesController.PROCEDENCIAS, value);
			pro.setCodigo(originService.save(pro)); 
			cbxProcedencia.getItems().clear();
			cbxProcedencia.getItems().addAll(originService.findAll());
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
		if (cbxModoTara.getSelectionModel().getSelectedItem() != null
				&& cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_TOMAR_TARA.label)) {
			modoTomarTara();
		} else if (cbxModoTara.getSelectionModel().getSelectedItem() != null
				&& cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label)) {
			modoConTara();
		} else {
			modoNormal();
		}
	}

	@FXML
	private void handleModalidad(ActionEvent event) {
		if (cbxModalidad.getSelectionModel().getSelectedItem() != null) {							
			modoAduana(cbxModalidad.getSelectionModel().getSelectedItem().equals(Tare.MODO.M_ADUANA.label));
			modoPublica(cbxModalidad.getSelectionModel().getSelectedItem().equals(Tare.MODO.M_PUBLICA.label));
			if(cbxModalidad.getSelectionModel().getSelectedItem().equals(Tare.MODO.M_ADUANA.label)) {
				// Patentes
				lblPatente.setText("Pat. Tractor");
				lblChasis.setText("Pat. Acoplado");
				lblProducto.setText("Mercaderia");
				lblProducto.setPrefWidth(80);										
			} else {
				lblPatente.setText("Patente");
				lblChasis.setText("Acoplado");
				lblProducto.setText("Producto");
				lblProducto.setPrefWidth(53);
			}	
		}
	}

	private void modoAduana(boolean isVisible) {
		// Importacion exportacion
		lblImpExp.setVisible(isVisible);
		cbxImpExp.setVisible(isVisible);
		btnAccesoImpExp.setVisible(isVisible);

		// ATA
		lbllATA.setVisible(isVisible);
		cbxATA.setVisible(isVisible);
		btnAccesoATA.setVisible(isVisible);

		lblContenedor.setVisible(isVisible);
		txtContenedor.setVisible(isVisible);

		lblTaraContenedor.setVisible(isVisible);
		txtTaraContenedor.setVisible(isVisible);

		lblManifiesto.setVisible(isVisible);
		txtManifiesto.setVisible(isVisible);

		lblDestinatario.setVisible(isVisible);
		txtDestinatario.setVisible(isVisible);

		//lblMercaderia.setVisible(isVisible);
		//txtMercaderia.setVisible(isVisible);

		lblObservacionesAduana.setVisible(isVisible);
		txtObservacionesAduana.setVisible(isVisible);
	}

	private void modoPublica(boolean isVisible) {			
		lblProcedencia.setVisible(!isVisible);
		cbxProcedencia.setVisible(!isVisible);
		btnAccesoProcedencia.setVisible(!isVisible);

		lblTransporte.setVisible(!isVisible);
		cbxTransporte.setVisible(!isVisible);
		btnAccesoTransporte.setVisible(!isVisible);
	}

	private void addPatente() {
		String pantente = txtPatente.getText();
		if (!pantente.isEmpty()) {
			Patent p = pantentService.findById(pantente);
			if (p == null) {
				boolean desicion = Message.option("La patente no existe, desea agregarla?");
				if (desicion) {
					Patent newPatente = new Patent();
					newPatente.setCodigo(pantente);
					newPatente.setTara(Double.valueOf(txtTara.getText()));
					pantentService.save(newPatente);
				} else {
					txtPatente.requestFocus();
					txtPatente.setText("");
				}
			} else {// cargo tara
					if (!cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_TOMAR_TARA.label)) {
						boolean existPending = tareService.checkPending(pantente);
						if (existPending) {
							Message.error(
									"Error al guardar, ya existe un pesaje pendiente con la patente " + pantente + ".");
							txtPatente.setText("");
							txtPatente.requestFocus();
							return;
					}
				}									
				if(cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_CON_TARA.label)) {
					// debo llevar el modo a la salida cargando la entrada con el valor de la TARA
					txtEntrada.setText(p.getTara().toString());
					// Marcar salida

					statusTara = 'S';
					btnPesarEntrada.setDisable(true);
					btnPesarSalida.setDisable(false);
					btnPesarEntrada.setStyle("");
					btnPesarSalida.setStyle("-fx-background-color: #7fffd4; ");
					btnIngresoManual.setDisable(false);
					clearValidation();
					initValidationsSalida();

					taraEdit = new Tare();
					idTaraEdit = -1;
					taraEdit.setPesoEntrada(new BigDecimal(p.getTara()));

					try {
						if(txtFecha.getText().isEmpty())
							taraEdit.setFechaEntrada(new Date());
						else
							taraEdit.setFechaEntrada(format.parse(txtFecha.getText()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
		btnTicket.setDisable(true);
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
		cbxATA.setStyle("-fx-opacity: 1");
		cbxATA.getEditor().setStyle("-fx-opacity: 1");
		cbxIndicador.setStyle("-fx-opacity: 1");
		cbxIndicador.getEditor().setStyle("-fx-opacity: 1");

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
		cbxModoTara.getItems().addAll(new String[] {Tare.ACTION.T_NORMAL.label, Tare.ACTION.T_CON_TARA.label, Tare.ACTION.T_TOMAR_TARA.label });
		cbxModalidad.getItems().addAll(new String[] { Tare.MODO.M_ESTANDAR.label, Tare.MODO.M_ADUANA.label, Tare.MODO.M_PUBLICA.label });
		cbxModoChasis.getItems().addAll(new String[] { Tare.TIPO.C_COMPLETO.label, Tare.TIPO.C_POR_EJE.label });
		txtPatente.setOnKeyReleased(this);
		txtNumDoc.setOnKeyReleased(this);
		txtConductor.setOnKeyReleased(this);
		txtNacionalidad.setOnKeyReleased(this);
		txtFactura.setOnKeyReleased(this);
		txtObservaciones.setOnKeyReleased(this);
		txtObservacionesAduana.setOnKeyReleased(this);

		txtPatente.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (!newPropertyValue) {
					addPatente();
				}
			}
		});
		txtPatente.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
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
		txtNacionalidad.setTextFormatter(new TextFormatter<>((change) -> {
			change.setText(change.getText().toUpperCase());
			return change;
		}));
		txtPatenteChasis.setTextFormatter(new TextFormatter<>((change) -> {
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
		txtObservacionesAduana.setTextFormatter(new TextFormatter<>((change) -> {
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
			if (cbxModoTara != null && cbxModoTara.getSelectionModel() != null
					&& cbxModoTara.getSelectionModel().getSelectedItem() != null
					&& cbxModoTara.getSelectionModel().getSelectedItem().equals(Tare.ACTION.T_TOMAR_TARA.label)) {
				txtTara.setText(newValue.toUpperCase());
			}
		});
		txtEntrada.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEntrada.setText(newValue.toUpperCase());
		});
		txtSalida.textProperty().addListener((ov, oldValue, newValue) -> {
			txtSalida.setText(newValue.toUpperCase());
		});

		txtNeto.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null && !newValue.isEmpty()) {
				try {
					double d = Math.round(Double.valueOf(newValue.toUpperCase()) * 100.0) / 100.0;
					txtNeto.setText(String.valueOf(d));
				} catch (NumberFormatException e) {
				}
			}
		});
		txtTransaccion.textProperty().addListener((ov, oldValue, newValue) -> {
			txtTransaccion.setText(newValue.toUpperCase());
		});
		txtNumDoc.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNumDoc.setText(newValue.toUpperCase());
		});
		txtConductor.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtConductor.setText(newValue.toUpperCase());
			}
		});
		txtPatenteChasis.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtPatenteChasis.setText(newValue.toUpperCase());
			}
		});
		txtNacionalidad.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtNacionalidad.setText(newValue.toUpperCase());
			}
		});
		txtNumberSerial.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("-?\\\\d+(.\\\\d+)?")) {
					txtNumberSerial.setText(newValue.replaceAll("-?\\\\d+(.\\\\d+)?", ""));
				}
			}
		});

		txtDiasVenc.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtDiasVenc.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		txtContenedor.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtContenedor.setText(newValue.toUpperCase());
			}
		});

		txtTaraContenedor.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtTaraContenedor.setText(newValue.toUpperCase());
			}
		});

		txtManifiesto.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtManifiesto.setText(newValue.toUpperCase());
			}
		});

		txtDestinatario.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtDestinatario.setText(newValue.toUpperCase());
			}
		});

		txtMercaderia.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtMercaderia.setText(newValue.toUpperCase());
			}
		});
	}

	private void enabledAduana(String newValue) {
		if (newValue != null && newValue.equals(Tare.MODO.M_ADUANA.label)) {
			modoAduana(true);
		}
		modoAduana(false);
	}

	private void enabledTara(String newValue) {
		if (newValue != null && statusTara != '-') {
			if (newValue.equals(Tare.ACTION.T_TOMAR_TARA.label)) {
				btnTomar.setText("Tomar Tara");
				btnTomar.setVisible(true);
				txtTara.setVisible(true);
				txtDiasVenc.setVisible(true);
				lblTara.setVisible(true);
				lblDias.setVisible(true);
				lblUltima.setVisible(true);
				txtUltima.setVisible(true);
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
		if (newValue != null && newValue.equals(Tare.ACTION.T_CON_TARA.label) && taraEdit.getPatente() != null) {
			Patent p = this.pantentService.findById(taraEdit.getPatente().getCodigo());
			if (p.getTara() != null) {
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
		if (newValue != null && statusTara != '-') {
			if (newValue.equals(Tare.TIPO.C_POR_EJE.label)) {
				tblEjes.getItems().clear();
				if (taraEdit.getIdtaras() != null) {
					tblEjes.getItems().addAll(axiService.findAllByTare(taraEdit.getIdtaras()));
				}
				btnTomar.setText("Pesar EJE");
				btnTomar.setVisible(true);
				tblEjes.setVisible(true);
				btnEliminarEje.setVisible(true);
			} else {
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
		if (socket == null) {
			socket = new JSocketConnection();
		}

		if (cbxIndicador.getSelectionModel().getSelectedItem() != null) {
			indicadorConfig = cbxIndicador.getSelectionModel().getSelectedItem();
			if (indicadorConfig.getCaracterControl() != null && indicadorConfig.getCaracterControl().length() > 0) {
				this.caracterControl = indicadorConfig.getCaracterControl().split(",");
			}

			this.posicionInicioDato = indicadorConfig.getPosicionInicioDato();
			this.longitudDato = indicadorConfig.getLongitudDato();
			logger.info("caracterControl: " + caracterControl);
			logger.info("posicionInicioDato: " + posicionInicioDato);
			logger.info("longitudDato: " + longitudDato);
			int paridad = 0;
			if (indicadorConfig.getParidad().equals("n")) {
				paridad = SerialPort.NO_PARITY;
			}
			try {
				boolean conect = socket.conectar("COM" + indicadorConfig.getPuerto(), indicadorConfig.getVelocidad(),
						indicadorConfig.getBitsDeDatos(), Integer.valueOf(indicadorConfig.getBitsDeParada()), paridad,
						2000);
				if (conect) {
					socket.addEventSocket(this);
					this.actions.setTitle(
							"Tara: Indicador Conectado -> " + indicadorConfig.getNombre() + " | Puerto: COM"
									+ indicadorConfig.getPuerto() + " | Velocidad: " + indicadorConfig.getVelocidad() +
									" | Bit de datos: " + indicadorConfig.getBitsDeDatos() +
									" | Paridad: " + indicadorConfig.getParidad() +
									" | Bit de Parada: " + indicadorConfig.getBitsDeParada() +
									" | Control: " + indicadorConfig.getControlDeFlujo()
					);
				} else {
					this.actions.setTitle("Tomar Pesajes: ERROR DE CONEXION CON EL INDICADOR ");
				}
			} catch (Exception e) {
				e.printStackTrace();
				this.actions.setTitle("Tomar Pesajes: ERROR DE CONEXION CON EL INDICADOR ");
			}
		} else {
			this.actions.setTitle("Tomar Pesajes: INDICADOR NO SELECCIONADO ");
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
		txtPatenteChasis.setVisible(visible);
		lblNacionalidad.setVisible(visible);
		txtNacionalidad.setVisible(visible);
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
		this.pantentService = new PatentService();
		this.clientService = new ClientService();
		this.originService = new OriginService();
		this.productService = new ProductService();
		this.transportService = new TransportService();
		this.indicatorService = new IndicatorService();
		this.comunicationService = new ComunicationService();
		this.importAndExportService = new ImportAndExportService();
		this.axiService = new AxisService();
		this.tareService = new TareService();
		this.globalParameterService = new GlobalParameterService();
		this.reportService = new ReportService();
		this.ataServices = new AtaService();

		cbxProducto.getItems().addAll(productService.findAll());
		cbxProducto.reload();
		cbxCliente.getItems().addAll(clientService.findAll());
		cbxCliente.reload();
		cbxTransporte.getItems().addAll(transportService.findAll());
		cbxTransporte.reload();
		cbxProcedencia.getItems().addAll(originService.findAll());
		cbxProcedencia.reload();
		cbxImpExp.getItems().addAll(importAndExportService.findAll());
		cbxImpExp.reload();
		cbxATA.getItems().addAll(ataServices.findAll());
		cbxATA.reload();
		cbxIndicador.setDisable(false);

		boolean add = true;
		List<Comunication> all = comunicationService.findAll();
		for (Comunication c : all) {
			Indicator i = indicatorService.findById((long) c.getIdindicadores());
			for (Indicator cbx : cbxIndicador.getItems()) {
				if (cbx.getIdindicadores().longValue() == i.getIdindicadores().longValue()) {
					add = false;
					break;
				}
			}
			if (add) {
				cbxIndicador.getItems().add(i);
			}
		}
		// inicializo la pantalla con el primer indicador
		if (cbxIndicador.getItems().size() > 0) {
			cbxIndicador.setValue(cbxIndicador.getItems().get(0));
		}
		cbxATA.reload();

		colTransaccion.setCellValueFactory(new PropertyValueFactory<>("transaccion"));
		colFecha.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				if (cellData.getValue().getFechaEntrada() != null) {
					return format.format(cellData.getValue().getFechaEntrada());
				}
				return "";

			}
		});
		colEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
		colSalida.setCellValueFactory(new PropertyValueFactory<>("pesoSalida"));

		colNeto.setCellValueFactory(cellData -> new ObservableValueBase<BigDecimal>() {

			@Override
			public BigDecimal getValue() {
				if (cellData.getValue().getPesoEntrada() != null && cellData.getValue().getPesoSalida() != null) {
					return new BigDecimal(cellData.getValue().getPesoEntrada().doubleValue()
							- cellData.getValue().getPesoSalida().doubleValue());
				}
				return null;

			}
		});
		colBalanza.setCellValueFactory(new PropertyValueFactory<>("balanza"));
		colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		colCliente.setCellFactory(col -> new ClientesTableCell<>());

		colChasis.setCellValueFactory(new PropertyValueFactory<>("patente"));
		colChasis.setCellFactory(col -> new PatenteTableCell<>());
		colTransporte.setCellValueFactory(new PropertyValueFactory<>("transporte"));
		colTransporte.setCellFactory(col -> new TransportesTableCell<>());
		colProcedencia.setCellValueFactory(new PropertyValueFactory<>("procedencias"));
		colProcedencia.setCellFactory(col -> new ProcedenciasTableCell<>());
		colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
		colProducto.setCellFactory(col -> new ProductosTableCell<>());

		colImpExp.setCellValueFactory(new PropertyValueFactory<>("impExp"));
		colImpExp.setCellFactory(col -> new ImpExpTableCell<>());

		/* Tabla de ejes */
		colNroEje.setCellValueFactory(new PropertyValueFactory<>("nroEje"));
		colPesoEntrada.setCellValueFactory(new PropertyValueFactory<>("pesoEntrada"));
		colPesoSalida.setCellValueFactory(new PropertyValueFactory<>("pesoSalida"));

		
		String debugValue = globalParameterService.get(GlobalParameter.P_ACTIVAR_DEBUG);
		if (!debugValue.isEmpty()) {
			isDebug = Boolean.valueOf(debugValue);
		}
		refleshTableTaras();
	}

	private void refleshTableTaras() {
		handleBuscar(null);
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
		cbxATA.setValue(null);
		txtObservaciones.setText("");
		txtObservacionesAduana.setText("");

		txtNumDoc.setText("");
		txtConductor.setText("");
		txtPatenteChasis.setText("");
		txtNacionalidad.setText("");
		txtPatente.setText("");
		txtTara.setText("0");
		txtDiasVenc.setText("");
		txtUltima.setText("");
		txtFactura.setText("");
		txtContenedor.setText("");
		txtTaraContenedor.setText("");
		txtManifiesto.setText("");
		txtDestinatario.setText("");
		txtMercaderia.setText("");

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
		initValidationsEntrada();
		clearForm();

		// longrunning operation runs on different thread
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Runnable updater = new Runnable() {

					@Override
					public void run() {
						if (statusTara == 'E') {
							long timestamp = System.currentTimeMillis();
							txtFecha.setText(format.format(new Date(timestamp)));
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

		String ticketFormat = globalParameterService.get(GlobalParameter.P_TICKET_ETIQUETADORA);
		if(ticketFormat.equals(GlobalParameter.TYPE_TICKET.REMITO.label)) {
			btnTicket.setText("Remito");
		}
	}

	private void clearValidation(){
		lblDocumento.setStyle(STYLE_NOMAL_LABEL);
		lblConductor.setStyle(STYLE_NOMAL_LABEL);
		lblNacionalidad.setStyle(STYLE_NOMAL_LABEL);
		lblChasis.setStyle(STYLE_NOMAL_LABEL);
		lblFactura.setStyle(STYLE_NOMAL_LABEL);
		lblObservaciones.setStyle(STYLE_NOMAL_LABEL);
		lblProducto.setStyle(STYLE_NOMAL_LABEL);
		lblTransporte.setStyle(STYLE_NOMAL_LABEL);
		lblCliente.setStyle(STYLE_NOMAL_LABEL);
		lblProcedencia.setStyle(STYLE_NOMAL_LABEL);

	}

	private void initValidationsEntrada() {
		String value = globalParameterService.get(GlobalParameter.P_VALIDACION_ENTRADA);
		if (!value.isEmpty()) {
			String[] validaciones = value.split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case GlobalParameter.V_DOCUMENTO:
						lblDocumento.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CONDUCTOR:
						lblConductor.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_NACIONALIDAD:
						lblNacionalidad.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CHASIS:
						lblChasis.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_FACTURA:
						lblFactura.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_OBSERVACION:
						lblObservaciones.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_PRODUCTO:
						lblProducto.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_TRANSPORTE:
						lblTransporte.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CLIENTE:
						lblCliente.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_PROCEDENCIA:
						lblProcedencia.setStyle(STYLE_BOLD_LABEL);
						break;
				}
			}
		}
	}

	private boolean validar(char type){
		if(txtPatente.getText().isEmpty())
			return false;

		String value = "";
		if(type == 'E')
			value = globalParameterService.get(GlobalParameter.P_VALIDACION_ENTRADA);
		 else
			 value = globalParameterService.get(GlobalParameter.P_VALIDACION_SALIDA);
		if (!value.isEmpty()) {
			boolean isValid = false;
			String[] validaciones = value.split(",");
			for(int i= 0; i < validaciones.length; i++){
				isValid = false;
				switch (Integer.valueOf(validaciones[i])){
					case GlobalParameter.V_DOCUMENTO:
						if(txtNumDoc.isVisible())
							isValid = !txtNumDoc.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_CONDUCTOR:
						if(txtConductor.isVisible())
							isValid = !txtConductor.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_NACIONALIDAD:
						if(txtNacionalidad.isVisible())
							isValid = !txtNacionalidad.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_CHASIS:
						if(txtPatenteChasis.isVisible())
							isValid = !txtPatenteChasis.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_FACTURA:
						if(txtFactura.isVisible())
							isValid = !txtFactura.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_OBSERVACION:
						if(txtObservaciones.isVisible())
							isValid = !txtObservaciones.getText().isEmpty();
						else
							isValid = true;
						break;
					case GlobalParameter.V_PRODUCTO:
						if(cbxProducto.isVisible())
							isValid = cbxProducto.getValue() != null;
						else
							isValid = true;
						break;
					case GlobalParameter.V_TRANSPORTE:
						if(cbxTransporte.isVisible())
							isValid = cbxTransporte.getValue() != null;
						else
							isValid = true;
						break;
					case GlobalParameter.V_CLIENTE:
						if(cbxCliente.isVisible())
							isValid = cbxCliente.getValue() != null;
						else
							isValid = true;
						break;
					case GlobalParameter.V_PROCEDENCIA:
						if(cbxProcedencia.isVisible())
							isValid = cbxProcedencia.getValue() != null;
						else
							isValid = true;
						break;
				}
				if(!isValid)
					return false;
			}
		}
		return true;
	}

	public void initValidationsSalida(){
		String value = globalParameterService.get(GlobalParameter.P_VALIDACION_SALIDA);
		if (!value.isEmpty()) {
			String[] validaciones = value.split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case GlobalParameter.V_DOCUMENTO:
						lblDocumento.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CONDUCTOR:
						lblConductor.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_NACIONALIDAD:
						lblNacionalidad.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CHASIS:
						lblChasis.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_FACTURA:
						lblFactura.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_OBSERVACION:
						lblObservaciones.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_PRODUCTO:
						lblProducto.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_TRANSPORTE:
						lblTransporte.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_CLIENTE:
						lblCliente.setStyle(STYLE_BOLD_LABEL);
						break;
					case GlobalParameter.V_PROCEDENCIA:
						lblProcedencia.setStyle(STYLE_BOLD_LABEL);
						break;
				}
			}
		}
	}

	public void closeSocket() {
		if (socket != null) {
			socket.close();
			socket = null;
		}
	}

	private int longitud;
	private String data;
	private boolean controlChar;
	private String inputBuffer = "";	

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.TAB)) {
			if (event.getSource() == txtPatente) {
				txtConductor.requestFocus();
				return;
			}
			if (event.getSource() == txtConductor) {
				txtNumDoc.requestFocus();
				return;
			}
			if (event.getSource() == txtNumDoc) {
				txtFactura.requestFocus();
				return;
			}

			if (event.getSource() == cbxProducto) {
				cbxTransporte.requestFocus();
				return;
			}
			if (event.getSource() == cbxTransporte) {
				txtObservaciones.requestFocus();
				return;
			}
			if (event.getSource() == txtObservaciones) {
				cbxCliente.requestFocus();
				return;
			}
			if (event.getSource() == cbxCliente) {
				cbxProcedencia.requestFocus();
				return;
			}

			if (event.getSource() == cbxProcedencia) {
				btnBuscar.requestFocus();
				return;
			}
		}
	}

	 @Override
	   public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

	@Override
	public void serialEvent(com.fazecast.jSerialComm.SerialPortEvent event) {
		if (isDebug) {
			logger.info("Es manual? " + (ingManual?"Si": "No"));
		}
		if (!ingManual) {
			if (isDebug) {
				logger.info("Rx -> EVENT " + (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE));
				logger.info("Rx -> EVENT type: " + event.getEventType() );					
			}
			//if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {
				try {					
					byte[] newData = event.getReceivedData();
					if(newData == null) {
						logger.info("Rx -> sin datos");
						return;
					}
					
					if (isDebug) {
						logger.info("Rx -> Received data of size: " + newData.length);											   
					}
					int available = newData.length;
					//for (int i = 0; i < available; i++) {// read all incoming characters
					int i= 0;
					 for (; ; ) {
						 char receivedVal;
						 try {
							 receivedVal = (char)newData[i];
						 }catch (ArrayIndexOutOfBoundsException e) {
							break;
						}
						// store it into an int (because of the input.read
																	// method
						longitud = this.posicionInicioDato + this.longitudDato;
						controlChar = false;
						if (caracterControl != null) {
							for (int x = 0; x < caracterControl.length; x++) {
								try {
									if (receivedVal == caracterControl[x].trim().charAt(0)) {
										controlChar = true;
										break;
									}
								} catch (StringIndexOutOfBoundsException e) {
									continue;
								}
							}
						}

						if (isDebug) {
							logger.info("Rx -> " + (char) receivedVal);
						}

						// Si es Inicio de trama o caracter de control limpio el buffer y sigo
						if (controlChar || receivedVal == SocketConnection.STX) {
							if (isDebug) {
								logger.info("Rx -> INICIO TRAZA");
							}
							inputBuffer = "";
							continue;
						}
						if (receivedVal != 10 && receivedVal != 13 && inputBuffer.length() <= longitud) {// if the
																											// character
																											// is not a
																											// new line
																											// "\n" and
																											// not a
																											// carriage
																											// return
							inputBuffer += (char) receivedVal; // store the new character into a buffer
							if (isDebug) {
								logger.info("Rx -> " + inputBuffer);
							}
						} else {// if it's a new line character
							data = "";
							data = inputBuffer;
							longitud = this.posicionInicioDato + this.longitudDato;
							if (data != null && longitud > data.length()) {
								longitud = data.length();
							}
							if (this.posicionInicioDato < data.length()) {
								data = data.substring(this.posicionInicioDato, longitud);
								sBufferConnection = data.trim().replaceAll("[^\\d.]", "");
								// limpio 0 adelante
								sBufferConnection = sBufferConnection.replaceFirst("^0+(?!$)", "");
								if (!sBufferConnection.isEmpty()
										&& !txtNumberSerial.getText().trim().equals(sBufferConnection.trim())) {
									try {
										Double.valueOf(sBufferConnection);										
										txtNumberSerial.setText(sBufferConnection);
									} catch (NumberFormatException e) {
										logger.error("ERROR DE CONVERSION. ", e);
									}
								}
								inputBuffer = "";// clear the buffer
								if (isDebug) {
									logger.info("Rx -> FIN TRAZA");
								}
							}
						}
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("IO Error Occurred: ", e);
				}
			//}
		}
		
	}

	@Override
	public void setDashboard(DashboardActions actions) {
		this.actions = actions;
	}
}

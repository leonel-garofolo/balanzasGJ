package com.balanzasgj.app.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.model.Client;
import com.balanzasgj.app.model.Comunication;
import com.balanzasgj.app.model.Entity;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.ImportAndExport;
import com.balanzasgj.app.model.Indicator;
import com.balanzasgj.app.model.Origin;
import com.balanzasgj.app.model.Patent;
import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.model.Transport;
import com.balanzasgj.app.model.User;
import com.balanzasgj.app.services.AtaService;
import com.balanzasgj.app.services.ClientService;
import com.balanzasgj.app.services.ComunicationService;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.services.ImportAndExportService;
import com.balanzasgj.app.services.IndicatorService;
import com.balanzasgj.app.services.OriginService;
import com.balanzasgj.app.services.PatentService;
import com.balanzasgj.app.services.ProductService;
import com.balanzasgj.app.services.TransportService;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.view.settings.RemitoView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ConfiguracionesController extends AnchorPane {

	public static final String CLIENTE = "CLIENTES";
	public static final String PROCEDENCIAS = "PROCEDENCIAS";
	public static final String PRODUCTOS = "PRODUCTOS";
	public static final String TRANSPORTES = "TRANSPORTES";
	public static final String IMPORTADORES = "IMPORTADORES/EXPORTADORES";
	public static final String ATA_TRANSPORTISTA = "ATA/TRANSPORTISTA ";
	public static final String PATENTES = "PATENTES";

	@FXML
	private TableView<Entity> tblEntidades;
	@FXML
	private TableColumn<Entity, Long> colCodigo;

	@FXML
	private TableColumn<Entity, String> colNombre;

	@FXML
	private TableColumn<Indicator, Long> colIndicadoresCodigo;
	@FXML
	private TableColumn<Indicator, String> colIndicadoresNombre;

	@FXML
	private Button btnNuevoEntidad;
	@FXML
	private Button btnEliminarEntidad;
	@FXML
	private TextField txtEntidadNombre;
	@FXML
	private Button btnAplicarEntidad;
	@FXML
	private ComboBox<String> cbxEntidades;
	@FXML
	private TextField txtPuerto;
	@FXML
	private ComboBox<Integer> cbxVelocidad;
	@FXML
	private ComboBox<Integer> cbxBitsDeDatos;
	@FXML
	private ComboBox<String> cbxParidad;
	@FXML
	private ComboBox<String> cbxBitsDeParada;
	@FXML
	private ComboBox<String> cbxControlDeFlujo;
	@FXML
	private TextField txtNombreIndicadores;	
	
	@FXML
	private ComboBox<Integer> cbxNroIndicador;
	@FXML
	private ComboBox<Indicator> cbxIndicador;
	@FXML
	private Button btnAplicarConecciones;
	@FXML
	private Button btnCancelarConecciones;
	@FXML
	private TableView<Indicator> tblIndicadores;
	@FXML
	private Button btnNuevoIndicador;
	@FXML
	private Button btnEliminarIndicador;
	@FXML
	private TextField txtEditPosicionControl;
	@FXML
	private TextField txtEditLongCaracterControl;
	@FXML
	private TextField txtEditCaracterControl;
	@FXML
	private TextField txtEditPosInicioDato;
	@FXML
	private TextField txtEditLongitudDato;
	@FXML
	private Button btnAplicarEditIndicacion;
	@FXML
	private Button btnCerrar;
	@FXML
	private TextArea txtIndicadorInfo;
	@FXML
	private CheckBox chkIndEje;
	
	@FXML
	private AnchorPane usuariosView;

	@FXML
	private Tab tabCom;

	@FXML
	private Tab tabInd;

	@FXML
	private Tab tabUsuarios;
	@FXML
	private Tab tabAduana;
	@FXML
	private Tab tabRemito;

	@FXML
	private TabPane tabPane;

	@FXML
	private Label lblNombre;

	@FXML
	private Label lblTara;
	@FXML
	private Label lblTaraValue;

	@FXML
	private Label lblFecha;
	@FXML
	private Label lblFechaValue;

	@FXML
	private Label lblCuitAlias;
	@FXML
	private Label lblMov;
	@FXML
	private Label lblAcum;

	@FXML
	private TextField txtEntidadCuitAlias;
	@FXML
	private TextField txtEntidadUltMov;
	@FXML
	private TextField txtEntidadAcumulado;

	@FXML
	private TextField txtCodAduana;
	@FXML
	private TextField txtCodLOT;
	@FXML
	private TextField txtCertHab;
	@FXML
	private TextField txtVenc;

	private ClientService clientService;
	private OriginService originService;
	private ProductService productService;
	private TransportService transportService;
	private IndicatorService indicatorService;
	private ComunicationService comunicationService;
	private ImportAndExportService importAndExportService;
	private AtaService ataService;
	private PatentService patentService;
	private GlobalParameterService globalParameterService;
	private boolean modoEditEntidades = false;
	private boolean modoEditIndicadores = false;

	@FXML
	private void handleAplicarAduana(ActionEvent event) {	
		saveFromTextComponent(GlobalParameter.A_CODIGO_ADUANA, txtCodAduana);
		saveFromTextComponent(GlobalParameter.A_CODIGO_LOG, txtCodLOT);
		saveFromTextComponent(GlobalParameter.A_CERTIFICADO, txtCertHab);
		saveFromTextComponent(GlobalParameter.A_VENCIMIENTO, txtVenc);

		Message.info("Los datos se guardaron correctamente.");
	}
	
	private void saveFromTextComponent(String param, TextField field) {
		if (field != null && field.getText() != null && !field.getText().isEmpty()) {			
			globalParameterService.save(param, field.getText());
		}
	}

	@FXML
	private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		((Stage) source.getScene().getWindow()).close();
	}

	@FXML
	private void handleSelectedEntidades(ActionEvent event) {		
		loadFormEntidades(this.cbxEntidades.getSelectionModel().getSelectedItem());
		switch (this.cbxEntidades.getSelectionModel().getSelectedItem()) {
		case PRODUCTOS:
			lblCuitAlias.setText("Alias");
			break;
		case CLIENTE:
			lblCuitAlias.setText("CUIT");
			break;

		default:
			lblCuitAlias.setText("CUIT");
			break;
		}
	}

	@FXML
	private void handleSelectedNroIndicador(ActionEvent event) {	
		if (cbxNroIndicador.getSelectionModel().getSelectedItem() != null) {
			setSelectedIndication();
			handleSelectedIndicador(event);
		}		
	}
	
	@FXML
	private void handleSelectedIndicador(ActionEvent event) {	
		if (cbxIndicador.getSelectionModel().getSelectedItem() == null) {
			txtIndicadorInfo.setText("");
		} else {
			Indicator indicador = cbxIndicador.getSelectionModel().getSelectedItem();
			String text = "CONFIGURACION INDICADOR DE PESO N° 2 \n";
			text += "Puerto: " + indicador.getPuerto() + "\n";
			text += "Velocidad: " + indicador.getVelocidad() + "\n";
			text += "Bits de Datos: " + indicador.getBitsDeDatos() + "\n";
			text += "Paridad: " + indicador.getParidad() + "\n";
			text += "Bits de Parada: " + indicador.getBitsDeParada() + "\n";
			text += "Control de Flujo : " + indicador.getControlDeFlujo() + "\n";
			text = text.replaceAll("null", "");
			txtIndicadorInfo.setText(text);
		}		
	}

	@FXML
	private void handleAplicarComunicaciones(ActionEvent event) {
		if (cbxNroIndicador.getValue() != null || 
				cbxIndicador.getValue() != null) {
			
			if(cbxIndicador.getValue() != null) {
				Comunication comun = new Comunication();
				comun.setIdcomunicaciones(cbxNroIndicador.getValue().longValue());
				comun.setNombre("INDICADOR " + cbxNroIndicador.getValue());
				comun.setIdindicadores(
						cbxIndicador.getSelectionModel().getSelectedItem().getIdindicadores().intValue());
				comunicationService.save(comun);
			}	
			Message.info("Se guardo correctamente.");
		} else {
			System.out.println("es necesario seleccionar indicador");
		}
	}

	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblEntidades.getSelectionModel().isEmpty()) {
			modoEditEntidades = true;
			if (tblEntidades.getSelectionModel().getSelectedItem() instanceof Patent) {
				Patent p = (Patent) tblEntidades.getSelectionModel().getSelectedItem();
				txtEntidadNombre.setText(p.getPatente());
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				txtEntidadCuitAlias.setText(String.valueOf(p.getTara()));
				txtEntidadUltMov.setText(sd.format(p.getUpdate()));
				txtEntidadAcumulado.setText(String.valueOf(p.getDiasVenc()));

			} else {
				txtEntidadNombre.setText(tblEntidades.getSelectionModel().getSelectedItem().getNombre());
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				switch (this.cbxEntidades.getSelectionModel().getSelectedItem()) {
				case CLIENTE:
					txtEntidadCuitAlias.setText(((Client)tblEntidades.getSelectionModel().getSelectedItem()).getCuit());
					break;
				case PRODUCTOS:
					txtEntidadCuitAlias.setText(((Product)tblEntidades.getSelectionModel().getSelectedItem()).getAlias());
					BigDecimal aProduct = ((Product)tblEntidades.getSelectionModel().getSelectedItem()).getAcumulado();
					txtEntidadAcumulado.setText(aProduct == null ? "" : aProduct.toString());
					break;
				case TRANSPORTES:	
					Transport t = (Transport)tblEntidades.getSelectionModel().getSelectedItem();
					Date dTransporte = t.getUltimoMovimiento();
					txtEntidadUltMov.setText(dTransporte==null ? "": sd.format(dTransporte));					
					break;
				case IMPORTADORES:
					ImportAndExport importExport = (ImportAndExport)tblEntidades.getSelectionModel().getSelectedItem();
					BigDecimal aImportAndExport = importExport.getAcumulado();
					txtEntidadAcumulado.setText(aImportAndExport == null ? "" : aImportAndExport.toString());
					Date dImpExp = importExport.getUltimoMovimiento();
					txtEntidadUltMov.setText(dImpExp==null ? "": sd.format(dImpExp));
					txtEntidadCuitAlias.setText(importExport.getCuit());
					break;
				case ATA_TRANSPORTISTA:
					Ata ata = (Ata)tblEntidades.getSelectionModel().getSelectedItem();
					BigDecimal aAta = ata.getAcumulado();
					txtEntidadAcumulado.setText(aAta == null ? "" : aAta.toString());
					Date dAta = ata.getUltimoMovimiento();
					txtEntidadUltMov.setText(dAta==null ? "": sd.format(dAta));
					txtEntidadCuitAlias.setText(ata.getCuit());
					break;
				
				default:				
					break;
				}								
			}
		}
	}

	@FXML
	private void handleTblIndicadoresSelected(MouseEvent event) {
		if (!tblIndicadores.getSelectionModel().isEmpty()) {
			modoEditIndicadores = true;
			txtNombreIndicadores.setText(tblIndicadores.getSelectionModel().getSelectedItem().getNombre());
			Indicator indicadores = indicatorService
					.findById(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
			txtEditPosicionControl.setText(indicadores.getPosicionCaracterControl().toString());
			txtEditLongCaracterControl.setText(indicadores.getLongitudCaracterControl().toString());
			txtEditCaracterControl.setText(indicadores.getCaracterControl());
			txtEditPosInicioDato.setText(indicadores.getPosicionInicioDato().toString());
			txtEditLongitudDato.setText(indicadores.getLongitudDato().toString());
			txtPuerto.setText(indicadores.getPuerto().toString());
			cbxVelocidad.setValue(indicadores.getVelocidad());
			cbxBitsDeDatos.setValue(indicadores.getBitsDeDatos());
			cbxParidad.setValue(indicadores.getParidad());
			cbxBitsDeParada.setValue(indicadores.getBitsDeParada());
			cbxControlDeFlujo.setValue(indicadores.getControlDeFlujo());
			chkIndEje.setSelected(indicadores.isEje());
		}
	}

	@FXML
	private void handleAplicarEntidades(ActionEvent event) {
		saveEntidades(this.cbxEntidades.getSelectionModel().getSelectedItem());
		txtEntidadNombre.requestFocus();
	}

	@FXML
	private void handleImportar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Indicadores files (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);
		File selectedDirectory = fileChooser.showOpenDialog((Stage) source.getScene().getWindow());

		if (selectedDirectory != null) {
			BufferedReader bufferedReader;
			try {
				bufferedReader = new BufferedReader(new FileReader(selectedDirectory.getAbsolutePath()));
				Gson gson = new Gson();
		        Object json = gson.fromJson(bufferedReader, Object.class);
		        ObjectMapper mapper = new ObjectMapper();
		        List<Indicator> indicadoresList = mapper.readValue(json.toString(), new TypeReference<List<Indicator>>(){});
		        for(Indicator i: indicadoresList) {
		    	   if(!indicatorService.existName(i.getNombre())) {
		    		   i.setIdindicadores(null);
		    		   indicatorService.save(i);
		    	   }
		        }
		        List<Indicator> indicadores = indicatorService.findAll();
		        tblIndicadores.getItems().clear();
				tblIndicadores.getItems().addAll(indicadores);		        
		        Message.info("Los indicadores se importaron correctamente.");
			} catch (IOException  e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void handleExportar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		FileChooser fileChooser = new FileChooser();
		// Set extension filter for text files
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Indicadores files (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);

		File fileJson = fileChooser.showSaveDialog((Stage) source.getScene().getWindow());
		try {
			if (fileJson != null && fileJson.createNewFile()) {
				Writer writer = new FileWriter(fileJson.getAbsolutePath());
				Gson gson = new GsonBuilder().setPrettyPrinting().create();

				String indicadoresStr = gson.toJson(tblIndicadores.getItems());
				gson.toJson(indicadoresStr, writer);
				writer.close();
				Message.info("Los indicadores se exportaron correctamente.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleNuevoEntidades(ActionEvent event) {
		cleanFormEntidades();
	}

	@FXML
	private void handleEliminarEntidades(ActionEvent event) {
		if (!cbxEntidades.getSelectionModel().isEmpty() && !tblEntidades.getSelectionModel().isEmpty()) {
			String entidadType = cbxEntidades.getSelectionModel().getSelectedItem();
			switch (entidadType) {
			case CLIENTE:
				clientService.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case PROCEDENCIAS:
				originService.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case PRODUCTOS:
				productService.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case TRANSPORTES:
				transportService.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case IMPORTADORES:
				importAndExportService
						.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case ATA_TRANSPORTISTA:
				ataService.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;

			case PATENTES:
				patentService
						.deleteById(((Patent) tblEntidades.getSelectionModel().getSelectedItem()).getPatente());
				break;
			default:
				break;
			}
			loadFormEntidades(entidadType);
		} else {

		}
	}

	@FXML
	private void handleNuevoIndicadores(ActionEvent event) {
		cleanFormIndicadores();
		loadFormIndicadores();
	}

	@FXML
	private void handleEliminarIndicadores(ActionEvent event) {
		if (!tblIndicadores.getSelectionModel().isEmpty()) {
			indicatorService.deleteById(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
			loadFormIndicadores();
		}
	}

	@FXML
	private void handleAplicarIndicadores(ActionEvent event) {
		saveIndicadores();
	}

	private void loadFormEntidades(String entidadType) {
		cleanFormEntidades();
		colNombre.setText("Nombre");
		switch (entidadType) {
		case CLIENTE:
			tblEntidades.getItems().addAll(clientService.findAll());
			setVisibleAduana(false);
			lblCuitAlias.setVisible(true);
			lblCuitAlias.setText("Cuit");
			txtEntidadCuitAlias.setVisible(true);
			break;
		case PROCEDENCIAS:
			tblEntidades.getItems().addAll(originService.findAll());
			setVisibleAduana(false);
			break;
		case PRODUCTOS:
			tblEntidades.getItems().addAll(productService.findAll());
			setVisibleAduana(true);
			break;
		case TRANSPORTES:
			tblEntidades.getItems().addAll(transportService.findAll());
			setVisibleAduana(true);
			break;
		case IMPORTADORES:
			tblEntidades.getItems().addAll(importAndExportService.findAll());
			setVisibleAduana(true);
			break;
		case ATA_TRANSPORTISTA:
			tblEntidades.getItems().addAll(ataService.findAll());
			setVisibleAduana(true);
			break;
		case PATENTES:
			tblEntidades.getItems().addAll(patentService.findAll());
			setVisiblePatente(true);
			colNombre.setText("Patentes");
			break;
		default:
			break;
		}
	}

	private void setVisiblePatente(boolean isVisible) {
		lblNombre.setText("Patente");
		txtEntidadNombre.setDisable(true);
		lblCuitAlias.setVisible(isVisible);
		lblCuitAlias.setText("Tara");
		lblMov.setVisible(isVisible);
		lblMov.setText("Ultima Toma");
		lblAcum.setVisible(isVisible);
		lblAcum.setText("Días");

		txtEntidadCuitAlias.setVisible(isVisible);
		txtEntidadUltMov.setVisible(isVisible);
		txtEntidadAcumulado.setVisible(isVisible);
		txtEntidadAcumulado.setDisable(false);

	}

	private void setVisibleAduana(boolean isVisible) {
		lblNombre.setText("Nombre");
		txtEntidadNombre.setDisable(false);
		lblCuitAlias.setVisible(isVisible);
		lblCuitAlias.setText("Cuit");
		lblMov.setVisible(isVisible);
		lblMov.setText("Ultimo Movimiento");
		lblAcum.setVisible(isVisible);
		lblAcum.setText("Acumulado");

		txtEntidadCuitAlias.setVisible(isVisible);
		txtEntidadUltMov.setVisible(isVisible);
		txtEntidadAcumulado.setVisible(isVisible);
		txtEntidadAcumulado.setDisable(true);

	}

	private void loadFormIndicadores() {
		cleanFormIndicadores();
		List<Indicator> indicadores = indicatorService.findAll();
		tblIndicadores.getItems().addAll(indicadores);
		
		cbxNroIndicador.getItems().clear();				
		String pg = globalParameterService.get(GlobalParameter.P_NUM_BALANZAS);
		if(pg.isEmpty()) {
			cbxNroIndicador.getItems().add(new Integer(1));			
		} else {
			Integer indCount = Integer.valueOf(pg);
			for(int i = 1; i <= indCount.intValue(); i++) {
				cbxNroIndicador.getItems().add(new Integer(i));			
			}
		}	
		cbxNroIndicador.setValue(1);
		
		cbxIndicador.getItems().clear();
		cbxIndicador.getItems().addAll(indicadores);	
		
		setSelectedIndication();
		handleSelectedIndicador(null);	
	}
	
	private void setSelectedIndication() {
		cbxIndicador.setValue(null);
		List<Comunication> all = comunicationService.findAll();
		for (Comunication c : all) {
			if(cbxNroIndicador.getSelectionModel().getSelectedItem().intValue() == c.getIdcomunicaciones()) {
				Indicator i = indicatorService.findById((long) c.getIdindicadores());
				cbxIndicador.setValue(i);
				break;
			}				
		}	
	}

	private void cleanFormEntidades() {
		this.modoEditEntidades = false;
		tblEntidades.getItems().clear();
		txtEntidadNombre.setText("");
		txtEntidadCuitAlias.setText("");
		txtEntidadUltMov.setText("");
		txtEntidadAcumulado.setText("");
	}

	private void cleanFormIndicadores() {
		this.modoEditIndicadores = false;
		tblIndicadores.getItems().clear();
		txtNombreIndicadores.setText("");
		txtEditPosicionControl.setText("");
		txtEditLongCaracterControl.setText("");
		txtEditCaracterControl.setText("");
		txtEditPosInicioDato.setText("");
		txtEditLongitudDato.setText("");
	}

	private void saveEntidades(String entidadType) {
		String nombre = txtEntidadNombre.getText();
		if (nombre.equals("")) {

		} else {
			switch (entidadType) {
			case CLIENTE:
				Client cli = new Client();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					cli.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				cli.setNombre(nombre);
				cli.setCuit(txtEntidadCuitAlias.getText());
				clientService.save(cli);
				this.loadFormEntidades(entidadType);
				break;
			case PROCEDENCIAS:
				Origin pro = new Origin();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					pro.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				pro.setNombre(nombre);
				originService.save(pro);
				break;
			case PRODUCTOS:
				Product producto = new Product();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					producto.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}

				producto.setNombre(nombre);
				producto.setAlias(txtEntidadCuitAlias.getText());
				productService.save(producto);
				break;
			case TRANSPORTES:
				Transport tras = new Transport();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					tras.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				tras.setNombre(nombre);
				tras.setCuit(txtEntidadCuitAlias.getText());
				transportService.save(tras);
				break;
			case IMPORTADORES:
				ImportAndExport ie = new ImportAndExport();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					ie.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				ie.setNombre(nombre);
				ie.setCuit(txtEntidadCuitAlias.getText());
				importAndExportService.save(ie);
				break;
			case ATA_TRANSPORTISTA:
				Ata ata = new Ata();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					ata.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				ata.setNombre(nombre);
				ata.setCuit(txtEntidadCuitAlias.getText());
				ataService.save(ata);
				break;
			case PATENTES:
				Patent p = new Patent();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					p.setPatente(((Patent) tblEntidades.getSelectionModel().getSelectedItem()).getPatente());
				}
				p.setTara(Double.valueOf(txtEntidadCuitAlias.getText()));
				p.setDiasVenc(Integer.valueOf(txtEntidadAcumulado.getText()));
				patentService.save(p);
				break;

			default:
				break;
			}
			this.loadFormEntidades(entidadType);
		}
	}

	private void saveIndicadores() {
		Indicator indicadores = new Indicator();
		indicadores.setNombre(txtNombreIndicadores.getText());
		if (txtEditPosicionControl.getText() != null) {
			indicadores.setPosicionCaracterControl(Integer.valueOf(txtEditPosicionControl.getText()));
		}

		if (txtEditLongCaracterControl.getText() != null) {
			indicadores.setLongitudCaracterControl(Integer.valueOf(txtEditLongCaracterControl.getText()));
		}

		if (txtEditCaracterControl.getText() != null) {
			indicadores.setCaracterControl(txtEditCaracterControl.getText());
		}

		indicadores.setPosicionInicioDato(Integer.valueOf(txtEditPosInicioDato.getText()));
		indicadores.setLongitudDato(Integer.valueOf(txtEditLongitudDato.getText()));
		indicadores.setPuerto(Integer.valueOf(txtPuerto.getText()));
		indicadores.setVelocidad(cbxVelocidad.getValue());
		indicadores.setBitsDeDatos(cbxBitsDeDatos.getValue());
		indicadores.setParidad(cbxParidad.getValue());
		indicadores.setBitsDeParada(cbxBitsDeParada.getValue());
		indicadores.setControlDeFlujo(cbxControlDeFlujo.getValue());
		indicadores.setEje(chkIndEje.isSelected());
		if (modoEditIndicadores && !tblIndicadores.getSelectionModel().isEmpty()) {
			indicadores.setIdindicadores(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
		}
		indicatorService.save(indicadores);

		loadFormIndicadores();
	}

	public void initialize() {
		switch (User.getPerfilLogeado()) {
		case User.P_SUPERVISOR:
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			break;
		case User.P_OPERADOR:
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			tabUsuarios.setDisable(true);
			tabAduana.setDisable(true);
			tabRemito.setDisable(true);
			break;
		default:
			break;
		}

		initPersistence();
		initTableView();
		initComunicaciones();

		this.cbxEntidades.getItems().addAll(new String[] { CLIENTE, PROCEDENCIAS, PRODUCTOS, TRANSPORTES, IMPORTADORES,
				ATA_TRANSPORTISTA, PATENTES });
		initTextUpperCase();

		// cargo los datos de los indicadores del sistema
		loadFormIndicadores();
		initRemitoView();
	}

	private void initTextUpperCase() {
		txtEntidadNombre.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEntidadNombre.setText(newValue.toUpperCase());
		});
		txtNombreIndicadores.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNombreIndicadores.setText(newValue.toUpperCase());
		});
		txtEditPosicionControl.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEditPosicionControl.setText(newValue.toUpperCase());
		});

		txtEditPosicionControl.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtEditPosicionControl.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		txtEditLongCaracterControl.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEditLongCaracterControl.setText(newValue.toUpperCase());
		});
		txtEditLongCaracterControl.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtEditLongCaracterControl.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		txtEditCaracterControl.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEditCaracterControl.setText(newValue.toUpperCase());
		});

		txtEditPosInicioDato.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEditPosInicioDato.setText(newValue.toUpperCase());
		});
		txtEditPosInicioDato.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtEditPosInicioDato.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		txtEditLongitudDato.textProperty().addListener((ov, oldValue, newValue) -> {
			txtEditLongitudDato.setText(newValue.toUpperCase());
		});
		txtEditLongitudDato.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtEditLongitudDato.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		txtPuerto.textProperty().addListener((ov, oldValue, newValue) -> {
			txtPuerto.setText(newValue.toUpperCase());
		});
		txtPuerto.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					txtPuerto.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		txtCodAduana.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtCodAduana.setText(newValue.toUpperCase());
			}
		});

		txtCodLOT.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtCodLOT.setText(newValue.toUpperCase());
			}
		});

		txtCertHab.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtCertHab.setText(newValue.toUpperCase());
			}
		});

		txtVenc.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtVenc.setText(newValue.toUpperCase());
			}
		});

		
		txtCodAduana.setText(globalParameterService.get(GlobalParameter.A_CODIGO_ADUANA));	
		txtCodLOT.setText(globalParameterService.get(GlobalParameter.A_CODIGO_LOG));
		txtCertHab.setText(globalParameterService.get(GlobalParameter.A_CERTIFICADO));		
		txtVenc.setText(globalParameterService.get(GlobalParameter.A_VENCIMIENTO));
	}
	
	private void initRemitoView() {		
		tabRemito.setContent(new RemitoView().build());
	}

	private void initPersistence() {
		this.globalParameterService = new GlobalParameterService();
		this.clientService = new ClientService();
		this.originService = new OriginService();
		this.productService = new ProductService();
		this.transportService = new TransportService();
		this.indicatorService = new IndicatorService();
		this.comunicationService = new ComunicationService();
		this.importAndExportService = new ImportAndExportService();
		this.patentService = new PatentService();
		this.ataService = new AtaService();
	}

	private void initComunicaciones() {
		cbxVelocidad.getItems().addAll(new Integer[] { 1200, 2400, 4800, 9600, 19200 });
		cbxBitsDeDatos.getItems().addAll(new Integer[] { 7, 8 });
		cbxParidad.getItems().addAll(new String[] { "n", "e", "o" });
		cbxBitsDeParada.getItems().addAll(new String[] { "1", "1.5", "2" });
		cbxControlDeFlujo.getItems().addAll(new String[] { "0 - comNone", "1 - comXOnXoff", "2 - comRTS" });
	}

	private void initTableView() {
		colCodigo.setCellValueFactory(cellData -> new ObservableValueBase<Long>() {

			@Override
			public Long getValue() {
				return cellData.getValue().getCodigo();
			}

		});

		colNombre.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				if (cellData.getValue() instanceof Patent) {
					return ((Patent) cellData.getValue()).getPatente();
				} else {
					return cellData.getValue().getNombre().toString();
				}

			}

		});
		colIndicadoresCodigo.setCellValueFactory(cellData -> new ObservableValueBase<Long>() {

			@Override
			public Long getValue() {
				return cellData.getValue().getIdindicadores();
			}

		});
		colIndicadoresNombre.setCellValueFactory(cellData -> new ObservableValueBase<String>() {

			@Override
			public String getValue() {
				return cellData.getValue().getNombre();
			}
		});
	}
}

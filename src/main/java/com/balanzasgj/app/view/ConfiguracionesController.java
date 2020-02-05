package com.balanzasgj.app.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.List;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Comunicaciones;
import com.balanzasgj.app.model.Entidades;
import com.balanzasgj.app.model.ImportadoresExportadores;
import com.balanzasgj.app.model.Indicadores;
import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.model.Patentes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.AtaPersistence;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.ImportadoresExportadoresPersistence;
import com.balanzasgj.app.persistence.IndicadoresPersistence;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.PatentesPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.AtaPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ImportadoresExportadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.IndicadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.PatentesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProcedenciasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TransportesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
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
	private TableView<Entidades> tblEntidades;
	@FXML
	private TableColumn<Entidades, Long> colCodigo;

	@FXML
	private TableColumn<Entidades, String> colNombre;

	@FXML
	private TableColumn<Indicadores, Long> colIndicadoresCodigo;
	@FXML
	private TableColumn<Indicadores, String> colIndicadoresNombre;

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
	private ComboBox<Indicadores> cbxIndicador;
	@FXML
	private Button btnAplicarConecciones;
	@FXML
	private Button btnCancelarConecciones;
	@FXML
	private TableView<Indicadores> tblIndicadores;
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

	private ClientesPersistence clientesPersistence;
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private IndicadoresPersistence indicadoresPersistence;
	private ComunicacionesPersistence comunicacionesPersistence;
	private ImportadoresExportadoresPersistence importadoresExportadoresPersistence;
	private AtaPersistence ataPersistence;
	private PatentesPersistence patentesPersistence;
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	private boolean modoEditEntidades = false;
	private boolean modoEditIndicadores = false;

	@FXML
	private void handleAplicarAduana(ActionEvent event) {
		ParametrosGlobales pg = new ParametrosGlobales();
		if (txtCodAduana != null && txtCodAduana.getText() != null && !txtCodAduana.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.A_CODIGO_ADUANA);
			pg.setValue(txtCodAduana.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		pg = new ParametrosGlobales();
		if (txtCodLOT != null && txtCodLOT.getText() != null && !txtCodLOT.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.A_CODIGO_LOG);
			pg.setValue(txtCodLOT.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		pg = new ParametrosGlobales();
		if (txtCertHab != null && txtCertHab.getText() != null && !txtCertHab.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.A_CERTIFICADO);
			pg.setValue(txtCertHab.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		pg = new ParametrosGlobales();
		if (txtVenc != null && txtVenc.getText() != null && !txtVenc.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.A_VENCIMIENTO);
			pg.setValue(txtVenc.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		Message.info("Los datos se guardaron correctamente.");
	}

	@FXML
	private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		((Stage) source.getScene().getWindow()).close();
	}

	@FXML
	private void handleSelectedEntidades(ActionEvent event) {
		if (this.cbxEntidades.getSelectionModel().getSelectedItem().equals(PRODUCTOS)) {
			lblCuitAlias.setText("Alias");
		} else {
			lblCuitAlias.setText("CUIT");
		}
		loadFormEntidades(this.cbxEntidades.getSelectionModel().getSelectedItem());

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
			Indicadores indicador = cbxIndicador.getSelectionModel().getSelectedItem();
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
				Comunicaciones comun = new Comunicaciones();
				comun.setIdcomunicaciones(cbxNroIndicador.getValue().longValue());
				comun.setNombre("INDICADOR " + cbxNroIndicador.getValue());
				comun.setIdindicadores(
						cbxIndicador.getSelectionModel().getSelectedItem().getIdindicadores().intValue());
				comunicacionesPersistence.save(comun);
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
			if (tblEntidades.getSelectionModel().getSelectedItem() instanceof Patentes) {
				Patentes p = (Patentes) tblEntidades.getSelectionModel().getSelectedItem();
				txtEntidadNombre.setText(p.getPatente());
				SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				txtEntidadCuitAlias.setText(String.valueOf(p.getTara()));
				txtEntidadUltMov.setText(sd.format(p.getUpdate()));
				txtEntidadAcumulado.setText(String.valueOf(p.getDiasVenc()));

			} else {
				txtEntidadNombre.setText(tblEntidades.getSelectionModel().getSelectedItem().getNombre());
				if (this.cbxEntidades.getSelectionModel().getSelectedItem().equals(PRODUCTOS)) {
					txtEntidadCuitAlias.setText(tblEntidades.getSelectionModel().getSelectedItem().getAlias());
				} else {
					txtEntidadCuitAlias.setText(tblEntidades.getSelectionModel().getSelectedItem().getCuit());
				}

				if (tblEntidades.getSelectionModel().getSelectedItem().getUltimoMovimiento() != null) {
					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					txtEntidadUltMov.setText(
							sd.format(tblEntidades.getSelectionModel().getSelectedItem().getUltimoMovimiento()));
				}
				if (tblEntidades.getSelectionModel().getSelectedItem().getAcumulado() != null) {
					txtEntidadAcumulado
							.setText(tblEntidades.getSelectionModel().getSelectedItem().getAcumulado().toString());
				}
			}
		}
	}

	@FXML
	private void handleTblIndicadoresSelected(MouseEvent event) {
		if (!tblIndicadores.getSelectionModel().isEmpty()) {
			modoEditIndicadores = true;
			txtNombreIndicadores.setText(tblIndicadores.getSelectionModel().getSelectedItem().getNombre());
			Indicadores indicadores = indicadoresPersistence
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
		        List<Indicadores> indicadoresList = mapper.readValue(json.toString(), new TypeReference<List<Indicadores>>(){});
		        for(Indicadores i: indicadoresList) {
		    	   if(!indicadoresPersistence.existName(i.getNombre())) {
		    		   i.setIdindicadores(null);
		    		   indicadoresPersistence.save(i);
		    	   }
		        }
		        List<Indicadores> indicadores = indicadoresPersistence.findAll();
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
				clientesPersistence.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case PROCEDENCIAS:
				procedenciasPersistence.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case PRODUCTOS:
				productosPersistence.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case TRANSPORTES:
				transportesPersistence.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case IMPORTADORES:
				importadoresExportadoresPersistence
						.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;
			case ATA_TRANSPORTISTA:
				ataPersistence.deleteById(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				break;

			case PATENTES:
				patentesPersistence
						.deleteById(((Patentes) tblEntidades.getSelectionModel().getSelectedItem()).getPatente());
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
			indicadoresPersistence.deleteById(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
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
			tblEntidades.getItems().addAll(clientesPersistence.findAll());
			setVisibleAduana(false);
			break;
		case PROCEDENCIAS:
			tblEntidades.getItems().addAll(procedenciasPersistence.findAll());
			setVisibleAduana(false);
			break;
		case PRODUCTOS:
			tblEntidades.getItems().addAll(productosPersistence.findAll());
			setVisibleAduana(true);
			break;
		case TRANSPORTES:
			tblEntidades.getItems().addAll(transportesPersistence.findAll());
			setVisibleAduana(true);
			break;
		case IMPORTADORES:
			tblEntidades.getItems().addAll(importadoresExportadoresPersistence.findAll());
			setVisibleAduana(true);
			break;
		case ATA_TRANSPORTISTA:
			tblEntidades.getItems().addAll(ataPersistence.findAll());
			setVisibleAduana(true);
			break;
		case PATENTES:
			tblEntidades.getItems().addAll(patentesPersistence.findAll());
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
		List<Indicadores> indicadores = indicadoresPersistence.findAll();
		tblIndicadores.getItems().addAll(indicadores);
		
		cbxNroIndicador.getItems().clear();		
		ParametrosGlobales pg = new ParametrosGlobales();	
		pg.setId(ParametrosGlobales.P_NUM_BALANZAS);
		parametrosGlobalesPersistence.load(pg);
		if(pg.getValue() == null) {
			cbxNroIndicador.getItems().add(new Integer(1));			
		} else {
			Integer indCount = Integer.valueOf(pg.getValue());
			for(int i = 1; i < indCount.intValue(); i++) {
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
		List<Comunicaciones> all = comunicacionesPersistence.findAll();
		for (Comunicaciones c : all) {
			if(cbxNroIndicador.getSelectionModel().getSelectedItem().intValue() == c.getIdcomunicaciones()) {
				Indicadores i = indicadoresPersistence.findById((long) c.getIdindicadores());
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
				Clientes cli = new Clientes();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					cli.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				cli.setNombre(nombre);
				clientesPersistence.save(cli);
				this.loadFormEntidades(entidadType);
				break;
			case PROCEDENCIAS:
				Procedencias pro = new Procedencias();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					pro.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				pro.setNombre(nombre);
				procedenciasPersistence.save(pro);
				break;
			case PRODUCTOS:
				Productos producto = new Productos();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					producto.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}

				producto.setNombre(nombre);
				producto.setAlias(txtEntidadCuitAlias.getText());
				productosPersistence.save(producto);
				break;
			case TRANSPORTES:
				Transportes tras = new Transportes();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					tras.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				tras.setNombre(nombre);
				tras.setCuit(txtEntidadCuitAlias.getText());
				transportesPersistence.save(tras);
				break;
			case IMPORTADORES:
				ImportadoresExportadores ie = new ImportadoresExportadores();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					ie.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				ie.setNombre(nombre);
				ie.setCuit(txtEntidadCuitAlias.getText());
				importadoresExportadoresPersistence.save(ie);
				break;
			case ATA_TRANSPORTISTA:
				Ata ata = new Ata();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					ata.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				ata.setNombre(nombre);
				ata.setCuit(txtEntidadCuitAlias.getText());
				ataPersistence.save(ata);
				break;
			case PATENTES:
				Patentes p = new Patentes();
				if (modoEditEntidades && tblEntidades.getSelectionModel().getSelectedItem() != null) {
					p.setPatente(((Patentes) tblEntidades.getSelectionModel().getSelectedItem()).getPatente());
				}
				p.setTara(Double.valueOf(txtEntidadCuitAlias.getText()));
				p.setDiasVenc(Integer.valueOf(txtEntidadAcumulado.getText()));
				patentesPersistence.save(p);
				break;

			default:
				break;
			}
			this.loadFormEntidades(entidadType);
		}

	}

	private void saveIndicadores() {
		Indicadores indicadores = new Indicadores();
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
		if (modoEditIndicadores && !tblIndicadores.getSelectionModel().isEmpty()) {
			indicadores.setIdindicadores(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
		}
		indicadoresPersistence.save(indicadores);

		loadFormIndicadores();
	}

	public void initialize() {
		switch (Usuarios.getPerfilLogeado()) {
		case Usuarios.P_SUPERVISOR:
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			break;
		case Usuarios.P_OPERADOR:
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			tabUsuarios.setDisable(true);
			tabAduana.setDisable(true);
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

		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.A_CODIGO_ADUANA);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtCodAduana.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.A_CODIGO_LOG);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtCodLOT.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.A_CERTIFICADO);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtCertHab.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.A_VENCIMIENTO);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtVenc.setText(pg.getValue());
		}
	}

	private void initPersistence() {
		this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.indicadoresPersistence = new IndicadoresPersistenceJdbc();
		this.comunicacionesPersistence = new ComunicacionesPersistenceJdbc();
		this.importadoresExportadoresPersistence = new ImportadoresExportadoresPersistenceJdbc();
		this.patentesPersistence = new PatentesPersistenceJdbc();
		this.ataPersistence = new AtaPersistenceJdbc();
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
				if (cellData.getValue() instanceof Patentes) {
					return ((Patentes) cellData.getValue()).getPatente();
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

package com.balanzasgj.app.view;

import java.util.List;

import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Comunicaciones;
import com.balanzasgj.app.model.Entidades;
import com.balanzasgj.app.model.Indicadores;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ClientesPersistence;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.IndicadoresPersistence;
import com.balanzasgj.app.persistence.ProcedenciasPersistence;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.TransportesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ClientesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicacionesPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.IndicadoresPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProcedenciasPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.ProductosPersistenceJdbc;
import com.balanzasgj.app.persistence.impl.jdbc.TransportesPersistenceJdbc;

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
import javafx.stage.Stage;

public class ConfiguracionesController extends AnchorPane {
	
	private static final String CLIENTE = "CLIENTES";		
	private static final String PROCEDENCIAS = "PROCEDENCIAS";
	private static final String PRODUCTOS = "PRODUCTOS";
	private static final String TRANSPORTES = "TRANSPORTES";
	
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
	private ComboBox<Comunicaciones> cbxIndicadores;
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
	private ComboBox<Indicadores> cbxIndicadorConfig;
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
	
		
	private ClientesPersistence clientesPersistence;	
	private ProcedenciasPersistence procedenciasPersistence;
	private ProductosPersistence productosPersistence;
	private TransportesPersistence transportesPersistence;
	private IndicadoresPersistence indicadoresPersistence;
	private ComunicacionesPersistence comunicacionesPersistence;
	private boolean modoEditEntidades = false;
	private boolean modoEditIndicadores = false;


	@FXML
	private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		((Stage) source.getScene().getWindow()).close();
	}

	@FXML
	private void handleSelectedEntidades(ActionEvent event) {
		loadFormEntidades(this.cbxEntidades.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	private void handleComunicaciones(ActionEvent event) {
		if(!cbxIndicadores.getSelectionModel().isEmpty()) {
			Comunicaciones comun = cbxIndicadores.getSelectionModel().getSelectedItem();
			if(comun.getIdindicadores() != null) {
				Indicadores indicador = indicadoresPersistence.findById(Long.valueOf(comun.getIdindicadores()));
				cbxIndicadorConfig.setValue(indicador);
			}else {
				cbxIndicadorConfig.setValue(null);
			}
		}
	}
	
	@FXML
	private void handleSelectedIndicador(ActionEvent event) {
		if(cbxIndicadorConfig.getValue() == null) {
			txtIndicadorInfo.setText("");
		}else {
			Indicadores indicador = cbxIndicadorConfig.getSelectionModel().getSelectedItem();
			String text = "CONFIGURACION INDICADOR DE PESO N° 1 \n";
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
		if(cbxIndicadores.getValue() != null
				&& cbxIndicadorConfig.getValue() != null) {			
			Comunicaciones comun = cbxIndicadores.getSelectionModel().getSelectedItem();
			comun.setIdindicadores(cbxIndicadorConfig.getSelectionModel().getSelectedItem().getIdindicadores().intValue());
			comunicacionesPersistence.save(comun);
			cleanComnunicaciones();
		}else {
			System.out.println("es necesario seleccionar indicodpres");
		}
	}
	
	private void cleanComnunicaciones() {
		cbxIndicadores.setValue(null);
		cbxIndicadorConfig.setValue(null);
		txtIndicadorInfo.setText("");
	}

	@FXML
	private void handleTblEntidadesSelected(MouseEvent event) {
		if (!tblEntidades.getSelectionModel().isEmpty()) {
			modoEditEntidades=true;
			txtEntidadNombre.setText(tblEntidades.getSelectionModel().getSelectedItem().getNombre());			
		}
	}
	
	@FXML
	private void handleTblIndicadoresSelected(MouseEvent event) {
		if (!tblIndicadores.getSelectionModel().isEmpty()) {
			modoEditIndicadores=true;
			txtNombreIndicadores.setText(tblIndicadores.getSelectionModel().getSelectedItem().getNombre());
			Indicadores indicadores = indicadoresPersistence.findById(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());			
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
	private void handleNuevoEntidades(ActionEvent event) {
		this.modoEditEntidades=false;
		txtEntidadNombre.setText("");
		txtEntidadNombre.requestFocus();
	}
	
	@FXML
	private void handleEliminarEntidades(ActionEvent event) {
		if (!cbxEntidades.getSelectionModel().isEmpty()
				&& !tblEntidades.getSelectionModel().isEmpty()) {
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
			default:
				break;
			}
			loadFormEntidades(entidadType);
		}else {
			
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
		switch (entidadType) {
		case CLIENTE:
			tblEntidades.getItems().addAll(clientesPersistence.findAll());
			break;
		case PROCEDENCIAS:
			tblEntidades.getItems().addAll(procedenciasPersistence.findAll());
			break;
		case PRODUCTOS:
			tblEntidades.getItems().addAll(productosPersistence.findAll());
			break;
		case TRANSPORTES:
			tblEntidades.getItems().addAll(transportesPersistence.findAll());
			break;
		default:
			break;
		}
	}
	
	private void loadFormIndicadores() {
		cleanFormIndicadores();
		List<Indicadores> indicadores = indicadoresPersistence.findAll();
		tblIndicadores.getItems().addAll(indicadores);
		cbxIndicadorConfig.getItems().clear();
		cbxIndicadorConfig.getItems().addAll(indicadores);
	}

	private void cleanFormEntidades() {
		this.modoEditEntidades=false;
		tblEntidades.getItems().clear();
		txtEntidadNombre.setText("");		
	}
	
	private void cleanFormIndicadores() {
		this.modoEditIndicadores=false;				
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
		if(nombre.equals("")) {
			
		}else {
			switch (entidadType) {
			case CLIENTE:
				Clientes cli = new Clientes();
				if(modoEditEntidades) {
					cli.setCodigo(tblEntidades.getSelectionModel().getSelectedItem().getCodigo());
				}
				cli.setNombre(nombre);
				clientesPersistence.save(cli);
				this.loadFormEntidades(entidadType);
				break;
			case PROCEDENCIAS:
				Procedencias pro  = new Procedencias();
				pro.setNombre(nombre);
				procedenciasPersistence.save(pro);			
				break;
			case PRODUCTOS:
				Productos producto = new Productos();
				producto.setNombre(nombre);				
				productosPersistence.save(producto);
				break;
			case TRANSPORTES:			
				Transportes tras = new Transportes();
				tras.setNombre(nombre);
				transportesPersistence.save(tras);
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
		indicadores.setPosicionCaracterControl(Integer.valueOf(txtEditPosicionControl.getText()));
		indicadores.setLongitudCaracterControl(Integer.valueOf(txtEditLongCaracterControl.getText()));
		indicadores.setCaracterControl(txtEditCaracterControl.getText());
		indicadores.setPosicionInicioDato(Integer.valueOf(txtEditPosInicioDato.getText()));
		indicadores.setLongitudDato(Integer.valueOf(txtEditLongitudDato.getText()));
		indicadores.setPuerto(Integer.valueOf(txtPuerto.getText()));
		indicadores.setVelocidad(cbxVelocidad.getValue());
		indicadores.setBitsDeDatos(cbxBitsDeDatos.getValue());
		indicadores.setParidad(cbxParidad.getValue());
		indicadores.setBitsDeParada(cbxBitsDeParada.getValue());
		indicadores.setControlDeFlujo(cbxControlDeFlujo.getValue());
		if(modoEditIndicadores
				&& !tblIndicadores.getSelectionModel().isEmpty()) {
			indicadores.setIdindicadores(tblIndicadores.getSelectionModel().getSelectedItem().getIdindicadores());
		}
		indicadoresPersistence.save(indicadores);
		
		loadFormIndicadores();
	}


	public void initialize() {
		lblTara.setVisible(false);
		lblTaraValue.setVisible(false);
		lblFecha.setVisible(false);
		lblFechaValue.setVisible(false);
		
		switch (Usuarios.getPerfilLogeado()) {
		case Usuarios.P_SUPERVISOR:
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			break;
		case Usuarios.P_OPERADOR:			
			tabCom.setDisable(true);
			tabInd.setDisable(true);
			tabUsuarios.setDisable(true);
			break;
		default:
			break;
		}
		
		initPersistence();
		initTableView();
		initComunicaciones();

		this.cbxEntidades.getItems().addAll(new String[] { 
				CLIENTE,
				PROCEDENCIAS, 
				PRODUCTOS, 
				TRANSPORTES });
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
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
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
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
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
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
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
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
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
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	txtPuerto.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
	}

	private void initPersistence() {
		this.clientesPersistence = new ClientesPersistenceJdbc();
		this.procedenciasPersistence = new ProcedenciasPersistenceJdbc();
		this.productosPersistence = new ProductosPersistenceJdbc();
		this.transportesPersistence = new TransportesPersistenceJdbc();
		this.indicadoresPersistence = new IndicadoresPersistenceJdbc();
		this.comunicacionesPersistence = new ComunicacionesPersistenceJdbc();
	}

	private void initComunicaciones() {		
		this.cbxIndicadores.getItems().addAll(comunicacionesPersistence.findAll());		
		cbxVelocidad.getItems().addAll(new Integer[] {1200,2400,4800,9600,19200});		
		cbxBitsDeDatos.getItems().addAll(new Integer[] {7,8});
		cbxParidad.getItems().addAll(new String[] {"n","e","o"});
		cbxBitsDeParada.getItems().addAll(new String[] {"1", "1.5", "2"});
		cbxControlDeFlujo.getItems().addAll(
				new String[] {
						"0 - comNone", 
						"1 - comXOnXoff", 
						"2 - comRTS"});
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
				return cellData.getValue().getNombre().toString();
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

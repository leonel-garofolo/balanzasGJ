package com.balanzasgj.app.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.javafx.controls.customs.StringField;

import com.balanzasgj.app.db.UpdateDB;
import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.Utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

public class HerramientasController extends AnchorPane implements IView {
	final static Logger logger = Logger.getLogger(HerramientasController.class);
	@FXML
	private StringField txtNombreEmpresa;
	@FXML
	private StringField txtTransaccion;

	@FXML
	private Button btnGenerar;
	@FXML
	private Button btnRestaurar;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCerrar;

	@FXML
	private TextField txtBkpAuto;

	@FXML
	private TextField txtPathBkp;

	@FXML
	private TextField txtPathRst;
	@FXML
	private ImageView imgEmpresa;

	@FXML
	private StringField txtDireccion;

	@FXML
	private StringField txtTel;
	
	@FXML
	private StringField txtEmail;	

	@FXML
	private StringField txtLocalidad;

	@FXML
	private StringField txtProv;

	@FXML
	private StringField txtNombreBalanza;

	@FXML
	private StringField txtDireccionBalanza;

	@FXML
	private StringField txtTelBalanza;
	
	@FXML
	private StringField txtEmailBalanza;

	@FXML
	private StringField txtLocalidadBalanza;

	@FXML
	private StringField txtProvBalanza;

	@FXML
	private PasswordField txtClaveIngManual;

	@FXML
	private CheckBox chkActivarDebug;

	@FXML
	private CheckBox chkticketEtiquetadora;

	@FXML
	private StringField txtNumBalanzas;

	//Campos requeridos de Entrada
	@FXML
	private CheckBox chbDocumentoEV;
	@FXML
	private CheckBox chbConductorEV;
	@FXML
	private CheckBox chbNacionalidadEV;
	@FXML
	private CheckBox chbChasisEV;
	@FXML
	private CheckBox chbFacturarEV;
	@FXML
	private CheckBox chbObservacionesEV;
	@FXML
	private CheckBox chbProductoEV;
	@FXML
	private CheckBox chbTransporteEV;
	@FXML
	private CheckBox chbClienteEV;
	@FXML
	private CheckBox chbProcedenciaEV;

	//Campos requeridos de Salida
	@FXML
	private CheckBox chbDocumentoSV;
	@FXML
	private CheckBox chbConductorSV;
	@FXML
	private CheckBox chbNacionalidadSV;
	@FXML
	private CheckBox chbChasisSV;
	@FXML
	private CheckBox chbFacturarSV;
	@FXML
	private CheckBox chbObservacionesSV;
	@FXML
	private CheckBox chbProductoSV;
	@FXML
	private CheckBox chbTransporteSV;
	@FXML
	private CheckBox chbClienteSV;
	@FXML
	private CheckBox chbProcedenciaSV;

	private Stage stage;
	private ParametrosGlobalesPersistence parametrosGlobalesPersistence;
	
	private static String EMPRESA= "Balanzas Full Service SRL";
	private static String DIRECCION= "Misiones 1011";
	private static String LOCALIDAD= "San Lorenzo";
	private static String PROVINCIA= "Santa Fe";
	private static String TELEFONO= "0341 153538781 // 03476 15535441";
	private static String EMAIL= "ventas@fullservicebalanzas.com.ar";

	@FXML
	private void handleSelectImagen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(stage);

		if (selectedDirectory != null) {
			Image image = new Image(selectedDirectory.toURI().toString());
			imgEmpresa.setFitHeight(image.getHeight());
			imgEmpresa.setFitWidth(image.getWidth());
			imgEmpresa.setImage(image);
			txtPathRst.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handlePathSelected(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(stage);

		if (selectedDirectory != null) {
			txtPathBkp.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handleRestSelected(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(stage);

		if (selectedDirectory != null) {
			txtPathRst.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handleGuardar(ActionEvent event) {
		ParametrosGlobales pg = new ParametrosGlobales();
		if (txtTransaccion != null && txtTransaccion.getText() != null && !txtTransaccion.getText().isEmpty()) {
			try {
				Integer.valueOf(txtTransaccion.getText().trim());
			} catch (NumberFormatException e) {
				Message.error("El numero de transacción debe ser un numero entero.");
				return;
			}

			pg.setId(ParametrosGlobales.P_EMPRESA_TRANSACCION);
			pg.setValue(txtTransaccion.getText().trim());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtBkpAuto != null && txtBkpAuto.getText() != null && !txtBkpAuto.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_AUTOMATICO);
			pg.setValue(txtBkpAuto.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtTransaccion != null && txtPathBkp.getText() != null && !txtPathBkp.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_BACKUP);
			pg.setValue(txtPathBkp.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtPathRst != null && txtPathRst.getText() != null && !txtPathRst.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_RESTORE);
			pg.setValue(txtPathRst.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		/* PROPIETARIO DE LA BALANZA */
		if (txtNombreBalanza != null && txtNombreBalanza.getText() != null && !txtNombreBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL);
			pg.setValue(txtNombreBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtDireccionBalanza != null && txtDireccionBalanza.getText() != null
				&& !txtDireccionBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_DIR_BAL);
			pg.setValue(txtDireccionBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtLocalidadBalanza != null && txtLocalidadBalanza.getText() != null
				&& !txtLocalidadBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_LOC_BAL);
			pg.setValue(txtLocalidadBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtProvBalanza != null && txtProvBalanza.getText() != null && !txtProvBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_PROV_BAL);
			pg.setValue(txtProvBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtTelBalanza != null && txtTelBalanza.getText() != null && !txtTelBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_TEL_BAL);
			pg.setValue(txtTelBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}
		
		if (txtEmailBalanza != null && txtEmailBalanza.getText() != null && !txtEmailBalanza.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_EMAIL_BAL);
			pg.setValue(txtEmailBalanza.getText());
			parametrosGlobalesPersistence.save(pg);
		}				

		/* DATOS DE LA EMPRESA */
		if (txtNombreEmpresa != null && txtNombreEmpresa.getText() != null && !txtNombreEmpresa.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE);
			pg.setValue(txtNombreEmpresa.getText());
			parametrosGlobalesPersistence.save(pg);
		} 

		if (txtDireccion != null && txtDireccion.getText() != null && !txtDireccion.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_DIR);
			pg.setValue(txtDireccion.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtLocalidad != null && txtLocalidad.getText() != null && !txtLocalidad.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_LOC);
			pg.setValue(txtLocalidad.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtProv != null && txtProv.getText() != null && !txtProv.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_PROV);
			pg.setValue(txtProv.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtTel != null && txtTel.getText() != null && !txtTel.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_TEL);
			pg.setValue(txtTel.getText());
			parametrosGlobalesPersistence.save(pg);
		}
		
		if (txtEmail != null && txtEmail.getText() != null && !txtEmail.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_EMAIL);
			pg.setValue(txtEmail.getText());
			parametrosGlobalesPersistence.save(pg);
		}		

		if (txtNumBalanzas != null && txtNumBalanzas.getText() != null && !txtNumBalanzas.getText().isEmpty()) {
			try {
				Integer.valueOf(txtNumBalanzas.getText().trim());
			} catch (NumberFormatException e) {
				Message.error("El número de balanzas debe ser un numero entero.");
				return;
			}

			pg.setId(ParametrosGlobales.P_NUM_BALANZAS);
			pg.setValue(txtNumBalanzas.getText().trim());
			parametrosGlobalesPersistence.save(pg);
		}

		if (imgEmpresa.getImage() != null) {
			pg.setId(ParametrosGlobales.P_EMPRESA_IMG);
			BufferedImage bImage = SwingFXUtils.fromFXImage(imgEmpresa.getImage(), null);
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			try {
				ImageIO.write(bImage, "png", s);
				byte[] res = s.toByteArray();
				s.close();
				pg.setValue("");
				Blob blob = new SerialBlob(res);
				pg.setValueByte(blob);
				parametrosGlobalesPersistence.save(pg);
			} catch (IOException e) {
				logger.error(e);
			} catch (SerialException e) {
				logger.error(e);
			} catch (SQLException e) {
				logger.error(e);
			}
		}

		if (chkActivarDebug != null) {
			pg.setId(ParametrosGlobales.P_ACTIVAR_DEBUG);
			pg.setValue(String.valueOf(chkActivarDebug.isSelected()));
			parametrosGlobalesPersistence.save(pg);
		}

		if (chkticketEtiquetadora != null) {
			pg.setId(ParametrosGlobales.P_TICKET_ETIQUETADORA);
			pg.setValue(String.valueOf(chkticketEtiquetadora.isSelected()));
			parametrosGlobalesPersistence.save(pg);
		}

		if (txtClaveIngManual != null && txtClaveIngManual.getText() != null
				&& !txtClaveIngManual.getText().isEmpty()) {
			pg.setId(ParametrosGlobales.P_EMPRESA_ING_MANUAL);
			pg.setValue(txtClaveIngManual.getText());
			parametrosGlobalesPersistence.save(pg);
		}

		pg = new ParametrosGlobales();
		pg.setId("EMPRESA_RESTORE");
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtPathRst.setText(pg.getValue());
		}

		saveCamposRequeridos();
		Message.info("Los datos se guardaron correctamente.");
	}

	private void saveCamposRequeridos() {
		String listValidation = "";
		if(chbDocumentoEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_DOCUMENTO);
		}
		if(chbConductorEV.isSelected()){
			listValidation += concatSplit(listValidation,ParametrosGlobales.V_CONDUCTOR);
		}
		if(chbNacionalidadEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_NACIONALIDAD);
		}
		if(chbChasisEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_CHASIS);
		}
		if(chbFacturarEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_FACTURA);
		}
		if(chbObservacionesEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_OBSERVACION);
		}
		if(chbProductoEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_PRODUCTO);
		}
		if(chbTransporteEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_TRANSPORTE);
		}
		if(chbClienteEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_CLIENTE);
		}
		if(chbProcedenciaEV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_PROCEDENCIA);
		}

		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_VALIDACION_ENTRADA);
		pg.setValue(listValidation);
		parametrosGlobalesPersistence.save(pg);

		listValidation = "";
		if(chbDocumentoSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_DOCUMENTO);
		}
		if(chbConductorSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_CONDUCTOR);
		}
		if(chbNacionalidadSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_NACIONALIDAD);
		}
		if(chbChasisSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_CHASIS);
		}
		if(chbFacturarSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_FACTURA);
		}
		if(chbObservacionesSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_OBSERVACION);
		}
		if(chbProductoSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_PRODUCTO);
		}
		if(chbTransporteSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_TRANSPORTE);
		}
		if(chbClienteSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_CLIENTE);
		}
		if(chbProcedenciaSV.isSelected()){
			listValidation += concatSplit(listValidation, ParametrosGlobales.V_PROCEDENCIA);
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_VALIDACION_SALIDA);
		pg.setValue(listValidation);
		parametrosGlobalesPersistence.save(pg);
	}

	private String concatSplit(String concat, int newValue){
		return (concat.isEmpty()? "" : ",") + newValue;
	}

	@FXML
	private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
		((Stage) source.getScene().getWindow()).close();
	}

	@FXML
    private void handleRestaurar(ActionEvent event) {
		if(!txtPathRst.getText().isEmpty()) {
			try {
				UpdateDB db = new UpdateDB();
				db.dropDatabase();
				//Utils.restaurarBackup(txtPathRst.getText());
				boolean result = Utils.restoreDB(txtPathRst.getText());				
				logger.info("Corriendo actualizacion RUN: " + result);
				//db.run();
				Message.info("Restauración Finalizada. El sistema se cerrara al presionar OK.");	
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
				Message.error("Se produjo un error al restaurar el backup.");
			}
		}
    }

	@FXML
	private void handleGenerar(ActionEvent event) {
		if (!txtPathBkp.getText().isEmpty()) {
			try {
				Utils.generarBackup(txtPathBkp.getText());
				Message.info("Backup Finalizado.");
			} catch (IOException e) {
				e.printStackTrace();
				Message.error("Se produjo un error al generar el backup.");
			}
		}
	}

	public void initialize() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			txtBkpAuto.setTextFormatter(
					new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		imgEmpresa.setStyle(".image-view-wrapper:border {" + "    -fx-border-color: black;"
				+ "    -fx-border-style: solid;" + "    -fx-border-width: 5" + "}");
		parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
		txtNombreEmpresa.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtNombreEmpresa.setValue(newValue.toUpperCase());
			}
		});
		txtDireccion.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtDireccion.setText(newValue.toUpperCase());
			}
		});
		txtLocalidad.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtLocalidad.setText(newValue.toUpperCase());
			}
		});
		txtProv.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtProv.setText(newValue.toUpperCase());
			}
		});
		txtTel.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtTel.setText(newValue.toUpperCase());
			}
		});
		txtEmail.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtEmail.setText(newValue.toUpperCase());
			}
		});

		txtNombreBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtNombreBalanza.setText(newValue.toUpperCase());
			}
		});
		txtDireccionBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtDireccionBalanza.setText(newValue.toUpperCase());
			}
		});
		txtLocalidadBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtLocalidadBalanza.setText(newValue.toUpperCase());
			}
		});
		txtProvBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtProvBalanza.setText(newValue.toUpperCase());
			}
		});
		txtTelBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtTelBalanza.setText(newValue.toUpperCase());
			}
		});
		
		txtEmailBalanza.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtEmailBalanza.setText(newValue.toUpperCase());
			}
		});		

		txtTransaccion.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtTransaccion.setValue(newValue.toUpperCase());
			}
		});

		txtNumBalanzas.textProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue != null) {
				txtNumBalanzas.setValue(newValue.toUpperCase());
			}
		});

		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtNombreBalanza.setValue(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_DIR_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtDireccionBalanza.setValue(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_LOC_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtLocalidadBalanza.setValue(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_PROV_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtProvBalanza.setValue(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TEL_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtTelBalanza.setValue(pg.getValue());
		}
		
		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_EMAIL_BAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtEmailBalanza.setValue(pg.getValue());
		}
		
		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TICKET);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtNombreEmpresa.setValue(pg.getValue());
		} 
		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TRANSACCION);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtTransaccion.setValue(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_NOMBRE);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtNombreEmpresa.setText(pg.getValue());
		} else {
			txtNombreEmpresa.setText(EMPRESA);
		}
			

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_DIR);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtDireccion.setText(pg.getValue());
		} else {
			txtDireccion.setText(DIRECCION);
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_LOC);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtLocalidad.setText(pg.getValue());
		} else {
			txtLocalidad.setText(LOCALIDAD);
		}


		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_PROV);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtProv.setText(pg.getValue());
		}else {
			txtProv.setText(PROVINCIA);
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_TEL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtTel.setText(pg.getValue());
		} else {
			txtTel.setText(TELEFONO);
		}
		
		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_EMAIL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			txtEmail.setText(pg.getValue());
		} else {
			txtEmail.setText(EMAIL);
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_ACTIVAR_DEBUG);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			chkActivarDebug.setSelected(Boolean.valueOf(pg.getValue()));
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_TICKET_ETIQUETADORA);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			chkticketEtiquetadora.setSelected(Boolean.valueOf(pg.getValue()));
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_NUM_BALANZAS);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtNumBalanzas.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_IMG);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValueByte() != null) {
			// convert blob to byte[]
			InputStream input;
			try {
				input = pg.getValueByte().getBinaryStream();
				byte[] img = new byte[new Long(pg.getValueByte().length()).intValue()];
				input.read(img);

				// convert byte[] to image
				InputStream inputStream = new ByteArrayInputStream(img);
				BufferedImage buffer = ImageIO.read(inputStream);
				Image image = SwingFXUtils.toFXImage(buffer, null);
				imgEmpresa.setImage(image);
			} catch (SQLException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			} catch (NullPointerException e) {
				logger.error(e);
				imgEmpresa.setImage(null);
			}
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_IMG);
		parametrosGlobalesPersistence.load(pg);

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_BACKUP);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtPathBkp.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_AUTOMATICO);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtBkpAuto.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_RESTORE);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtPathRst.setText(pg.getValue());
		}

		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_EMPRESA_ING_MANUAL);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null) {
			txtClaveIngManual.setText(pg.getValue());
		}

		loadCamposRequeridos();
	}

	private void loadCamposRequeridos(){
		ParametrosGlobales pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_VALIDACION_ENTRADA);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			String[] validaciones = pg.getValue().split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case ParametrosGlobales.V_DOCUMENTO:
						chbDocumentoEV.setSelected(true);
						break;
					case ParametrosGlobales.V_CONDUCTOR:
						chbConductorEV.setSelected(true);
						break;
					case ParametrosGlobales.V_NACIONALIDAD:
						chbNacionalidadEV.setSelected(true);
						break;
					case ParametrosGlobales.V_CHASIS:
						chbChasisEV.setSelected(true);
						break;
					case ParametrosGlobales.V_FACTURA:
						chbFacturarEV.setSelected(true);
						break;
					case ParametrosGlobales.V_OBSERVACION:
						chbObservacionesEV.setSelected(true);
						break;
					case ParametrosGlobales.V_PRODUCTO:
						chbProductoEV.setSelected(true);
						break;
					case ParametrosGlobales.V_TRANSPORTE:
						chbTransporteEV.setSelected(true);
						break;
					case ParametrosGlobales.V_CLIENTE:
						chbClienteEV.setSelected(true);
						break;
					case ParametrosGlobales.V_PROCEDENCIA:
						chbProcedenciaEV.setSelected(true);
						break;
				}
			}
		}


		pg = new ParametrosGlobales();
		pg.setId(ParametrosGlobales.P_VALIDACION_SALIDA);
		parametrosGlobalesPersistence.load(pg);
		if (pg != null && pg.getValue() != null && !pg.getValue().isEmpty()) {
			String[] validaciones = pg.getValue().split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case ParametrosGlobales.V_DOCUMENTO:
						chbDocumentoSV.setSelected(true);
						break;
					case ParametrosGlobales.V_CONDUCTOR:
						chbConductorSV.setSelected(true);
						break;
					case ParametrosGlobales.V_NACIONALIDAD:
						chbNacionalidadSV.setSelected(true);
						break;
					case ParametrosGlobales.V_CHASIS:
						chbChasisSV.setSelected(true);
						break;
					case ParametrosGlobales.V_FACTURA:
						chbFacturarSV.setSelected(true);
						break;
					case ParametrosGlobales.V_OBSERVACION:
						chbObservacionesSV.setSelected(true);
						break;
					case ParametrosGlobales.V_PRODUCTO:
						chbProductoSV.setSelected(true);
						break;
					case ParametrosGlobales.V_TRANSPORTE:
						chbTransporteSV.setSelected(true);
						break;
					case ParametrosGlobales.V_CLIENTE:
						chbClienteSV.setSelected(true);
						break;
					case ParametrosGlobales.V_PROCEDENCIA:
						chbProcedenciaSV.setSelected(true);
						break;
				}
			}
		}
	}

	@Override
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}

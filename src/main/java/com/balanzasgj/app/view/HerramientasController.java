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
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.services.GlobalParameterService;
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
	private TextField txtExportPath;

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

	@FXML
	private TextField txtUserWindows;

	@FXML
	private PasswordField txtPassWindows;
	
	private GlobalParameterService paramConfigurationService;
	private MainActions mainActions;

	
	private static String EMPRESA= "Balanzas Full Service SRL";
	private static String DIRECCION= "Misiones 1011";
	private static String LOCALIDAD= "San Lorenzo";
	private static String PROVINCIA= "Santa Fe";
	private static String TELEFONO= "0341 153538781 // 03476 15535441";
	private static String EMAIL= "ventas@fullservicebalanzas.com.ar";

	@FXML
	private void handleSelectImagen(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(mainActions.getStage());

		if (selectedDirectory != null) {
			Image image = new Image(selectedDirectory.toURI().toString());
			imgEmpresa.setFitHeight(image.getHeight());
			imgEmpresa.setFitWidth(image.getWidth());
			imgEmpresa.setImage(image);
			txtPathRst.setText(selectedDirectory.getAbsolutePath());
		}
	}
	@FXML
	private void handleExportPath(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(mainActions.getStage());

		if (selectedDirectory != null) {
			txtExportPath.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handlePathSelected(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(mainActions.getStage());

		if (selectedDirectory != null) {
			txtPathBkp.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handleRestSelected(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(mainActions.getStage());

		if (selectedDirectory != null) {
			txtPathRst.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handleGuardar(ActionEvent event) {
		GlobalParameter pg = new GlobalParameter();
		if (txtTransaccion != null && txtTransaccion.getText() != null && !txtTransaccion.getText().isEmpty()) {
			try {
				Integer.valueOf(txtTransaccion.getText().trim());
			} catch (NumberFormatException e) {
				Message.error("El numero de transacción debe ser un numero entero.");
				return;
			}

			paramConfigurationService.save(GlobalParameter.P_EMPRESA_TRANSACCION, txtTransaccion.getText());
		}

		if (txtBkpAuto != null && txtBkpAuto.getText() != null && !txtBkpAuto.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_AUTOMATICO, txtBkpAuto.getText());
		}

		if (txtTransaccion != null && txtPathBkp.getText() != null && !txtPathBkp.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_BACKUP, txtPathBkp.getText());
		}

		if (txtPathRst != null && txtPathRst.getText() != null && !txtPathRst.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_RESTORE, txtPathRst.getText());
		}

		/* PROPIETARIO DE LA BALANZA */
		if (txtNombreBalanza != null && txtNombreBalanza.getText() != null && !txtNombreBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_NOMBRE_BAL, txtNombreBalanza.getText());
		}

		if (txtDireccionBalanza != null && txtDireccionBalanza.getText() != null
				&& !txtDireccionBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_DIR_BAL, txtDireccionBalanza.getText());
		}

		if (txtLocalidadBalanza != null && txtLocalidadBalanza.getText() != null
				&& !txtLocalidadBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_LOC_BAL, txtLocalidadBalanza.getText());
		}

		if (txtProvBalanza != null && txtProvBalanza.getText() != null && !txtProvBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_PROV_BAL, txtProvBalanza.getText());
		}

		if (txtTelBalanza != null && txtTelBalanza.getText() != null && !txtTelBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_TEL_BAL, txtTelBalanza.getText());
		}
		
		if (txtEmailBalanza != null && txtEmailBalanza.getText() != null && !txtEmailBalanza.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_EMAIL_BAL, txtEmailBalanza.getText());
		}				

		/* DATOS DE LA EMPRESA */
		if (txtNombreEmpresa != null && txtNombreEmpresa.getText() != null && !txtNombreEmpresa.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_NOMBRE, txtNombreEmpresa.getText());
		} 

		if (txtDireccion != null && txtDireccion.getText() != null && !txtDireccion.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_DIR, txtDireccion.getText());
		}

		if (txtLocalidad != null && txtLocalidad.getText() != null && !txtLocalidad.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_LOC, txtLocalidad.getText());
		}

		if (txtProv != null && txtProv.getText() != null && !txtProv.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_PROV, txtProv.getText());
		}

		if (txtTel != null && txtTel.getText() != null && !txtTel.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_TEL, txtTel.getText());
		}
		
		if (txtEmail != null && txtEmail.getText() != null && !txtEmail.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_EMAIL, txtEmail.getText());
		}		

		if (txtNumBalanzas != null && txtNumBalanzas.getText() != null && !txtNumBalanzas.getText().isEmpty()) {
			try {
				Integer.valueOf(txtNumBalanzas.getText().trim());
			} catch (NumberFormatException e) {
				Message.error("El número de balanzas debe ser un numero entero.");
				return;
			}
			paramConfigurationService.save(GlobalParameter.P_NUM_BALANZAS, txtNumBalanzas.getText());
		}

		if (imgEmpresa.getImage() != null) {
			BufferedImage bImage = SwingFXUtils.fromFXImage(imgEmpresa.getImage(), null);
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			try {
				ImageIO.write(bImage, "png", s);
				byte[] res = s.toByteArray();
				s.close();
				paramConfigurationService.saveBlob(GlobalParameter.P_EMPRESA_IMG, new SerialBlob(res));
			} catch (IOException e) {
				logger.error(e);
			} catch (SerialException e) {
				logger.error(e);
			} catch (SQLException e) {
				logger.error(e);
			}
		}

		if (chkActivarDebug != null) {
			paramConfigurationService.save(GlobalParameter.P_ACTIVAR_DEBUG, String.valueOf(chkActivarDebug.isSelected()));
		}

		if (chkticketEtiquetadora != null) {
			paramConfigurationService.save(GlobalParameter.P_TICKET_ETIQUETADORA, String.valueOf(chkticketEtiquetadora.isSelected()));
		}

		if (txtClaveIngManual != null && txtClaveIngManual.getText() != null
				&& !txtClaveIngManual.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_EMPRESA_ING_MANUAL, txtClaveIngManual.getText());
		}

		final String restore = paramConfigurationService.get("EMPRESA_RESTORE");
		if (!restore.isEmpty()) {
			txtPathRst.setText(restore);
		}

		if (txtExportPath != null && txtExportPath.getText() != null && !txtExportPath.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_CSV_EXPORT_PATH, txtExportPath.getText());
		}
		if (txtUserWindows != null && txtUserWindows.getText() != null && !txtUserWindows.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_USER_WINDOWS, txtUserWindows.getText());
		}
		if (txtPassWindows != null && txtPassWindows.getText() != null && !txtPassWindows.getText().isEmpty()) {
			paramConfigurationService.save(GlobalParameter.P_PASS_WINDOWS, txtPassWindows.getText());
		}

		saveCamposRequeridos();
		Message.info("Los datos se guardaron correctamente.");
	}

	private void saveCamposRequeridos() {
		String listValidation = "";
		if(chbDocumentoEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_DOCUMENTO);
		}
		if(chbConductorEV.isSelected()){
			listValidation += concatSplit(listValidation,GlobalParameter.V_CONDUCTOR);
		}
		if(chbNacionalidadEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_NACIONALIDAD);
		}
		if(chbChasisEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_CHASIS);
		}
		if(chbFacturarEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_FACTURA);
		}
		if(chbObservacionesEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_OBSERVACION);
		}
		if(chbProductoEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_PRODUCTO);
		}
		if(chbTransporteEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_TRANSPORTE);
		}
		if(chbClienteEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_CLIENTE);
		}
		if(chbProcedenciaEV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_PROCEDENCIA);
		}
		paramConfigurationService.save(GlobalParameter.P_VALIDACION_ENTRADA, listValidation);

		listValidation = "";
		if(chbDocumentoSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_DOCUMENTO);
		}
		if(chbConductorSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_CONDUCTOR);
		}
		if(chbNacionalidadSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_NACIONALIDAD);
		}
		if(chbChasisSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_CHASIS);
		}
		if(chbFacturarSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_FACTURA);
		}
		if(chbObservacionesSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_OBSERVACION);
		}
		if(chbProductoSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_PRODUCTO);
		}
		if(chbTransporteSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_TRANSPORTE);
		}
		if(chbClienteSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_CLIENTE);
		}
		if(chbProcedenciaSV.isSelected()){
			listValidation += concatSplit(listValidation, GlobalParameter.V_PROCEDENCIA);
		}
		paramConfigurationService.save(GlobalParameter.P_VALIDACION_SALIDA, listValidation);
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
		this.paramConfigurationService = new GlobalParameterService();
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

		txtNombreBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_NOMBRE_BAL));
		txtDireccionBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR_BAL));
		txtLocalidadBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC_BAL));
		txtProvBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV_BAL));
		txtTelBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL_BAL));
		txtEmailBalanza.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_EMAIL_BAL));
		txtNombreEmpresa.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_TICKET));
		if (txtNombreEmpresa.getText().isEmpty()) {
			txtNombreEmpresa.setText(EMPRESA);
		}
		txtTransaccion.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_TRANSACCION));
		txtDireccion.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_DIR));
		if (txtDireccion.getText().isEmpty()) {
			txtDireccion.setText(DIRECCION);
		}

		txtLocalidad.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_LOC));
		if (txtLocalidad.getText().isEmpty()) {
			txtLocalidad.setText(LOCALIDAD);
		}

		txtProv.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_PROV));
		if (txtProv.getText().isEmpty()) {
			txtProv.setText(PROVINCIA);
		}

		txtTel.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_TEL));
		if (txtTel.getText().isEmpty()) {
			txtTel.setText(TELEFONO);
		}

		txtEmail.setValue(paramConfigurationService.get(GlobalParameter.P_EMPRESA_EMAIL));
		if (txtEmail.getText().isEmpty()) {
			txtEmail.setText(EMAIL);
		}

		chkActivarDebug.setSelected(Boolean.valueOf(paramConfigurationService.get(GlobalParameter.P_ACTIVAR_DEBUG)));
		chkticketEtiquetadora.setSelected(Boolean.valueOf(paramConfigurationService.get(GlobalParameter.P_TICKET_ETIQUETADORA)));
		txtNumBalanzas.setValue(paramConfigurationService.get(GlobalParameter.P_NUM_BALANZAS));
		final Blob empresaImagen = paramConfigurationService.getBlob(GlobalParameter.P_EMPRESA_IMG);
		if (empresaImagen != null) {
			// convert blob to byte[]
			InputStream input;
			try {
				input = empresaImagen.getBinaryStream();
				byte[] img = new byte[new Long(empresaImagen.length()).intValue()];
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

		txtPathBkp.setText(paramConfigurationService.get(GlobalParameter.P_EMPRESA_BACKUP));
		txtBkpAuto.setText(paramConfigurationService.get(GlobalParameter.P_EMPRESA_AUTOMATICO));
		txtPathRst.setText(paramConfigurationService.get(GlobalParameter.P_EMPRESA_RESTORE));
		txtClaveIngManual.setText(paramConfigurationService.get(GlobalParameter.P_EMPRESA_ING_MANUAL));

		txtExportPath.setText(paramConfigurationService.get(GlobalParameter.P_CSV_EXPORT_PATH));
		txtUserWindows.setText(paramConfigurationService.get(GlobalParameter.P_USER_WINDOWS));
		txtPassWindows.setText(paramConfigurationService.get(GlobalParameter.P_PASS_WINDOWS));
		loadCamposRequeridos();
	}

	private void loadCamposRequeridos(){
		final String pValidEntrada = paramConfigurationService.get(GlobalParameter.P_VALIDACION_ENTRADA);
		if (pValidEntrada != null && !pValidEntrada.isEmpty()) {
			String[] validaciones = pValidEntrada.split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case GlobalParameter.V_DOCUMENTO:
						chbDocumentoEV.setSelected(true);
						break;
					case GlobalParameter.V_CONDUCTOR:
						chbConductorEV.setSelected(true);
						break;
					case GlobalParameter.V_NACIONALIDAD:
						chbNacionalidadEV.setSelected(true);
						break;
					case GlobalParameter.V_CHASIS:
						chbChasisEV.setSelected(true);
						break;
					case GlobalParameter.V_FACTURA:
						chbFacturarEV.setSelected(true);
						break;
					case GlobalParameter.V_OBSERVACION:
						chbObservacionesEV.setSelected(true);
						break;
					case GlobalParameter.V_PRODUCTO:
						chbProductoEV.setSelected(true);
						break;
					case GlobalParameter.V_TRANSPORTE:
						chbTransporteEV.setSelected(true);
						break;
					case GlobalParameter.V_CLIENTE:
						chbClienteEV.setSelected(true);
						break;
					case GlobalParameter.V_PROCEDENCIA:
						chbProcedenciaEV.setSelected(true);
						break;
				}
			}
		}

		final String pValidSalida = paramConfigurationService.get(GlobalParameter.P_VALIDACION_SALIDA);
		if (pValidSalida != null && !pValidSalida.isEmpty()) {
			String[] validaciones = pValidSalida.split(",");
			for(int i= 0; i < validaciones.length; i++){
				switch (Integer.valueOf(validaciones[i])){
					case GlobalParameter.V_DOCUMENTO:
						chbDocumentoSV.setSelected(true);
						break;
					case GlobalParameter.V_CONDUCTOR:
						chbConductorSV.setSelected(true);
						break;
					case GlobalParameter.V_NACIONALIDAD:
						chbNacionalidadSV.setSelected(true);
						break;
					case GlobalParameter.V_CHASIS:
						chbChasisSV.setSelected(true);
						break;
					case GlobalParameter.V_FACTURA:
						chbFacturarSV.setSelected(true);
						break;
					case GlobalParameter.V_OBSERVACION:
						chbObservacionesSV.setSelected(true);
						break;
					case GlobalParameter.V_PRODUCTO:
						chbProductoSV.setSelected(true);
						break;
					case GlobalParameter.V_TRANSPORTE:
						chbTransporteSV.setSelected(true);
						break;
					case GlobalParameter.V_CLIENTE:
						chbClienteSV.setSelected(true);
						break;
					case GlobalParameter.V_PROCEDENCIA:
						chbProcedenciaSV.setSelected(true);
						break;
				}
			}
		}
	}
	
	@Override
	public void setMainActions(MainActions mainActions) {
		this.mainActions = mainActions;		
	}
}

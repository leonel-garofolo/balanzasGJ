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

import org.javafx.controls.customs.StringField;

import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.Utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
	@FXML
	private StringField txtNombre;
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
	private TextField txtNombreEmpresa;
	
	@FXML
	private TextField txtDireccion;
	
	@FXML
	private TextField txtTel;
	
	@FXML
	private TextField txtLocalidad;
	
	@FXML
	private TextField txtProv;
	
	@FXML
	private PasswordField txtClaveIngManual;
		
	private Stage stage;
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	
	@FXML
    private void handleSelectImagen(ActionEvent event) {		
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(stage);

		if(selectedDirectory != null){
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

		if(selectedDirectory != null){
			txtPathBkp.setText(selectedDirectory.getAbsolutePath());
		}
	}
	@FXML
    private void handleRestSelected(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		File selectedDirectory = fileChooser.showOpenDialog(stage);

		if(selectedDirectory != null){
			txtPathRst.setText(selectedDirectory.getAbsolutePath());
		}
	}
		
	@FXML
    private void handleGuardar(ActionEvent event) {
		ParametrosGoblales pg = new ParametrosGoblales();
		if(!txtNombre.getText().isEmpty()) {
			pg.setId(ParametrosGoblales.P_EMPRESA_TICKET);
			pg.setValue(txtNombre.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		if(!txtTransaccion.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_TRANSACCION);
			pg.setValue(txtTransaccion.getText());
			parametrosGoblalesPersistence.save(pg);
		}
				
		if(!txtBkpAuto.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_AUTOMATICO);
			pg.setValue(txtBkpAuto.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtPathBkp.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_BACKUP);
			pg.setValue(txtPathBkp.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(txtPathRst.getText() != null && !txtPathRst.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_RESTORE);
			pg.setValue(txtPathRst.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtNombreEmpresa.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_NOMBRE);
			pg.setValue(txtNombreEmpresa.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtDireccion.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_DIR);
			pg.setValue(txtDireccion.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtLocalidad.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_LOC);
			pg.setValue(txtLocalidad.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtProv.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_PROV);
			pg.setValue(txtProv.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtTel.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_TEL);
			pg.setValue(txtTel.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		
		if(imgEmpresa.getImage() != null) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_IMG);
			BufferedImage bImage = SwingFXUtils.fromFXImage(imgEmpresa.getImage(), null);
			ByteArrayOutputStream s = new ByteArrayOutputStream();
			try {
				ImageIO.write(bImage, "png", s);
				byte[] res  = s.toByteArray();
				s.close();
				pg.setValue("");
				 Blob blob = new SerialBlob(res);
				pg.setValueByte(blob);
				parametrosGoblalesPersistence.save(pg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SerialException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!txtClaveIngManual.getText().isEmpty()) {			
			pg.setId(ParametrosGoblales.P_EMPRESA_ING_MANUAL);
			pg.setValue(txtClaveIngManual.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_RESTORE");
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtPathRst.setText(pg.getValue());
		}
		
		Message.info("Los datos se guardaron correctamente.");
    }
	
	@FXML
    private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
	    ((Stage) source.getScene().getWindow()).close();	    
    }
	
	@FXML
    private void handleRestaurar(ActionEvent event) {
		if(!txtPathRst.getText().isEmpty()) {
			Utils.restaurarBackup(txtPathRst.getText());
		}
    }
	
	@FXML
    private void handleGenerar(ActionEvent event) {
		if(!txtPathBkp.getText().isEmpty()) {
			Utils.generarBackup(txtPathBkp.getText());
		}
    }	
	
	public void initialize() {		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		try {
			txtBkpAuto.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		imgEmpresa.setStyle(".image-view-wrapper:border {" + 
				"    -fx-border-color: black;" + 
				"    -fx-border-style: solid;" + 
				"    -fx-border-width: 5" + 
				"}");
		parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		txtNombre.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNombre.setValue(newValue.toUpperCase());
		});
		txtTransaccion.textProperty().addListener((ov, oldValue, newValue) -> {
			txtTransaccion.setValue(newValue.toUpperCase());
		});		
		
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_TICKET);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtNombre.setValue(pg.getValue());
		}		
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_TRANSACCION);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtTransaccion.setValue(pg.getValue());
		}		
		
		pg.setId(ParametrosGoblales.P_EMPRESA_NOMBRE);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtNombreEmpresa.setText(pg.getValue());
		}
		
		pg.setId(ParametrosGoblales.P_EMPRESA_DIR);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtDireccion.setText(pg.getValue());
		}
		
		
		pg.setId(ParametrosGoblales.P_EMPRESA_LOC);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtLocalidad.setText(pg.getValue());
		}
		
		
		pg.setId(ParametrosGoblales.P_EMPRESA_PROV);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtProv.setText(pg.getValue());
		}
		
		
		pg.setId(ParametrosGoblales.P_EMPRESA_TEL);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtTel.setText(pg.getValue());
		}
		
		pg.setId(ParametrosGoblales.P_EMPRESA_IMG);
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null && pg.getValueByte() != null) {
			//convert blob to byte[]
            InputStream input;
			try {
				input = pg.getValueByte().getBinaryStream();
				byte[] img = new byte[new Long(pg.getValueByte().length()).intValue()];
	            input.read(img);

	            //convert byte[] to image
	            InputStream inputStream = new ByteArrayInputStream(img);
	            BufferedImage buffer = ImageIO.read(inputStream);
	            Image image = SwingFXUtils.toFXImage(buffer, null);
	            imgEmpresa.setImage(image);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		}
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_IMG);
		parametrosGoblalesPersistence.load(pg);		
		
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_BACKUP);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtPathBkp.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_AUTOMATICO);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtBkpAuto.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_RESTORE);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtPathRst.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_ING_MANUAL);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtClaveIngManual.setText(pg.getValue());
		}
    }
	@Override
	public void setStage(Stage stage) {
		this.stage =stage;
	} 
}

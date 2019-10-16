package com.balanzasgj.app.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.javafx.controls.customs.StringField;

import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.balanzasgj.app.utils.Message;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	private TextField txtPathBkp; 
	
	@FXML
	private TextField txtPathRst; 		
	@FXML
	private ImageView imgEmpresa;
	
	@FXML
	private TextField txtNombreEmpresa;	
	
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
			pg.setId("EMPRESA_TICKET");
			pg.setValue(txtNombre.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		if(!txtTransaccion.getText().isEmpty()) {			
			pg.setId("EMPRESA_TRANSACCION");
			pg.setValue(txtTransaccion.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		if(!txtPathBkp.getText().isEmpty()) {			
			pg.setId("EMPRESA_BACKUP");
			pg.setValue(txtPathBkp.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(txtPathRst.getText() != null && !txtPathRst.getText().isEmpty()) {			
			pg.setId("EMPRESA_RESTORE");
			pg.setValue(txtPathRst.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtNombreEmpresa.getText().isEmpty()) {			
			pg.setId("EMPRESA_NOMBRE");
			pg.setValue(txtNombreEmpresa.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		
		if(!txtClaveIngManual.getText().isEmpty()) {			
			pg.setId("EMPRESA_ING_MANUAL");
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
			try {
				String command = "D:\\aplicaciones\\xampp\\mysql\\bin\\mysqldump -u root sist_pesada < " + txtPathRst;
				System.out.println(command);				
				Runtime.getRuntime().exec(command);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
    }
	
	@FXML
    private void handleGenerar(ActionEvent event) {
		if(!txtPathBkp.getText().isEmpty()) {
			try {
				String command = "D:\\aplicaciones\\xampp\\mysql\\bin\\mysqldump -u root sist_pesada";
				System.out.println(command);
				
				
				Process p = Runtime.getRuntime().exec(command);
				 InputStream is = p.getInputStream();
			      FileOutputStream fos = new FileOutputStream(txtPathBkp.getText() + "\\backup_.sql");
			      byte[] buffer = new byte[1000];

			      int leido = is.read(buffer);
			      while (leido > 0) {
			         fos.write(buffer, 0, leido);
			         leido = is.read(buffer);
			      }

			      fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
    }	
	
	public void initialize() {
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
		pg.setId("EMPRESA_TICKET");
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtNombre.setValue(pg.getValue());
		}		
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_TRANSACCION");
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtTransaccion.setValue(pg.getValue());
		}		
		
		pg.setId("EMPRESA_NOMBRE");
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			txtNombreEmpresa.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_BACKUP");
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtPathBkp.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_RESTORE");
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			txtPathRst.setText(pg.getValue());
		}
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_ING_MANUAL");
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

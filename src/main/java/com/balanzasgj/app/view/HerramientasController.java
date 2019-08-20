package com.balanzasgj.app.view;

import org.javafx.controls.customs.FileSelectFieldVBox;
import org.javafx.controls.customs.StringField;

import com.balanzasgj.app.basic.bean.ParametrosGoblales;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HerramientasController extends AnchorPane {
	@FXML
	private StringField txtNombre;
	@FXML
	private StringField txtTransaccion;
	@FXML
	private FileSelectFieldVBox fileBackup;
	@FXML
	private Button btnGenerar;
	@FXML
	private FileSelectFieldVBox fileRestoreBackup;
	@FXML
	private Button btnRestaurar;
	@FXML
	private Button btnGuardar;
	@FXML
	private Button btnCerrar;
	
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
		
	@FXML
    private void handleGuardar(ActionEvent event) {
		ParametrosGoblales pg = new ParametrosGoblales();
		if(!txtNombre.getText().isEmpty()) {
			pg.setId("EMPRESA_NOMBRE");
			pg.setValue(txtNombre.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		if(!txtTransaccion.getText().isEmpty()) {			
			pg.setId("EMPRESA_TRANSACCION");
			pg.setValue(txtTransaccion.getText());
			parametrosGoblalesPersistence.save(pg);
		}
		if(!txtTransaccion.getText().isEmpty()) {			
			pg.setId("EMPRESA_BACKUP");
			pg.setValue(fileRestoreBackup.getValue());
			parametrosGoblalesPersistence.save(pg);
		}
    }
	
	@FXML
    private void handleCerrar(ActionEvent event) {
		final Node source = (Node) event.getSource();
	    ((Stage) source.getScene().getWindow()).close();	    
    }
	
	@FXML
    private void handleRestaurar(ActionEvent event) {
		System.out.println("Hello World!");
    }
	
	@FXML
    private void handleGenerar(ActionEvent event) {
		System.out.println("Hello World!");
    }	
	
	public void initialize() {
		parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		txtNombre.textProperty().addListener((ov, oldValue, newValue) -> {
			txtNombre.setValue(newValue.toUpperCase());
		});
		txtTransaccion.textProperty().addListener((ov, oldValue, newValue) -> {
			txtTransaccion.setValue(newValue.toUpperCase());
		});		
		
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId("EMPRESA_NOMBRE");
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
		
		pg = new ParametrosGoblales();
		pg.setId("EMPRESA_BACKUP");
		parametrosGoblalesPersistence.load(pg);
		if(pg!= null) {
			fileBackup.setValue(pg.getValue());
		}
    } 
}

package com.balanzasgj.app.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController{
	@FXML
	private Button btnTaras;
	@FXML
	private Button btnConfiguraciones;
	@FXML
	private Button btnInformes;
	@FXML
	private Button btnSalir;	
	@FXML
	private Button btnUsuarios;	
	@FXML
	private Button btnHerramientas;			

	@FXML
    private void handleUsuarios(ActionEvent event) {
		this.openWindows("PesarEntradaSalidaView", "Tara");
    }
	
	@FXML
    private void handleTara(ActionEvent event) {
		this.openWindows("PesarEntradaSalidaView", "Tara");
    }
	
	@FXML
    private void handleConfiguraciones(ActionEvent event) {
		this.openWindows("ConfiguracionesView", "Configuraciones");
    }
	
	@FXML
    private void handleInformes(ActionEvent event) {
		this.openWindows("InformesView", "Informes");
    }
	
	@FXML
    private void handleHerramientas(ActionEvent event) {
		this.openWindows("HerramientasView", "Herramientas");
    }
	
	@FXML
    private void exit(ActionEvent event) {
		System.exit(0);
    }
	
	private void openWindows(String fxmlName, String title) {
		try {
			Parent rootHerramientas =FXMLLoader.load(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.resizableProperty().setValue(Boolean.FALSE);
		    stage.setTitle(title);
		    Scene scene = new Scene(rootHerramientas);
		    scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
		    stage.setScene(scene);  
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialize() {
    } 
}

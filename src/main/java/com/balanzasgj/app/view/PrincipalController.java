package com.balanzasgj.app.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.balanzasgj.app.App;
import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrincipalController implements Initializable, IView{
	@FXML
	private Button btnTaras;
	@FXML
	private Button btnConfiguraciones;
	@FXML
	private Button btnInformes;
	@FXML
	private Button btnSalir;	
	@FXML
	private Button btnHerramientas;	
	private ParametrosGoblalesPersistence parametrosGoblalesPersistence;
	
	@FXML
	private Label lblEmpresa;
	
	@FXML
	private Label lblUsuario;
	private Stage stage;
	
	@FXML
    private void handleUsuarios(ActionEvent event) {
		this.openWindows("UsuariosView", "Usuarios");
    }
	
	@FXML
    private void handleTara(ActionEvent event) {
		this.openWindows("PesarEntradaSalidaView", "Tara");
    }
	
	@FXML
    private void handleCerrarSesion(ActionEvent event) {					
	    stage.setWidth(300);
		stage.setHeight(190);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/LoginView.fxml"));			
			Parent rootPrincipal = (Parent)loader.load();			
			Scene scene = new Scene(rootPrincipal);			
			scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
			LoginController controller = (LoginController)loader.getController();
	    	
			stage.setScene(scene);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
			stage.resizableProperty().set(false);				
			Image ico = new Image(App.PATH_ICONO); 
			stage.getIcons().add(ico); 
			controller.setStage(stage);	
			stage.show();
			
			
			stage.setOnHiding(new EventHandler<WindowEvent>() {

	            public void handle(WindowEvent event) {
	            	System.exit(0);
	            }
	        });		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		this.openWindows("HerramientasView", "Sistema");
    }
	
	@FXML
    private void exit(ActionEvent event) {
		System.exit(0);
    }
	
	private void openWindows(String fxmlName, String title) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + fxmlName + ".fxml"));
			Parent rootHerramientas = (Parent)loader.load();
			Stage stage = new Stage();
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.resizableProperty().setValue(Boolean.FALSE);
		    stage.setTitle(title);
		    Scene scene = new Scene(rootHerramientas);
		    scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
		    stage.setScene(scene);  
		    
		    if(loader.getController() instanceof PesarEntradaSalidaController) {
		    	PesarEntradaSalidaController controller = (PesarEntradaSalidaController)loader.getController();
		    	stage.setOnCloseRequest(E -> {
		    		controller.closeSocket();
		    	   });
		    	controller.setStage(stage);
		    }
		    if(loader.getController() instanceof HerramientasController) {
		    	IView controller = (HerramientasController)loader.getController();
		    	controller.setStage(stage);
		    }		    
		    stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId("EMPRESA_NOMBRE");
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			lblEmpresa.setText(pg.getValue());
		}		
		
		lblEmpresa.setFont(new Font("Arial", 30));
		lblUsuario.setFont(new Font("Arial", 14));
		lblUsuario.setStyle("-fx-font-weight: bold");
		lblUsuario.setText(Usuarios.getUsuarioLogeado().toUpperCase());
		
		
		String perfil = Usuarios.getPerfilLogeado();
		switch (perfil) {		
		case Usuarios.P_SUPERVISOR:
			btnHerramientas.setDisable(true);
			break;
		case Usuarios.P_OPERADOR:
			btnInformes.setDisable(true);			
			btnHerramientas.setDisable(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void setStage(Stage stage) {
		this.stage= stage;
		
	}
}

package com.balanzasgj.app.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.balanzasgj.app.App;
import com.balanzasgj.app.model.ParametrosGoblales;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.ParametrosGoblalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGoblalesPersistenceJdbc;
import com.ibm.icu.text.SimpleDateFormat;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrincipalController implements Initializable, IView{	
	private SimpleDateFormat sf;
	@FXML
	private ImageView imgEmpresa;
	
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
	private Label lblHora;
	
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
		    Image ico = new Image(App.PATH_ICONO); 
			stage.getIcons().add(ico); 
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
		this.sf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		parametrosGoblalesPersistence = new ParametrosGoblalesPersistenceJdbc();
		ParametrosGoblales pg = new ParametrosGoblales();
		pg.setId(ParametrosGoblales.P_EMPRESA_NOMBRE);
		parametrosGoblalesPersistence.load(pg);		
		if(pg!= null) {
			lblEmpresa.setText(pg.getValue());
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
		
		lblEmpresa.setFont(new Font("Arial", 30));
		lblUsuario.setFont(new Font("Arial", 14));
		lblUsuario.setStyle("-fx-font-weight: bold");
		lblUsuario.setText(Usuarios.getUsuarioLogeado().toUpperCase());		
		lblHora.setText("");
		/*
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				while(true) {
					lblHora.setText(sf.format(new Date()));
				}
			} 
			
		});
		*/
		
		String perfil = Usuarios.getPerfilLogeado();
		switch (perfil) {		
		case Usuarios.P_SUPERVISOR:
			btnHerramientas.setDisable(true);
			break;
		case Usuarios.P_OPERADOR:
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

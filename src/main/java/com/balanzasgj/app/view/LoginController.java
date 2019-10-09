package com.balanzasgj.app.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.UsuariosPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.UsuariosPersistenceJdbc;
import com.balanzasgj.app.utils.Message;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable{
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtClave;
	@FXML
	private Button btnIngresar;
	private UsuariosPersistence usuariosPersistence;
	
	@FXML
	public void handleIngresar(Event e) {
		if(!txtUsuario.getText().isEmpty()
				&& !txtClave.getText().isEmpty()) {
			Usuarios usuario = this.usuariosPersistence.loadForNombre(txtUsuario.getText());
			if(usuario == null) {
				Message.error("Usuario incorrecto.");
				return;
			} else if(!usuario.getClave().equals(txtClave.getText())) {
				Message.error("Clave incorrecta.");
				return;
			}
			
			Stage stage = new Stage();
			Screen screen = Screen.getPrimary();
		    Rectangle2D bounds = screen.getVisualBounds();
		    stage.setWidth(bounds.getWidth());
		    stage.setHeight(bounds.getHeight());
		    		    
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/PrincipalView.fxml"));
				Parent rootPrincipal = (Parent)loader.load();
				Scene scene = new Scene(rootPrincipal);
				scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/style.css").toExternalForm());
				stage.setScene(scene);
				stage.resizableProperty().set(false);				
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
	}
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.usuariosPersistence = new UsuariosPersistenceJdbc();
	}
}

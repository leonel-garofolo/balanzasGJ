package com.balanzasgj.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.balanzasgj.app.model.User;
import com.balanzasgj.app.services.UserService;
import com.balanzasgj.app.utils.Message;
import com.balanzasgj.app.utils.Utils;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable, IView, EventHandler<WindowEvent>{
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtClave;
	@FXML
	private Button btnIngresar;
	private UserService userService;
	private MainActions mainActions;
	
	@FXML
	public void handleIngresar(Event e) {
		if(!txtUsuario.getText().isEmpty()
				&& !txtClave.getText().isEmpty()) {					
			User usuario = this.userService.loadForNombre(txtUsuario.getText());
			if(usuario == null) {
				Message.error("Usuario incorrecto.");
				return;
			} else if(!usuario.getClave().equals(txtClave.getText())) {
				Message.error("Clave incorrecta.");
				return;
			}
			
			User.setUsuarioLogeado(usuario.getNombre());
			switch (String.valueOf(usuario.getIdPerfil())) {
			case "1":
				User.setPerfilLogeado(User.P_ADMINISTRADOR);
				break;
			case "2":
				User.setPerfilLogeado(User.P_SUPERVISOR);
				break;
			case "3":
				User.setPerfilLogeado(User.P_OPERADOR);
				break;

			default:
				break;
			}
									
			this.mainActions.showDashboard();
		}
	}
	
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
		if(Utils.isDebug()) {
			txtUsuario.setText("admin");
			txtClave.setText("123456");
		}
		txtUsuario.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		
		this.userService = new UserService();		
		txtUsuario.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	handleIngresar(ke);
	            }
	        }
	    });
		txtClave.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.ENTER))
	            {
	            	handleIngresar(ke);
	            }
	        }
	    });
	}
	
	@Override
	public void handle(WindowEvent e) {
		txtUsuario.requestFocus();		
	}


	@Override
	public void setMainActions(MainActions mainActions) {
		this.mainActions = mainActions;
		txtUsuario.requestFocus();
	}
}

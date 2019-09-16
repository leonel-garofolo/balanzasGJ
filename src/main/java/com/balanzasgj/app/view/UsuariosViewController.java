package com.balanzasgj.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.balanzasgj.app.model.Perfiles;
import com.balanzasgj.app.model.Usuarios;
import com.balanzasgj.app.persistence.UsuariosPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.UsuariosPersistenceJdbc;
import com.balanzasgj.app.utils.Message;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UsuariosViewController implements Initializable{
	@FXML
	private TableView<Perfiles> tblPerfiles;
	@FXML
	private TableColumn<Perfiles, String> colNombrePerfil;
	@FXML
	private Button btnNuevo;
	@FXML
	private TableView<Usuarios> tblUsuarios;
	@FXML
	private TableColumn<Usuarios, String> colUsuario;
	@FXML
	private TableColumn<Usuarios, String> colClave;
	@FXML
	private Button btnAplicar;
	
	@FXML
	private TextField txtUsuario;	
	@FXML
	private PasswordField txtClave;
	
	private UsuariosPersistence usuariosPersistence;
	
	private Usuarios userEdit; 
	
	@FXML
	private void handleEliminar(ActionEvent event) {
		if(tblPerfiles.getSelectionModel().isEmpty()) {
			Message.error("Es requerido seleccionar un perfil.");
			return;
		}
		if(tblUsuarios.getSelectionModel().isEmpty()) {
			Message.error("Es requerido seleccionar un usuario.");
			return;
		}
		this.userEdit = tblUsuarios.getSelectionModel().getSelectedItem();
		usuariosPersistence.deleteById(userEdit.getId());
		refreshTableUsuarios();
		cleanFormUsuario();
		Message.info("El usuario se elimino correctamente.");
	}
	
	@FXML
	private void handleNuevo(ActionEvent event) {
		cleanFormUsuario();
	}
	
	@FXML
	private void handleAplicar(ActionEvent event) {
		if(tblPerfiles.getSelectionModel().isEmpty()) {
			Message.error("Es requerido seleccionar un perfil.");
		}else {
			if(txtUsuario.getText().isEmpty() || txtClave.getText().isEmpty()) {
				Message.error("Los campos en negrita son requeridos.");					
			}
			if(this.userEdit == null) {
				this.userEdit = new Usuarios();				
			}
			
			this.userEdit.setNombre(txtUsuario.getText());
			this.userEdit.setClave(txtClave.getText());
			this.userEdit.setIdPerfil(tblPerfiles.getSelectionModel().getSelectedItem().getId());
			usuariosPersistence.save(userEdit);
			refreshTableUsuarios();
			cleanFormUsuario();
			Message.info("Se guardo correctamente.");
		}
	}
	
	@FXML
	private void handleTblUsuariosSelected(MouseEvent event) {		
		if(!tblUsuarios.getSelectionModel().isEmpty()) {
			this.userEdit =tblUsuarios.getSelectionModel().getSelectedItem();
			txtUsuario.setText(tblUsuarios.getSelectionModel().getSelectedItem().getNombre());
			txtClave.setText(tblUsuarios.getSelectionModel().getSelectedItem().getClave());
		}
	}
	
	@FXML
	private void handleTblPerfilesSelected(MouseEvent event) {	
		cleanFormUsuario();
		refreshTableUsuarios();
	}
	
	private void cleanFormUsuario() {
		this.userEdit = null;
		txtUsuario.setText("");
		txtClave.setText("");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.usuariosPersistence = new UsuariosPersistenceJdbc();
		loadTables();
	}
	
	private void loadTables() {
		colNombrePerfil.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		Perfiles perf = new Perfiles();
		perf.setId(1);
		perf.setNombre("ADMINISTRADOR");
		tblPerfiles.getItems().add(perf);
		
		perf = new Perfiles();
		perf.setId(2);
		perf.setNombre("SUPERVISOR");
		tblPerfiles.getItems().add(perf);
		
		perf = new Perfiles();
		perf.setId(3);
		perf.setNombre("OPERARIO");
		tblPerfiles.getItems().add(perf);		
	}
	
	private void refreshTableUsuarios() {
		tblUsuarios.getItems().clear();
		if(tblPerfiles.getSelectionModel().isEmpty()) {			
			// perfil por defecto es el ADMINISTRADOR
			tblUsuarios.getItems().addAll(usuariosPersistence.loadForPerfil(1));
		}else {
			tblUsuarios.getItems().addAll(usuariosPersistence.loadForPerfil(tblPerfiles.getSelectionModel().getSelectedItem().getId()));
		}
	}
}

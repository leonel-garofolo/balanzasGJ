package com.balanzasgj.app.view.custom;

import com.balanzasgj.app.view.ConfiguracionesController;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AduanaDialog extends Dialog<String> {
	public static String SPLIT= "####";
	private static Label lblNombre;
	private Label lblCuitAlias;
	private TextField nombreField;
	private TextField cuitAliasField;

	public AduanaDialog(String type) {
		lblNombre = new Label();
		lblNombre.setText("Nombre");
		lblCuitAlias = new Label();
		GridPane layout = new GridPane();		
		
		String title = "Nuevo ";
		switch (type) {
		case ConfiguracionesController.CLIENTE:
			title += " Cliente";
			break;
		case ConfiguracionesController.IMPORTADORES:
			title += " Importadores/Exportadores";
			break;
		case ConfiguracionesController.PROCEDENCIAS:
			title += " Procedencia";
			break;
		case ConfiguracionesController.PRODUCTOS:
			title += " Producto";
			break;
		case ConfiguracionesController.TRANSPORTES:
			title += " Transporte";
			break;

		default:
			break;
		}
		setTitle(title);

		ButtonType btnGuardar = new ButtonType("Guardar", ButtonData.OK_DONE);
		getDialogPane().getButtonTypes().addAll(btnGuardar, ButtonType.CANCEL);

		nombreField = new TextField();
		nombreField.setPromptText("Nombre");
		

		cuitAliasField = new TextField();
		if (type.equals(ConfiguracionesController.CLIENTE) || type.equals(ConfiguracionesController.PROCEDENCIAS)) {
			cuitAliasField.setVisible(false);
			lblCuitAlias.setVisible(false);
		} else {
			cuitAliasField.setVisible(true);
			lblCuitAlias.setVisible(true);
		}
		if (type.equals(ConfiguracionesController.PRODUCTOS)) {
			lblCuitAlias.setText("Alias");
		} else {
			lblCuitAlias.setText("Cuit");
		}
		
		layout.add(lblNombre, 1, 0);
		layout.add(lblCuitAlias, 1, 1);
		layout.add(nombreField, 2, 0);
		layout.add(cuitAliasField, 2, 1);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setHgap(10);
		layout.setVgap(10);

		GridPane.setHgrow(layout, Priority.ALWAYS);
		getDialogPane().setContent(layout);

		Platform.runLater(() -> nombreField.requestFocus());

		setResultConverter(dialogButton -> {
			if (dialogButton == btnGuardar) {				
				return nombreField.getText() + SPLIT + cuitAliasField.getText();
			}
			return null;
		});
	}
}
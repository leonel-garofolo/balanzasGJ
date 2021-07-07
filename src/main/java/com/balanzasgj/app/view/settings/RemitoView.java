package com.balanzasgj.app.view.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.javafx.controls.customs.view.ComboBoxAutoCompleteView;

import com.balanzasgj.app.informes.model.RemitoFieldType;
import com.balanzasgj.app.model.RemitoField;
import com.balanzasgj.app.services.RemitoFieldService;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RemitoView extends VBox {
	private VBox screen;	
	private TableView<RemitoField> tblRemito;
	private RemitoFieldService remitoFieldService;
	
	public RemitoView(){
		this.remitoFieldService = new RemitoFieldService();
		initialize();
	}		

	public Node build() {		
		return this;
	}
	
	private void initialize() {
		screen = new VBox();		
		ComboBoxAutoCompleteView<String> setupPage = new ComboBoxAutoCompleteView<String>("Seleccione el formato de pagina");
		setupPage.getItems().add("A4");
		setupPage.getItems().add("A5");
		screen.getChildren().add(setupPage);	
		
		
		Label lbl = new Label("Indique en la tabla las posiciones vertical y horizontal de cada variable en centimetros:");
		lbl.setPadding(new Insets(5, 0, 5, 0));
		screen.getChildren().add(lbl);	
		screen.setMaxHeight(Double.MAX_VALUE);				
		setMaxHeight(Double.MAX_VALUE);
		
		List<RemitoField> items = buildItems();
		tblRemito = new TableView<RemitoField>();
		tblRemito.setEditable(true);
		TableColumn<RemitoField, String> colFieldName = new TableColumn<RemitoField, String>();
		colFieldName.setText("Variable");
		colFieldName.setCellValueFactory(new PropertyValueFactory<>("dato"));
		
		TableColumn<RemitoField, String> colFieldPosX = new TableColumn<RemitoField, String>();
		colFieldPosX.setText("Posición X");
		colFieldPosX.setCellFactory(TextFieldTableCell.forTableColumn());
		colFieldPosX.setOnEditCommit(e ->{
			e.getRowValue().setPosX(e.getNewValue());
		});				
		colFieldPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
		
		TableColumn<RemitoField, String> colFieldPosY = new TableColumn<RemitoField, String>();
		colFieldPosY.setText("Posición Y");
		colFieldPosY.setCellFactory(TextFieldTableCell.forTableColumn());
		colFieldPosY.setOnEditCommit(e ->{
			e.getRowValue().setPosY(e.getNewValue());
		});	
		colFieldPosY.setCellValueFactory(new PropertyValueFactory<>("posY"));
		tblRemito.getColumns().add(colFieldName);
		tblRemito.getColumns().add(colFieldPosX);
		tblRemito.getColumns().add(colFieldPosY);
		tblRemito.getItems().addAll(items);
		screen.getChildren().add(tblRemito);
		
		final HBox hbox = new HBox();
		hbox.setId("BoxButton");
		hbox.setSpacing(10);	
		hbox.setPadding(new Insets(5, 10, 5, 0));
		
		final Button btnPrimary = new Button("Guardar");
		btnPrimary.setOnAction(v -> save());
		btnPrimary.setId("btnPrimary");		
		hbox.getChildren().add(btnPrimary);		
		
		final Button btnGenRemito = new Button("Generar Remito");
		btnGenRemito.setOnAction(v -> generarRemito());
		btnGenRemito.setId("btnSecundary");		
		hbox.getChildren().add(btnGenRemito);		
		
		
		ScrollPane scroll = new ScrollPane(screen);
		scroll.setContent(screen);
		scroll.setMaxHeight(Double.MAX_VALUE);
		getChildren().add(scroll);
		getChildren().add(hbox);
	}
	
	private void generarRemito() {
		save();
		List<RemitoField> fields = remitoFieldService.findAll();
	}

	private void save() {
		remitoFieldService.deleteAll();
		List<RemitoField> fields = tblRemito.getItems();
		List<RemitoField> fieldsValid =fields.stream().filter(f -> validateNull(f)).collect(Collectors.toList());
		for(RemitoField f: fieldsValid) {
			remitoFieldService.save(f);				
		}
	}
	
	private boolean validateNull(RemitoField f) {
		return !f.getDato().isEmpty() && !f.getPosX().isEmpty() && !f.getPosY().isEmpty();
	}

	private List<RemitoField> buildItems(){
		List<RemitoField> items = new ArrayList<>();
		items.add(new RemitoField(RemitoFieldType.DENOMINACION.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.DOMICILIO.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.LOCALIDAD.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.PROVINCIA.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.CUIT.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.CONDUCTOR.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.ACOPLADO.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.PESO_ENTRADA.label, "", ""));
		items.add(new RemitoField(RemitoFieldType.PESO_SALIDA.label, "", ""));
		return items;
	}
	
	
	
}

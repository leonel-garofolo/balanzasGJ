package com.balanzasgj.app.view.settings;

import java.util.ArrayList;
import java.util.List;

import com.balanzasgj.app.informes.model.RemitoFieldType;

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
	private TableView<Field> tblRemito;
	public RemitoView(){
		initialize();
	}		

	public Node build() {		
		return this;
	}
	
	private void initialize() {
		screen = new VBox();
		Label lbl = new Label("Indique en la tabla las posiciones vertical y horizontal de cada variable en centimetros:");
		lbl.setPadding(new Insets(5, 0, 5, 0));
		screen.getChildren().add(lbl);	
		screen.setMaxHeight(Double.MAX_VALUE);				
		setMaxHeight(Double.MAX_VALUE);
		
		List<Field> items = buildItems();
		tblRemito = new TableView<RemitoView.Field>();
		tblRemito.setEditable(true);
		TableColumn<RemitoView.Field, String> colFieldName = new TableColumn<RemitoView.Field, String>();
		colFieldName.setText("Variable");
		colFieldName.setCellValueFactory(new PropertyValueFactory<>("dato"));
		
		TableColumn<RemitoView.Field, String> colFieldPosX = new TableColumn<RemitoView.Field, String>();
		colFieldPosX.setText("Posición X");
		colFieldPosX.setCellFactory(TextFieldTableCell.forTableColumn());
		colFieldPosX.setOnEditCommit(e ->{
			e.getRowValue().setPosX(e.getNewValue());
		});				
		colFieldPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
		
		TableColumn<RemitoView.Field, String> colFieldPosY = new TableColumn<RemitoView.Field, String>();
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
		hbox.setPadding(new Insets(5, 0, 5, 0));
		final Button btnPrimary = new Button("Guardar");
		btnPrimary.setOnAction(v -> {
			List<Field> fields = tblRemito.getItems();
			for(Field f: fields) {
				System.out.println(f.getDato() + "|" + f.getPosX() + "|" + f.getPosY());
			}
		});
		btnPrimary.setId("btnPrimary");		
		hbox.getChildren().add(btnPrimary);		
		
		
		ScrollPane scroll = new ScrollPane(screen);
		scroll.setContent(screen);
		scroll.setMaxHeight(Double.MAX_VALUE);
		getChildren().add(scroll);
		getChildren().add(hbox);
	}
	
	private List<Field> buildItems(){
		List<Field> items = new ArrayList<>();
		items.add(new Field(RemitoFieldType.DENOMINACION.label, "", ""));
		items.add(new Field(RemitoFieldType.DOMICILIO.label, "", ""));
		items.add(new Field(RemitoFieldType.LOCALIDAD.label, "", ""));
		items.add(new Field(RemitoFieldType.PROVINCIA.label, "", ""));
		items.add(new Field(RemitoFieldType.CUIT.label, "", ""));
		items.add(new Field(RemitoFieldType.CONDUCTOR.label, "", ""));
		items.add(new Field(RemitoFieldType.ACOPLADO.label, "", ""));
		items.add(new Field(RemitoFieldType.PESO_ENTRADA.label, "", ""));
		items.add(new Field(RemitoFieldType.PESO_SALIDA.label, "", ""));
		return items;
	}
	
	public class Field {
		private String dato;
		private String posX;
		private String posY;
				
		public Field(String dato, String posX, String posY) {
			super();
			this.dato = dato;
			this.posX = posX;
			this.posY = posY;
		}
		public String getDato() {
			return dato;
		}
		public void setDato(String dato) {
			this.dato = dato;
		}
		public String getPosX() {
			return posX;
		}
		public void setPosX(String posX) {
			this.posX = posX;
		}
		public String getPosY() {
			return posY;
		}
		public void setPosY(String posY) {
			this.posY = posY;
		}
		
		
	}
	
}

package com.balanzasgj.app.view.settings;

import com.balanzasgj.app.App;
import com.balanzasgj.app.informes.RemitoReport;
import com.balanzasgj.app.informes.ReportBase.PAGE_FORMAT;
import com.balanzasgj.app.informes.model.RemitoFieldType;
import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.model.RemitoField;
import com.balanzasgj.app.services.GlobalParameterService;
import com.balanzasgj.app.services.RemitoFieldService;
import com.balanzasgj.app.utils.Message;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;
import org.javafx.controls.customs.view.ComboBoxAutoCompleteView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RemitoView extends VBox {
	final static Logger logger = Logger.getLogger(RemitoView.class);
	private VBox screen;	
	private ComboBoxAutoCompleteView<String> setupPage;
	private TableView<RemitoField> tblRemito;
	private final RemitoFieldService remitoFieldService;
	private final GlobalParameterService globalParameterService; 
	
	public RemitoView(){
		this.remitoFieldService = new RemitoFieldService();
		this.globalParameterService = new GlobalParameterService();
		initialize();
	}		

	public Node build() {		
		return this;
	}
	
	private void initialize() {
		screen = new VBox();		
		this.setupPage = new ComboBoxAutoCompleteView<String>("Seleccione el formato de pagina");
		setupPage.addItem(PAGE_FORMAT.A4.label);
		setupPage.getItems().add(PAGE_FORMAT.A5.label);
		screen.getChildren().add(setupPage);

		String valorPageSetup = globalParameterService.get(GlobalParameter.P_REMITO_PAGE_FORMAT);
		if(!valorPageSetup.isEmpty())
			setupPage.setValue(globalParameterService.get(GlobalParameter.P_REMITO_PAGE_FORMAT));
		
		
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
		
		TableColumn<RemitoField, String> colFieldPosX = new TableColumn();
		colFieldPosX.setText("Posición X");
		colFieldPosX.setCellFactory(TextFieldTableCell.forTableColumn());

		colFieldPosX.setOnEditCommit(e ->{
			String value = e.getNewValue();
			try{
				e.getRowValue().setPosX(Double.valueOf(value).toString());
			}catch (NumberFormatException error){
				Message.error("Solo valor numerico ( numeros 0 al 9 y '.' )");
				tblRemito.refresh();
			}
		});				
		colFieldPosX.setCellValueFactory(new PropertyValueFactory<>("posX"));
		
		TableColumn<RemitoField, String> colFieldPosY = new TableColumn<RemitoField, String>();
		colFieldPosY.setText("Posición Y");
		colFieldPosY.setCellFactory(TextFieldTableCell.forTableColumn());
		colFieldPosY.setOnEditCommit(e ->{
			String value = e.getNewValue();
			try{
				e.getRowValue().setPosY(Double.valueOf(value).toString());
			}catch (NumberFormatException error){
				Message.error("Solo valor numerico ( numeros 0 al 9 y '.' )");
				tblRemito.refresh();
			}
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
		btnPrimary.setOnAction(v -> {
			if(save())
				Message.info("Se ha guardado correctamente!");
			else
				Message.error("Seleccione el formato de Hoja");
		});
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
		if(save()) {
			Map<String, Object> data = new HashMap();
			data.put(RemitoFieldType.FECHA.label, "<TARA_FECHA>");
			data.put(RemitoFieldType.DENOMINACION.label, "<TARA_CLIENTE_DENOM>");
			data.put(RemitoFieldType.DOMICILIO.label, "<TARA_CLIENTE_DIR>");
			data.put(RemitoFieldType.LOCALIDAD.label,"<TARA_CLIENTE_LOC>");
			data.put(RemitoFieldType.PROVINCIA.label, "<TARA_CLIENTE_PROV>");
			data.put(RemitoFieldType.CUIT.label, "<TARA_CLIENTE_CUIT>");
			data.put(RemitoFieldType.CONDUCTOR.label, "<TARA_CONDUCTOR>");
			data.put(RemitoFieldType.CONDUCTOR_DNI.label, "<TARA_CONDUCTOR_DNI>");
			data.put(RemitoFieldType.CHASIS.label, "<TARA_CHASIS>");
			data.put(RemitoFieldType.ACOPLADO.label, "<TARA_ACOPLADO>");
			data.put(RemitoFieldType.TRANSPORTE.label, "<TARA_TRANSPORTE>");
			data.put(RemitoFieldType.PESO_ENTRADA.label, "<PESO_ENTRADA>");
			data.put(RemitoFieldType.PESO_SALIDA.label, "<PESO_SALIDA>");
			data.put(RemitoFieldType.PESO_NETO.label, "<PESO_NETO>");
			
			RemitoReport remito = new RemitoReport(RemitoReport.PAGE_FORMAT.A4, data, remitoFieldService.findAll());
			try {
				remito.build();
				boolean status = remito.show();
				if(!status)
					Message.error("Las posiciones ingresadas producen un desborde de hoja");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
		

	private boolean save() {
		if(setupPage.getSelectionModel().getSelectedItem() != null) {
			globalParameterService.save(GlobalParameter.P_REMITO_PAGE_FORMAT, setupPage.getSelectionModel().getSelectedItem());
			List<RemitoField> fields = tblRemito.getItems();
			List<RemitoField> fieldsValid =fields.stream().filter(f -> validateNull(f)).collect(Collectors.toList());
			remitoFieldService.deleteAll();
			for(RemitoField f: fieldsValid) {
				f.setId(null);
				remitoFieldService.save(f);				
			}
			return true;
		}
		return false;
	}
	
	private boolean validateNull(RemitoField f) {
		return !f.getDato().isEmpty() && !f.getPosX().isEmpty() && !f.getPosY().isEmpty();
	}

	private List<RemitoField> buildItems(){
		List<RemitoField> fields = remitoFieldService.findAll();
		List<RemitoField> items = new ArrayList<>();
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.FECHA.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.DENOMINACION.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.DOMICILIO.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.LOCALIDAD.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.PROVINCIA.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.CUIT.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.CONDUCTOR.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.CONDUCTOR_DNI.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.CHASIS.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.ACOPLADO.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.TRANSPORTE.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.PESO_ENTRADA.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.PESO_SALIDA.label, "", "")));
		items.add(findPositions(fields, new RemitoField(RemitoFieldType.PESO_NETO.label, "", "")));
		return items;
	}
	
	private RemitoField findPositions(List<RemitoField> fields, RemitoField r) {			
		for(RemitoField field: fields) {
			if(field.getDato().equals(r.getDato())) {
				return field;
			}	
		}
		return r;
	}
}

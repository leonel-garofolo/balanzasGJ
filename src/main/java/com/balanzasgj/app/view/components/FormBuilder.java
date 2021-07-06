package com.balanzasgj.app.view.components;

import java.util.List;

import org.javafx.controls.customs.CheckBoxField;
import org.javafx.controls.customs.NumberField;
import org.javafx.controls.customs.StringField;

import com.balanzasgj.app.view.components.model.FormField;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FormBuilder {
	private GridPane gridPane;
	private List<FormField> fields;
	
	public FormBuilder(List<FormField> fields) {
		this.gridPane = new GridPane();		
	}
		
	public GridPane build() {		
		this.buildGridPane();
		return this.gridPane;
	}
	
	private void buildGridPane() {
		int colIndex = 0;
		int rowIndex = 0;		
		for(FormField ff: fields) {
			Label label = new Label(ff.getLabel());
			this.gridPane.add(label, colIndex, rowIndex);
			colIndex++;
			this.gridPane.add(this.buildField(ff), colIndex, rowIndex);
			rowIndex++;
		}			
	}
	
	private Node buildField(FormField formField) {		
		Node node= null;
		switch (formField.getType()) {
		case STRING:
			final StringField stringField = new StringField();
			if(formField.getValue()!= null)
				stringField.setValue((String) formField.getValue());
			node = stringField;
			break;
		case NUMBER:
			final NumberField numberField= new NumberField();
			if(formField.getValue()!= null)
				numberField.setValue((int)formField.getValue());
			break;			
		case BOOLEAN:
			CheckBoxField checkBoxField = new CheckBoxField();
			if(formField.getValue()!= null)
				checkBoxField.setSelected((boolean)formField.getValue());
			break;
		case LIST:
			ComboBox field = new ComboBox();
			break;

		default:
			break;
		}		
		return node;
	}
}

package com.balanzasgj.app.view.components.model;

public class FormField {
	private String id;
	private String label;
	private FormFieldType type ;
	private Object value;
	
	
	public FormField(String label, FormFieldType type, Object value) {
		super();
		this.label = label;
		this.type = type;
		this.value = value;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public FormFieldType getType() {
		return type;
	}

	public void setType(FormFieldType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}	
}

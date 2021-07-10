package com.balanzasgj.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "remito_field")
public class RemitoField {
	
	@DatabaseField(id = true)
	private Long id;
	@DatabaseField(canBeNull = false)
	private String dato;
	@DatabaseField(canBeNull = false, columnName = "pos_x")
	private String posX;
	@DatabaseField(canBeNull = false, columnName = "pos_y")
	private String posY;
			
	public RemitoField() {
		super();
	}

	public RemitoField(String dato, String posX, String posY) {
		super();
		this.dato = dato;
		this.posX = posX;
		this.posY = posY;
	}		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

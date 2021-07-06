package com.balanzasgj.app.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public abstract class TransportEntity extends Entity{

	@DatabaseField(canBeNull = true, columnName = "ultimo_movimiento")
	protected Date ultimoMovimiento; // Date

	@DatabaseField(canBeNull = true)
	protected String cuit; // String
	
	
	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Date getUltimoMovimiento() {
		return ultimoMovimiento;
	}

	public void setUltimoMovimiento(Date ultimoMovimiento) {
		this.ultimoMovimiento = ultimoMovimiento;
	}
}

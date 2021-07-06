package com.balanzasgj.app.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ejes")
public class Axis implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id = true)
	private Long idEje;
	
	@DatabaseField(canBeNull = false)
	private Integer nroEje;
	@DatabaseField(canBeNull = false)
	private Double pesoEntrada;
	@DatabaseField(canBeNull = false)
	private Double pesoSalida;	
	@DatabaseField(canBeNull = false)
	private Long idTaras;
	
	public Long getIdEje() {
		return idEje;
	}
	public void setIdEje(Long idEje) {
		this.idEje = idEje;
	}
	public Integer getNroEje() {
		return nroEje;
	}
	public void setNroEje(Integer nroEje) {
		this.nroEje = nroEje;
	}
		
	public Double getPesoEntrada() {
		return pesoEntrada;
	}
	public void setPesoEntrada(Double pesoEntrada) {
		this.pesoEntrada = pesoEntrada;
	}
	public Double getPesoSalida() {
		return pesoSalida;
	}
	public void setPesoSalida(Double pesoSalida) {
		this.pesoSalida = pesoSalida;
	}
	public Long getIdTaras() {
		return idTaras;
	}
	public void setIdTaras(Long idTaras) {
		this.idTaras = idTaras;
	}	
}

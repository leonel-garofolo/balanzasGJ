package com.balanzasgj.app.model;

import java.io.Serializable;

public class Ejes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEje;
	private Integer nroEje;
	private Double peso;
	private Long idTaras;
	private String type;
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
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Long getIdTaras() {
		return idTaras;
	}
	public void setIdTaras(Long idTaras) {
		this.idTaras = idTaras;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
}

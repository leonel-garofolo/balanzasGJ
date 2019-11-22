package com.balanzasgj.app.model;

import java.io.Serializable;

public class Ejes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEje;
	private Integer nroEje;
	private Double pesoEntrada;
	private Double pesoSalida;
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

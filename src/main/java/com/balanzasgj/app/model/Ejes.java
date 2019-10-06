package com.balanzasgj.app.model;

public class Ejes {
	
	private long idEje;
	private int nroEje;
	private double peso;
	private long idTaras;
	private char type;
	
	public long getIdEje() {
		return idEje;
	}
	public void setIdEje(long idEje) {
		this.idEje = idEje;
	}
	public int getNroEje() {
		return nroEje;
	}
	public void setNroEje(int nroEje) {
		this.nroEje = nroEje;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public long getIdTaras() {
		return idTaras;
	}
	public void setIdTaras(long idTaras) {
		this.idTaras = idTaras;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}	
}

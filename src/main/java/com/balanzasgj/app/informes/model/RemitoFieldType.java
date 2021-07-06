package com.balanzasgj.app.informes.model;

public enum RemitoFieldType {
	DENOMINACION("Denominación"),
	DOMICILIO("Domicilio"),
	LOCALIDAD("Localidad"),
	PROVINCIA("Provincia"),
	CONDUCTOR("Conductor"),
	ACOPLADO("Acoplado"),
	PESO_ENTRADA("Peso Entrada"),
	PESO_SALIDA("Peso Salida"),	
	CUIT("Cuit");		
	public final String label;

    private RemitoFieldType(String label) {
        this.label = label;
    }

}

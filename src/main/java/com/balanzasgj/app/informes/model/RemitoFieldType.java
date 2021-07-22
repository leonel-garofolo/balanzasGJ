package com.balanzasgj.app.informes.model;

public enum RemitoFieldType {
	FECHA("Fecha"),
	DENOMINACION("Denominación"),
	DOMICILIO("Domicilio"),
	LOCALIDAD("Localidad"),
	PROVINCIA("Provincia"),
	CONDUCTOR("Conductor"),
	CONDUCTOR_DNI("Conductor DNI"),
	CHASIS("Chasis"),
	ACOPLADO("Acoplado"),
	TRANSPORTE("Transporte"),
	PRODUCTO("Producto"),
	PESO_ENTRADA("Peso Entrada"),
	PESO_SALIDA("Peso Salida"),
	PESO_NETO("Peso Neto"),	
	CUIT("Cuit");		
	public final String label;

    private RemitoFieldType(String label) {
        this.label = label;
    }

}

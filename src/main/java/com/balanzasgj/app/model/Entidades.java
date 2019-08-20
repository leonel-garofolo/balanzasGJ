package com.balanzasgj.app.model;

public class Entidades {
	
	protected Long    codigo       ; // Id or Primary Key

	protected String     nombre       ;
  //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------
    /**
     * Set the "nombre" field value
     * This field is mapped on the database column "nombre" ( type "VARCHAR", NotNull : false ) 
     * @param nombre
     */
    public void setNombre( String nombre ) {
        this.nombre = nombre;
    }
    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
     * Get the "nombre" field value
     * This field is mapped on the database column "nombre" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getNombre() {
        return this.nombre;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer();       
        sb.append(nombre);
        return sb.toString(); 
    } 
}

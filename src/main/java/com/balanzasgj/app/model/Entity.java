package com.balanzasgj.app.model;

import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;

public abstract class Entity {
	
	@DatabaseField(id = true)
	protected Long    codigo       ; // Id or Primary Key

	@DatabaseField(canBeNull = true)
	protected String     nombre       ;

	 // BigDecimal 
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
        if(nombre != null) {
        	sb.append(nombre);
        } else {
        	sb.append("");
        }
        return sb.toString(); 
    } 
}

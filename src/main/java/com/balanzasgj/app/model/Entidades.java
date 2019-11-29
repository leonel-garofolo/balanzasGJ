package com.balanzasgj.app.model;

import java.math.BigDecimal;
import java.util.Date;

public class Entidades {
	
	protected Long    codigo       ; // Id or Primary Key

	protected String     nombre       ;
	protected String cuit ;  // String 
	 
	protected String alias ;  // String 

	protected Date ultimoMovimiento ;  // Date 

	protected BigDecimal acumulado ;  // BigDecimal 
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
    


    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}



	public Date getUltimoMovimiento() {
		return ultimoMovimiento;
	}



	public void setUltimoMovimiento(Date ultimoMovimiento) {
		this.ultimoMovimiento = ultimoMovimiento;
	}



	public BigDecimal getAcumulado() {
		return acumulado;
	}



	public void setAcumulado(BigDecimal acumulado) {
		this.acumulado = acumulado;
	}
	
	

    public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
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

/*
 * Created on 2019-12-28 ( Date ISO 2019-12-28 - Time 12:27:25 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */


package com.balanzasgj.app.model;

import java.io.Serializable;

/**
 * Java bean for entity "ata" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author Telosys Tools Generator
 *
 */
public class Ata extends Entidades implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String nacionalidad;

    /**
     * Default constructor
     */
    public Ata() {
        super();
    }
    
    public String getNacionalidad() {
		return nacionalidad;
	}
    
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
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

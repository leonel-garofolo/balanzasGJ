/*
 * Created on 2019-11-19 ( Date ISO 2019-11-19 - Time 00:11:09 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */


package com.balanzasgj.app.model;

import java.io.Serializable;

/**
 * Java bean for entity "importadores_exportadores" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author Telosys Tools Generator
 *
 */
public class ImportadoresExportadores extends Entidades implements Serializable
{
    private static final long serialVersionUID = 1L;    
    /**
     * Default constructor
     */
    public ImportadoresExportadores() {
        super();
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

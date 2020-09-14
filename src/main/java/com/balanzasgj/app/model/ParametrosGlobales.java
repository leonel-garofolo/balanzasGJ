/*
 * Java bean class for entity "parametros_goblales" 
 * Created on 2019-07-27 ( Date ISO 2019-07-27 - Time 22:46:02 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.balanzasgj.app.model;

import java.io.Serializable;
import java.sql.Blob;


/**
 * Java bean for entity "parametros_goblales"
 * 
 * @author Telosys Tools Generator
 *
 */
public class ParametrosGlobales implements Serializable
{
	public static final String P_EMPRESA_RESTORE= "EMPRESA_RESTORE";
	public static final String P_EMPRESA_NOMBRE= "EMPRESA_NOMBRE";
	public static final String P_EMPRESA_DIR= "EMPRESA_DIR";
	public static final String P_EMPRESA_LOC= "EMPRESA_LOC";
	public static final String P_EMPRESA_PROV = "EMPRESA_PROV";
	public static final String P_EMPRESA_TEL= "EMPRESA_TEL";
	public static final String P_EMPRESA_NOMBRE_BAL= "EMPRESA_NOMBRE_BAL";
	public static final String P_EMPRESA_DIR_BAL= "EMPRESA_DIR_BAL";
	public static final String P_EMPRESA_LOC_BAL= "EMPRESA_LOC_BAL";
	public static final String P_EMPRESA_PROV_BAL = "EMPRESA_PROV_BAL";
	public static final String P_EMPRESA_TEL_BAL= "EMPRESA_TEL_BAL";
	public static final String P_TICKET_ETIQUETADORA= "TICKET_ETIQUETADORA";
	public static final String P_NUM_BALANZAS= "NUM_BALANZAS";
	
	public static final String P_EMPRESA_IMG= "EMPRESA_IMG";	
	public static final String P_EMPRESA_ING_MANUAL= "EMPRESA_ING_MANUAL";
	public static final String P_EMPRESA_TICKET= "EMPRESA_TICKET";
	
	public static final String P_EMPRESA_TRANSACCION= "EMPRESA_TRANSACCION";
	public static final String P_EMPRESA_BACKUP= "EMPRESA_BACKUP";
	public static final String P_EMPRESA_AUTOMATICO= "EMPRESA_AUTOMATICO";
	public static final String P_ACTIVAR_DEBUG= "ACTIVAR_DEBUG";
	public static final String P_REPORT_COPY= "REPORT_COPY";
	
	public static final String A_CODIGO_ADUANA= "CODIGO_ADUANA";	                           
	public static final String A_CODIGO_LOG= "CODIGO_LOG";
	public static final String A_CERTIFICADO= "CERTIFICADO";
	public static final String A_VENCIMIENTO= "VENCIMIENTO";
	
	public static final String I_INDICADOR1= "INDICADOR#1";
	public static final String I_INDICADOR2= "INDICADOR#2";
	
    private static final long serialVersionUID = 1L;

    private String    id           ; // Id or Primary Key

    private String     value        ;
    private Blob     valueByte        ;
    

    /**
     * Default constructor
     */
    public ParametrosGlobales() {
        super();
    }
    
    
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	//----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------
    /**
     * Set the "value" field value
     * This field is mapped on the database column "value" ( type "VARCHAR", NotNull : false ) 
     * @param value
     */
    public void setValue( String value ) {
        this.value = value;
    }
    /**
     * Get the "value" field value
     * This field is mapped on the database column "value" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getValue() {
        return this.value;
    }    
   
    public Blob getValueByte() {
		return valueByte;
	}


	public void setValueByte(Blob valueByte) {
		this.valueByte = valueByte;
	}


	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(value);
        return sb.toString(); 
    } 

}

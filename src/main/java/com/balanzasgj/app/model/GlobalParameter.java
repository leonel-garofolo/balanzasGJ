/*
 * Java bean class for entity "parametros_goblales" 
 * Created on 2019-07-27 ( Date ISO 2019-07-27 - Time 22:46:02 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.balanzasgj.app.model;

import java.io.Serializable;
import java.sql.Blob;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "parametros_globales")
public class GlobalParameter implements Serializable
{
	public static final String P_EMPRESA_RESTORE= "EMPRESA_RESTORE";
	public static final String P_EMPRESA_NOMBRE= "EMPRESA_NOMBRE";
	public static final String P_EMPRESA_DIR= "EMPRESA_DIR";
	public static final String P_EMPRESA_LOC= "EMPRESA_LOC";
	public static final String P_EMPRESA_PROV = "EMPRESA_PROV";
	public static final String P_EMPRESA_TEL= "EMPRESA_TEL";
	public static final String P_EMPRESA_EMAIL= "EMPRESA_EMAIL";
	public static final String P_EMPRESA_NOMBRE_BAL= "EMPRESA_NOMBRE_BAL";
	public static final String P_EMPRESA_DIR_BAL= "EMPRESA_DIR_BAL";
	public static final String P_EMPRESA_LOC_BAL= "EMPRESA_LOC_BAL";
	public static final String P_EMPRESA_PROV_BAL = "EMPRESA_PROV_BAL";
	public static final String P_EMPRESA_TEL_BAL= "EMPRESA_TEL_BAL";
	public static final String P_EMPRESA_EMAIL_BAL= "EMPRESA_EMAIL_BAL";
	public static final String P_TICKET_ETIQUETADORA= "TICKET_ETIQUETADORA";
	public static final String P_NUM_BALANZAS= "NUM_BALANZAS";
	public static final String P_NUM_COMPROBANTE= "NUM_COMPROBANTE";	
		
	// Datos de la Empresa
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

	public static final String P_VALIDACION_ENTRADA= "VALIDACION_ENTRADA";
	public static final String P_VALIDACION_SALIDA= "VALIDACION_SALIDA";
	
	//csv
	public static final String P_EXPORT_PATH= "EXPORT_PATH";
	public static final String P_USER_WINDOWS= "USER_WINDOWS";
	public static final String P_PASS_WINDOWS= "PASS_WINDOWS";
	
	public static final int V_DOCUMENTO = 1;
	public static final int V_CONDUCTOR = 2;
	public static final int V_NACIONALIDAD = 3;
	public static final int V_CHASIS = 4;
	public static final int V_FACTURA = 5;
	public static final int V_OBSERVACION = 6;
	public static final int V_PRODUCTO = 7;
	public static final int V_TRANSPORTE = 8;
	public static final int V_CLIENTE = 9;
	public static final int V_PROCEDENCIA = 10;

    private static final long serialVersionUID = 1L;

    @DatabaseField(id = true)
    private String    id           ; // Id or Primary Key

    @DatabaseField(canBeNull = false)
    private String     value        ;
    
    @DatabaseField(dataType = DataType.BYTE_ARRAY)
    private Blob     valueByte        ;
    

    /**
     * Default constructor
     */
    public GlobalParameter() {
        super();
        this.valueByte = null;
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

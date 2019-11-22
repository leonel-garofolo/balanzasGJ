/*
 * Created on 2019-11-19 ( Date ISO 2019-11-19 - Time 00:11:09 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */


package com.balanzasgj.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private String cuit ;  // String 

    private Date ultimoMovimiento ;  // Date 

    private BigDecimal acumulado ;  // BigDecimal 

    /**
     * Default constructor
     */
    public ImportadoresExportadores() {
        super();
    }
       
    /**
     * Set the "cuit" field value
     * This field is mapped on the database column "CUIT" ( type "VARCHAR", NotNull : false ) 
     * @param cuit
     */
    public void setCuit( String cuit ) {
        this.cuit = cuit;
    }
    /**
     * Get the "cuit" field value
     * This field is mapped on the database column "CUIT" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getCuit() {
        return this.cuit;
    }

    /**
     * Set the "ultimoMovimiento" field value
     * This field is mapped on the database column "ultimo_movimiento" ( type "DATETIME", NotNull : false ) 
     * @param ultimoMovimiento
     */
    public void setUltimoMovimiento( Date ultimoMovimiento ) {
        this.ultimoMovimiento = ultimoMovimiento;
    }
    /**
     * Get the "ultimoMovimiento" field value
     * This field is mapped on the database column "ultimo_movimiento" ( type "DATETIME", NotNull : false ) 
     * @return the field value
     */
    public Date getUltimoMovimiento() {
        return this.ultimoMovimiento;
    }

    /**
     * Set the "acumulado" field value
     * This field is mapped on the database column "acumulado" ( type "DECIMAL", NotNull : false ) 
     * @param acumulado
     */
    public void setAcumulado( BigDecimal acumulado ) {
        this.acumulado = acumulado;
    }
    /**
     * Get the "acumulado" field value
     * This field is mapped on the database column "acumulado" ( type "DECIMAL", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getAcumulado() {
        return this.acumulado;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codigo);
        sb.append("|");
        sb.append(nombre);
        sb.append("|");
        sb.append(cuit);
        sb.append("|");
        sb.append(ultimoMovimiento);
        sb.append("|");
        sb.append(acumulado);
        return sb.toString(); 
    } 



}
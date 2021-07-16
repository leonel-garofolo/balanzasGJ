/*
 * Java bean class for entity "clientes" 
 * Created on 2019-06-27 ( Date ISO 2019-06-27 - Time 21:04:01 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.balanzasgj.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


/**
 * Java bean for entity "clientes"
 * 
 * @author Telosys Tools Generator
 *
 */
@DatabaseTable(tableName = "clientes")
public class Client extends EntityLong implements Serializable
{
    private static final long serialVersionUID = 1L;
   

    /**
     * Default constructor
     */
    public Client() {
        super();
    }
    
    @DatabaseField(canBeNull = true)
	protected String cuit; // String

    @DatabaseField(canBeNull = true)
    protected String direccion; // String

    @DatabaseField(canBeNull = true)
    protected String localidad; // String

    @DatabaseField(canBeNull = true)
    protected String provincia; // String

    @DatabaseField(canBeNull = true)
    protected String denominacion; // String

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer();       
        sb.append(nombre);
        return sb.toString(); 
    }
}

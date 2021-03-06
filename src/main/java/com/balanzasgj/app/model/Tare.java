/*
 * Java bean class for entity "taras" 
 * Created on 2019-07-17 ( Date ISO 2019-07-17 - Time 00:00:40 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */

package com.balanzasgj.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "taras")
public class Tare implements Serializable
{
    private static final long serialVersionUID = 1L;

    public enum ACTION{
        T_NORMAL("NORMAL"),
        T_CON_TARA("CON TARA"),
        T_TOMAR_TARA("TOMAR TARA");

        public final String label;

        private ACTION(String label) {
            this.label = label;
        }
    };

    public enum MODO{
        M_ESTANDAR("ESTANDAR"),
        M_ADUANA("ADUANA"),
        M_PUBLICA("PUBLICA");

        public final String label;

        private MODO(String label) {
            this.label = label;
        }
    };

    public enum TIPO{
        C_COMPLETO("COMPLETO"),
        C_POR_EJE("POR EJE");

        public final String label;

        private TIPO(String label) {
            this.label = label;
        }
    };

    private static final String C_COMPLETO = "COMPLETO";
    private static final String C_POR_EJE = "POR EJE";

    @DatabaseField(columnName = "idTaras", generatedId = true)
    private Long    idtaras      ; // Id or Primary Key

    @DatabaseField(canBeNull = false)
    private String     transaccion  ;
    @DatabaseField(canBeNull = false, columnName = "fecha_entrada")
    private Date       fechaEntrada        ;
    @DatabaseField(columnName = "fecha_salida")
    private Date 		fechaSalida  ;
    @DatabaseField()
    private String     balanza      ;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_producto", foreignColumnName = "codigo")
    private Product    producto   ;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_cliente", foreignColumnName = "codigo")
    private Client    cliente    ;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_transporte", foreignColumnName = "codigo")
    private Transport    transporte ;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_procedencia", foreignColumnName = "codigo")
    private Origin procedencias;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_imp_exp", foreignColumnName = "codigo")
    private ImportAndExport impExp;
    @DatabaseField(canBeNull = true)
    private String     modalidad ;
    @DatabaseField(canBeNull = true, columnName = "comprobante_nun1")
    private String     comprobanteNun1 ;
    @DatabaseField(canBeNull = true, columnName = "modoTara")
    private String     modoTara ;
    @DatabaseField(canBeNull = true, columnName = "destino")
    private String     destino      ;
    @DatabaseField(canBeNull = true, columnName = "conductor")
    private String     conductor    ;
    @DatabaseField(canBeNull = true, columnName = "tipo_doc")
    private String     tipoDoc      ;
    @DatabaseField(canBeNull = true, columnName = "num_doc")
    private String     numDoc       ;
    @DatabaseField(foreign = true, columnName = "patente", foreignColumnName = "codigo")
    private Patent    patente      ;
    @DatabaseField(canBeNull = true, columnName = "patente_aceptado")
    private String     patenteAceptado ;
    @DatabaseField(canBeNull = true, columnName = "observacion")
    private String     observacion  ;
    @DatabaseField(canBeNull = true, columnName = "observacion_aduana")
    private String     observacionAduana  ;
    @DatabaseField(canBeNull = true, columnName = "contenedor_num")
    private String     contenedorNum ;
    @DatabaseField(canBeNull = false, columnName = "peso_entrada")
    private BigDecimal pesoEntrada  ;
    @DatabaseField(canBeNull = true, columnName = "peso_salida")
    private BigDecimal pesoSalida   ;
    @DatabaseField(persisted = false)
    private BigDecimal pesoNeto   ;
    @DatabaseField(canBeNull = true)
    private String     modoChasis ;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "id_ata", foreignColumnName = "codigo")
    private Ata ata;
    @DatabaseField(canBeNull = true)
    private String contenedor;
    @DatabaseField(canBeNull = true)
    private String manifiesto;
    @DatabaseField(canBeNull = true)
    private String mercaderia;
    @DatabaseField(canBeNull = true)
    private String nacionalidad;

    /**
     * Default constructor
     */
    public Tare() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "idtaras" field value
     * This field is mapped on the database column "idtaras" ( type "INT", NotNull : true ) 
     * @param idtaras
     */
    

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS
    //----------------------------------------------------------------------
    /**
     * Set the "transaccion" field value
     * This field is mapped on the database column "transaccion" ( type "VARCHAR", NotNull : false ) 
     * @param transaccion
     */
    public void setTransaccion( String transaccion ) {
        this.transaccion = transaccion;
    }
    public Long getIdtaras() {
		return idtaras;
	}

	public void setIdtaras(Long idtaras) {
		this.idtaras = idtaras;
	}

	/**
     * Get the "transaccion" field value
     * This field is mapped on the database column "transaccion" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getTransaccion() {
        return this.transaccion;
    }
   
    public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
     * Set the "balanza" field value
     * This field is mapped on the database column "balanza" ( type "VARCHAR", NotNull : false ) 
     * @param balanza
     */
    public void setBalanza( String balanza ) {
        this.balanza = balanza;
    }
    /**
     * Get the "balanza" field value
     * This field is mapped on the database column "balanza" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getBalanza() {
        return this.balanza;
    }

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}
	/**
     * Set the "modalidad" field value
     * This field is mapped on the database column "comprobante_tipo" ( type "VARCHAR", NotNull : false ) 
     * @param modalidad
     */
    public void setModalidad( String modalidad ) {
        this.modalidad = modalidad;
    }
    /**
     * Get the "modalidad" field value
     * This field is mapped on the database column "comprobante_tipo" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getModalidad() {
        return this.modalidad;
    }

    /**
     * Set the "comprobanteNun1" field value
     * This field is mapped on the database column "comprobante_nun1" ( type "VARCHAR", NotNull : false ) 
     * @param comprobanteNun1
     */
    public void setComprobanteNun1( String comprobanteNun1 ) {
        this.comprobanteNun1 = comprobanteNun1;
    }
    /**
     * Get the "comprobanteNun1" field value
     * This field is mapped on the database column "comprobante_nun1" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getComprobanteNun1() {
        return this.comprobanteNun1;
    }

    /**
     * Set the "modoTara" field value
     * This field is mapped on the database column "comprobante_num_2" ( type "VARCHAR", NotNull : false ) 
     * @param modoTara
     */
    public void setModoTara( String modoTara ) {
        this.modoTara = modoTara;
    }
    /**
     * Get the "modoTara" field value
     * This field is mapped on the database column "comprobante_num_2" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getModoTara() {
        return this.modoTara;
    }

    /**
     * Set the "destino" field value
     * This field is mapped on the database column "destino" ( type "VARCHAR", NotNull : false ) 
     * @param destino
     */
    public void setDestino( String destino ) {
        this.destino = destino;
    }
    /**
     * Get the "destino" field value
     * This field is mapped on the database column "destino" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getDestino() {
        return this.destino;
    }

    /**
     * Set the "conductor" field value
     * This field is mapped on the database column "conductor" ( type "VARCHAR", NotNull : false ) 
     * @param conductor
     */
    public void setConductor( String conductor ) {
        this.conductor = conductor;
    }
    /**
     * Get the "conductor" field value
     * This field is mapped on the database column "conductor" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getConductor() {
        return this.conductor;
    }

    /**
     * Set the "tipoDoc" field value
     * This field is mapped on the database column "tipo_doc" ( type "VARCHAR", NotNull : false ) 
     * @param tipoDoc
     */
    public void setTipoDoc( String tipoDoc ) {
        this.tipoDoc = tipoDoc;
    }
    /**
     * Get the "tipoDoc" field value
     * This field is mapped on the database column "tipo_doc" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getTipoDoc() {
        return this.tipoDoc;
    }

    /**
     * Set the "numDoc" field value
     * This field is mapped on the database column "num_doc" ( type "VARCHAR", NotNull : false ) 
     * @param numDoc
     */
    public void setNumDoc( String numDoc ) {
        this.numDoc = numDoc;
    }
    /**
     * Get the "numDoc" field value
     * This field is mapped on the database column "num_doc" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getNumDoc() {
        return this.numDoc;
    }
   

    /**
     * Set the "patenteAceptado" field value
     * This field is mapped on the database column "patente_aceptado" ( type "VARCHAR", NotNull : false ) 
     * @param patenteAceptado
     */
    public void setPatenteAceptado( String patenteAceptado ) {
        this.patenteAceptado = patenteAceptado;
    }
    /**
     * Get the "patenteAceptado" field value
     * This field is mapped on the database column "patente_aceptado" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getPatenteAceptado() {
        return this.patenteAceptado;
    }

    /**
     * Set the "observacion" field value
     * This field is mapped on the database column "observacion" ( type "VARCHAR", NotNull : false ) 
     * @param observacion
     */
    public void setObservacion( String observacion ) {
        this.observacion = observacion;
    }
    /**
     * Get the "observacion" field value
     * This field is mapped on the database column "observacion" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getObservacion() {
        return this.observacion;
    }

    /**
     * Set the "contenedorNum" field value
     * This field is mapped on the database column "contenedor_num" ( type "VARCHAR", NotNull : false ) 
     * @param contenedorNum
     */
    public void setContenedorNum( String contenedorNum ) {
        this.contenedorNum = contenedorNum;
    }
    /**
     * Get the "contenedorNum" field value
     * This field is mapped on the database column "contenedor_num" ( type "VARCHAR", NotNull : false ) 
     * @return the field value
     */
    public String getContenedorNum() {
        return this.contenedorNum;
    }

    /**
     * Set the "pesoEntrada" field value
     * This field is mapped on the database column "peso_entrada" ( type "DECIMAL", NotNull : false ) 
     * @param pesoEntrada
     */
    public void setPesoEntrada( BigDecimal pesoEntrada ) {
        this.pesoEntrada = pesoEntrada;
    }
    /**
     * Get the "pesoEntrada" field value
     * This field is mapped on the database column "peso_entrada" ( type "DECIMAL", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getPesoEntrada() {
        return this.pesoEntrada;
    }

    /**
     * Set the "pesoSalida" field value
     * This field is mapped on the database column "peso_salida" ( type "DECIMAL", NotNull : false ) 
     * @param pesoSalida
     */
    public void setPesoSalida( BigDecimal pesoSalida ) {
        this.pesoSalida = pesoSalida;
    }
    /**
     * Get the "pesoSalida" field value
     * This field is mapped on the database column "peso_salida" ( type "DECIMAL", NotNull : false ) 
     * @return the field value
     */
    public BigDecimal getPesoSalida() {
        return this.pesoSalida;
    }

    public Product getProducto() {
        return producto;
    }

    public BigDecimal getPesoNeto() {
        if(pesoEntrada == null || pesoSalida == null){
            pesoNeto = null;
        }else{
            pesoNeto = new BigDecimal(pesoEntrada.doubleValue() - pesoSalida.doubleValue());
        }
        return pesoNeto;
    }

    public void setPesoNeto(BigDecimal pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public Transport getTransporte() {
        return transporte;
    }

    public void setTransporte(Transport transporte) {
        this.transporte = transporte;
    }

    public Origin getProcedencias() {
        return procedencias;
    }

    public void setProcedencias(Origin procedencias) {
        this.procedencias = procedencias;
    }
    
    public String getModoChasis() {
		return modoChasis;
	}

	public void setModoChasis(String modoChasis) {
		this.modoChasis = modoChasis;
	}		

	public ImportAndExport getImpExp() {
		return impExp;
	}

	public void setImpExp(ImportAndExport impExp) {
		this.impExp = impExp;
	}		

	public Patent getPatente() {
		return patente;
	}

	public void setPatente(Patent patente) {
		this.patente = patente;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public String getContenedor() {
		return contenedor;
	}

	public void setContenedor(String contenedor) {
		this.contenedor = contenedor;
	}

	public String getManifiesto() {
		return manifiesto;
	}

	public void setManifiesto(String manifiesto) {
		this.manifiesto = manifiesto;
	}
	
	

	public String getMercaderia() {
		return mercaderia;
	}

	public void setMercaderia(String mercaderia) {
		this.mercaderia = mercaderia;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getObservacionAduana() {
		return observacionAduana;
	}

	public void setObservacionAduana(String observacionAduana) {
		this.observacionAduana = observacionAduana;
	}

	//----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idtaras);
        sb.append("|");
        sb.append(transaccion);
        sb.append("|");
        sb.append(balanza);
        sb.append("|");
        sb.append(cliente);
        sb.append("|");
        sb.append(modalidad);
        sb.append("|");
        sb.append(comprobanteNun1);
        sb.append("|");
        sb.append(modoTara);
        sb.append("|");
        sb.append(destino);
        sb.append("|");
        sb.append(conductor);
        sb.append("|");
        sb.append(tipoDoc);
        sb.append("|");
        sb.append(numDoc);
        sb.append("|");
        sb.append(patente);
        sb.append("|");
        sb.append(patenteAceptado);
        sb.append("|");
        sb.append(observacion);
        sb.append("|");
        sb.append(contenedorNum);
        sb.append("|");
        sb.append(pesoEntrada);
        sb.append("|");
        sb.append(pesoSalida);
        return sb.toString(); 
    } 

}

/*
 * Created on 2019-07-17 ( Date ISO 2019-07-17 - Time 00:00:51 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Named;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.ImportadoresExportadores;
import com.balanzasgj.app.model.Patentes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Taras;
import com.balanzasgj.app.model.Transportes;
import com.balanzasgj.app.persistence.TarasPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

/**
 * Taras persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("TarasPersistence")
public class TarasPersistenceJdbc extends GenericJdbcDAO<Taras> implements TarasPersistence {

	private final static String SQL_SELECT_ALL = 
		"select idtaras, transaccion, fecha_entrada, fecha_salida, balanza, t.id_producto, p.nombre as nombreProducto, t.id_cliente, c.nombre as nombreCli, t.id_transporte, tra.nombre as nombreTra, tra.cuit as cuitTra, t.id_procedencia, pro.nombre as nombrePro, ie.codigo as codigoIE, ie.nombre as nombreIE, modalidad, comprobante_nun1, modoTara, destino, conductor, tipo_doc, num_doc, patente, patente_aceptado, observacion, observacion_aduana, contenedor_num, TRIM(peso_entrada) + 0 as peso_entrada, TRIM(peso_salida) + 0 as peso_salida, modoChasis, contenedor, manifiesto, id_ata, a.nombre as ata_nombre, a.CUIT as ata_cuit, mercaderia, t.nacionalidad " +
				"from taras t " +
				"left join clientes c on c.codigo = t.id_cliente " +
				"left join productos p on p.codigo = t.id_producto " +
				"left join transportes tra on tra.codigo = t.id_transporte " +
				"left join procedencias pro on pro.codigo = t.id_procedencia " +
				"left join importadores_exportadores ie on ie.codigo = t.id_imp_exp " +
				"left join ata a on a.codigo = t.id_ata ";


	private final static String SQL_SELECT =
			"select idtaras, transaccion, fecha_entrada, fecha_salida, balanza, t.id_producto, p.nombre as nombreProducto, t.id_cliente, c.nombre as nombreCli, t.id_transporte, tra.nombre as nombreTra, tra.cuit as cuitTra, t.id_procedencia, pro.nombre as nombrePro, ie.codigo as codigoIE, ie.nombre as nombreIE, modalidad, comprobante_nun1, modoTara, destino, conductor, tipo_doc, num_doc, patente, patente_aceptado, observacion, observacion_aduana, contenedor_num, TRIM(peso_entrada) + 0 as peso_entrada, TRIM(peso_salida) + 0 as peso_salida, modoChasis, contenedor, manifiesto, id_ata, a.nombre as ata_nombre, a.CUIT as ata_cuit, mercaderia, t.nacionalidad  " +
					"from taras t " +
					"left join clientes c on c.codigo = t.id_cliente " +
					"left join productos p on p.codigo = t.id_producto " +
					"left join transportes tra on tra.codigo = t.id_transporte " +
					"left join procedencias pro on pro.codigo = t.id_procedencia " +
					"left join importadores_exportadores ie on ie.codigo = t.id_imp_exp " +
					"left join ata a on a.codigo = t.id_ata " +
					"where idtaras = ?";

	private final static String SQL_INSERT = 
		"insert into taras ( transaccion, fecha_entrada, fecha_salida, balanza, id_producto, id_cliente, id_transporte, id_procedencia, id_imp_exp, modalidad, comprobante_nun1, modoTara, destino, conductor, tipo_doc, num_doc, patente, patente_aceptado, observacion, observacion_aduana , contenedor_num, peso_entrada, peso_salida, modoChasis, id_ata, contenedor, manifiesto, mercaderia, nacionalidad ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ? )";

	private final static String SQL_UPDATE = 
		"update taras set transaccion = ?, fecha_entrada = ?, fecha_salida = ?, balanza = ?, id_producto = ?, id_cliente = ?, id_transporte = ?, id_procedencia = ?, id_imp_exp = ?, modalidad = ?, comprobante_nun1 = ?, modoTara = ?, destino = ?, conductor = ?, tipo_doc = ?, num_doc = ?, patente = ?, patente_aceptado = ?, observacion = ?, observacion_aduana = ?, contenedor_num = ?, peso_entrada = ?, peso_salida = ?, modoChasis = ?, id_ata=?, contenedor =?, manifiesto=?, mercaderia=?, nacionalidad=? where idtaras = ?";

	private final static String SQL_DELETE = 
		"delete from taras where idtaras = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from taras";

	private final static String SQL_COUNT = 
		"select count(*) from taras where idtaras = ?";
	
	private final static String SQL_COUNT_PENDING = 
			"select count(*) as total from taras where peso_salida is null && patente = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public TarasPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(Taras record, long value) {
		record.setIdtaras(value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Taras taras) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, taras.getIdtaras() ) ; // "idtaras" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Taras taras) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "idtaras" is auto-incremented => no set in insert		
		setValue(ps, i++, taras.getTransaccion() ) ; // "transaccion" : java.lang.String
		setValue(ps, i++, taras.getFechaEntrada() ) ; // "fecha" : java.util.Date
		setValue(ps, i++, taras.getFechaSalida()) ; // "fecha" : java.util.Date
		setValue(ps, i++, taras.getBalanza() ) ; // "balanza" : java.lang.String
		if(taras.getProducto() == null)
			ps.setNull(i++, Types.INTEGER);
		else
			setValue(ps, i++, taras.getProducto().getCodigo() ) ; // "id_producto" : java.lang.Integer

		if(taras.getCliente() == null)
			ps.setNull(i++, Types.INTEGER);
		else
			setValue(ps, i++, taras.getCliente().getCodigo()) ; // "id_cliente" : java.lang.Integer

		if(taras.getTransporte() != null) {
			setValue(ps, i++, taras.getTransporte().getCodigo() ) ; // "id_transporte" : java.lang.Integer
		}else {
			ps.setNull(i++, Types.INTEGER);
		}
		
		if(taras.getProcedencias() != null) {
			setValue(ps, i++, taras.getProcedencias().getCodigo() ) ; // "id_procedencia" : java.lang.Integer
		}else {
			ps.setNull(i++, Types.INTEGER);
		}
		
		if (taras.getImpExp() != null) {
			setValue(ps, i++, taras.getImpExp().getCodigo() ) ; // "id_procedencia" : java.lang.Integer
		} else {
			ps.setNull(i++, Types.INTEGER);
		}
				
		setValue(ps, i++, taras.getModalidad() ) ; // "modalidad" : java.lang.String
		setValue(ps, i++, taras.getComprobanteNun1() ) ; // "comprobante_nun1" : java.lang.String
		setValue(ps, i++, taras.getModoTara() ) ; // "modoTara" : java.lang.String
		setValue(ps, i++, taras.getDestino() ) ; // "destino" : java.lang.String
		setValue(ps, i++, taras.getConductor() ) ; // "conductor" : java.lang.String
		setValue(ps, i++, taras.getTipoDoc() ) ; // "tipo_doc" : java.lang.String
		setValue(ps, i++, taras.getNumDoc() ) ; // "num_doc" : java.lang.String
		setValue(ps, i++, taras.getPatente().getPatente() ) ; // "patente" : java.lang.String
		setValue(ps, i++, taras.getPatenteAceptado() ) ; // "patente_aceptado" : java.lang.String
		setValue(ps, i++, taras.getObservacion() ) ; // "observacion" : java.lang.String
		setValue(ps, i++, taras.getObservacionAduana() ) ; // "observacionAduana" : java.lang.String
		setValue(ps, i++, taras.getContenedorNum() ) ; // "contenedor_num" : java.lang.String
		setValue(ps, i++, taras.getPesoEntrada() ) ; // "peso_entrada" : java.math.BigDecimal
		setValue(ps, i++, taras.getPesoSalida() ) ; // "peso_salida" : java.math.BigDecimal
		setValue(ps, i++, taras.getModoChasis() ) ;
		
		if (taras.getAta() != null) {
			setValue(ps, i++, taras.getAta().getCodigo());
		} else {
			ps.setNull(i++, Types.INTEGER);
		}
		setValue(ps, i++, taras.getContenedor() ) ;
		setValue(ps, i++, taras.getManifiesto() ) ;
		setValue(ps, i++, taras.getMercaderia() ) ;
		setValue(ps, i++, taras.getNacionalidad() ) ;
		
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Taras taras) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, taras.getTransaccion() ) ; // "transaccion" : java.lang.String
		setValue(ps, i++, taras.getFechaEntrada() ) ; // "fecha" : java.util.Date
		setValue(ps, i++, taras.getFechaSalida() ) ; // "fecha" : java.util.Date
		setValue(ps, i++, taras.getBalanza() ) ; // "balanza" : java.lang.String
		setValue(ps, i++, taras.getProducto().getCodigo() ) ; // "id_producto" : java.lang.Integer
		setValue(ps, i++, taras.getCliente().getCodigo()) ; // "id_cliente" : java.lang.Integer
		if(taras.getTransporte() != null) {
			setValue(ps, i++, taras.getTransporte().getCodigo() ) ; // "id_transporte" : java.lang.Integer	
		} else {
			ps.setNull(i++, java.sql.Types.BIGINT);
		}
		
		setValue(ps, i++, taras.getProcedencias().getCodigo() ) ; // "id_procedencia" : java.lang.Integer
		if(taras.getImpExp() != null) {
			setValue(ps, i++, taras.getImpExp().getCodigo() ) ; // "id_procedencia" : java.lang.Integer	
		} else {
			ps.setNull(i++, java.sql.Types.BIGINT);
		}
		
		setValue(ps, i++, taras.getModalidad() ) ; // "modalidad" : java.lang.String
		setValue(ps, i++, taras.getComprobanteNun1() ) ; // "comprobante_nun1" : java.lang.String
		setValue(ps, i++, taras.getModoTara() ) ; // "modoTara" : java.lang.String
		setValue(ps, i++, taras.getDestino() ) ; // "destino" : java.lang.String
		setValue(ps, i++, taras.getConductor() ) ; // "conductor" : java.lang.String
		setValue(ps, i++, taras.getTipoDoc() ) ; // "tipo_doc" : java.lang.String
		setValue(ps, i++, taras.getNumDoc() ) ; // "num_doc" : java.lang.String
		setValue(ps, i++, taras.getPatente().getPatente() ) ; // "patente" : java.lang.String
		setValue(ps, i++, taras.getPatenteAceptado() ) ; // "patente_aceptado" : java.lang.String
		setValue(ps, i++, taras.getObservacion() ) ; // "observacion" : java.lang.String
		setValue(ps, i++, taras.getObservacionAduana() ) ; // "observacionAduana" : java.lang.String
		setValue(ps, i++, taras.getContenedorNum() ) ; // "contenedor_num" : java.lang.String
		setValue(ps, i++, taras.getPesoEntrada() ) ; // "peso_entrada" : java.math.BigDecimal
		setValue(ps, i++, taras.getPesoSalida() ) ; // "peso_salida" : java.math.BigDecimal
		setValue(ps, i++, taras.getModoChasis() ) ;
		if (taras.getAta() != null) {
			setValue(ps, i++, taras.getAta().getCodigo());
		} else {
			ps.setNull(i++, Types.INTEGER);
		}
		setValue(ps, i++, taras.getContenedor() ) ;
		setValue(ps, i++, taras.getManifiesto() ) ;
		setValue(ps, i++, taras.getMercaderia()) ;
		setValue(ps, i++, taras.getNacionalidad() ) ;
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, taras.getIdtaras() ) ; // "idtaras" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idtaras;
	 * @return the new instance
	 */
	private Taras newInstanceWithPrimaryKey( Long idtaras ) {
		Taras taras = new Taras();
		taras.setIdtaras( idtaras );
		return taras ;
	}

	//----------------------------------------------------------------------
	@Override
	protected Taras newInstance() {
		return new Taras() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected Taras populateBean(ResultSet rs, Taras taras) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		taras.setIdtaras(rs.getLong("idtaras")); // java.lang.Integer
		if ( rs.wasNull() ) { taras.setIdtaras(null); }; // not primitive number => keep null value if any
		taras.setTransaccion(rs.getString("transaccion")); // java.lang.String
		taras.setFechaEntrada(rs.getTimestamp("fecha_entrada")); // java.util.Date
		taras.setFechaSalida(rs.getTimestamp("fecha_salida")); // java.util.Date
		taras.setBalanza(rs.getString("balanza")); // java.lang.String

		Clientes cli = new Clientes();
		cli.setCodigo(rs.getLong("id_cliente"));
		cli.setNombre(rs.getString("nombreCli"));
		if(rs.wasNull()){
			taras.setCliente(null);
		} else
			taras.setCliente(cli); // java.lang.Integer

		Transportes tra = new Transportes();		
		tra.setCuit(rs.getString("cuitTra"));
		tra.setCodigo(rs.getLong("id_transporte"));
		tra.setNombre(rs.getString("nombreTra"));
		if(rs.wasNull()){
			taras.setTransporte(null);
		} else
			taras.setTransporte(tra); // java.lang.Integer

		Procedencias procedencias = new Procedencias();
		procedencias.setCodigo(rs.getLong("id_procedencia"));
		procedencias.setNombre(rs.getString("nombrePro"));
		if(rs.wasNull()){
			taras.setProcedencias(null);
		} else
			taras.setProcedencias(procedencias);


		Productos productos = new Productos();
		productos.setCodigo(rs.getLong("id_producto"));
		productos.setNombre(rs.getString("nombreProducto"));
		if(rs.wasNull()){
			taras.setProducto(null);
		} else
			taras.setProducto(productos);
		
		if(rs.getString("nombreIE") != null) {
			ImportadoresExportadores ie = new ImportadoresExportadores();
			ie.setCodigo(rs.getLong("codigoIE"));
			ie.setNombre(rs.getString("nombreIE"));
			taras.setImpExp(ie);
		}
		
		if(rs.getString("ata_nombre") != null) {
			Ata a = new Ata();
			a.setCodigo(rs.getLong("id_ata"));
			a.setNombre(rs.getString("ata_nombre"));
			a.setCuit(rs.getString("ata_cuit"));
			taras.setAta(a);
		}
		
		taras.setModalidad(rs.getString("modalidad")); // java.lang.String
		taras.setComprobanteNun1(rs.getString("comprobante_nun1")); // java.lang.String
		taras.setModoTara(rs.getString("modoTara")); // java.lang.String
		taras.setDestino(rs.getString("destino")); // java.lang.String
		taras.setConductor(rs.getString("conductor")); // java.lang.String
		taras.setTipoDoc(rs.getString("tipo_doc")); // java.lang.String
		taras.setNumDoc(rs.getString("num_doc")); // java.lang.String
		
		if(rs.getString("patente") != null) {
			Patentes p = new Patentes();
			p.setPatente(rs.getString("patente"));			
			taras.setPatente(p);
		}
		taras.setPatenteAceptado(rs.getString("patente_aceptado")); // java.lang.String
		taras.setObservacion(rs.getString("observacion")); // java.lang.String
		taras.setObservacionAduana(rs.getString("observacion_aduana")); // java.lang.String
		
		taras.setContenedorNum(rs.getString("contenedor_num")); // java.lang.String
		taras.setPesoEntrada(rs.getBigDecimal("peso_entrada")); // java.math.BigDecimal		
		if ( rs.wasNull() ) { taras.setPesoEntrada(null); }; // not primitive number => keep null value if any
		taras.setPesoSalida(rs.getBigDecimal("peso_salida")); // java.math.BigDecimal
		if ( rs.wasNull() ) { taras.setPesoSalida(null); }; // not primitive number => keep null value if any
		
		taras.setModoChasis(rs.getString("modoChasis"));
		taras.setContenedor(rs.getString("contenedor"));
		taras.setManifiesto(rs.getString("manifiesto"));
		taras.setMercaderia(rs.getString("mercaderia"));
		taras.setNacionalidad(rs.getString("nacionalidad"));

		return taras ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public Taras findById( Long idtaras ) {
		Taras taras = newInstanceWithPrimaryKey( idtaras ) ;
		if ( super.doSelect(taras) ) {
			return taras ;
		}
		else {
			return null ; // Not found
		}
	}

	@Override
	public List<Taras> findByFieldInformes(String field, String data, Date fechaDesde, Date fechaHasta) {
		List<Taras> list = new LinkedList<Taras>() ;
		Connection conn = null;

		String selectWithFilter = getSqlSelectAll() + " where 1=1 ";
		if(field.equals("Número de Transacción")){
			selectWithFilter += " and transaccion like '%" + data + "%' ";
		}else if(field.equals("Patente")){
			selectWithFilter += " and patente like '%" + data + "%' ";
		}else if(field.equals("Producto")){
			selectWithFilter += " and p.nombre like '%" + data + "%' ";
		}else if(field.equals("Cliente")){
			selectWithFilter += " and c.nombre like '%" + data + "%' ";
		}else if(field.equals("Transporte")){
			selectWithFilter += " and  tra.nombre  like '%" + data + "%' ";
		}else if(field.equals("Procedencia")){
			selectWithFilter += " and pro.nombre like '%" + data + "%' ";
		}
		
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		if(fechaDesde != null) {
			selectWithFilter += " and fecha_entrada >= '" + dFormat.format(fechaDesde) + "' ";
		}
		if(fechaDesde != null) {
			selectWithFilter += " and fecha_entrada <= '" + dFormat.format(fechaHasta) + "' ";
		}
		
		selectWithFilter += " order by update_date desc";
		System.out.println(selectWithFilter);
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(selectWithFilter);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				Taras bean = newInstance();
				populateBean(rs, bean);
				list.add(bean);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return list;
	}

	@Override
	public List<Taras> findByField(String field, String data, boolean salidasPendientes) {
		List<Taras> list = new LinkedList<Taras>() ;
		Connection conn = null;

		String selectWithFilter = getSqlSelectAll() + " where 1=1 ";
		if(salidasPendientes) {
			selectWithFilter += " and peso_salida is null ";
		}
		if(field.equals("Número de Transacción")){
			selectWithFilter += " and transaccion like '%" + data + "%' ";
		}else if(field.equals("Patente")){
			selectWithFilter += " and patente like '%" + data + "%' ";
		}else if(field.equals("Producto")){
			selectWithFilter += " and p.nombre like '%" + data + "%' ";
		}else if(field.equals("Cliente")){
			selectWithFilter += " and c.nombre like '%" + data + "%' ";
		}else if(field.equals("Transporte")){
			selectWithFilter += " and  tra.nombre  like '%" + data + "%' ";
		}else if(field.equals("Procedencia")){
			selectWithFilter += " and pro.nombre like '%" + data + "%' ";
		}
		selectWithFilter += " order by update_date desc";
		System.out.println(selectWithFilter);
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(selectWithFilter);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				Taras bean = newInstance();
				populateBean(rs, bean);
				list.add(bean);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return list;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public List<Taras> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param taras
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( Taras taras ) {
		return super.doSelect(taras) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param taras
	 */
	public long insert(Taras taras) {
		Long key = super.doInsertAutoIncr(taras);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public Taras create(Taras taras) {
		insert(taras);
		return taras ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public boolean update(Taras taras) {
		int r = super.doUpdate(taras);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public Taras save(Taras taras) {
		if ( super.doExists(taras) ) {
			super.doUpdate(taras);
		}
		else {
			Connection conn = null;
			try {
				conn = getConnection();
				PreparedStatement ps = conn.prepareStatement( getSqlInsert() );
				//--- Call specific method to set the values to be inserted
				setValuesForInsert(ps, 1, taras); 
				//--- Execute SQL INSERT
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
                if(rs.next())
                {
                	taras.setIdtaras(rs.getLong(1)) ;
                }
                
				ps.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				closeConnection(conn);
			}
		}
		return taras ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public boolean deleteById( Long idtaras ) {
		Taras taras = newInstanceWithPrimaryKey( idtaras ) ;
		int r = super.doDelete(taras);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public boolean delete( Taras taras ) {
		int r = super.doDelete(taras);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idtaras;
	 * @return
	 */
	// @Override
	public boolean exists( Long idtaras ) {
		Taras taras = newInstanceWithPrimaryKey( idtaras ) ;
		return super.doExists(taras);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param taras
	 * @return
	 */
	// @Override
	public boolean exists( Taras taras ) {
		return super.doExists(taras);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long countAll() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelectAll() {
		return SQL_SELECT_ALL;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}

	@Override
	public boolean checkPending(String patente) {
		int total = 0;
		Connection conn = null;		
		try {
			
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_COUNT_PENDING);
			ps.setString(1, patente);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				total =rs.getInt("total");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		
		return total > 0;
	}
}

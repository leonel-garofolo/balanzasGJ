package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.model.Client;
import com.balanzasgj.app.model.ImportAndExport;
import com.balanzasgj.app.model.Origin;
import com.balanzasgj.app.model.Patent;
import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.model.Transport;
import com.balanzasgj.app.persistence.TareDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class TareDaoImpl extends GenericJdbcDAO<Tare, Long> implements TareDao{
	final static Logger logger = Logger.getLogger(TareDaoImpl.class);
	private final static String SQL_COUNT_PENDING = 
			"select count(*) as total from taras where peso_salida is null && patente = ?";
	
	private final static String SQL_SELECT_ALL = 
			"select idtaras, transaccion, fecha_entrada, fecha_salida, balanza, t.id_producto, p.nombre as nombreProducto, t.id_cliente, c.nombre as nombreCli, t.id_transporte, tra.nombre as nombreTra, tra.cuit as cuitTra, t.id_procedencia, pro.nombre as nombrePro, ie.codigo as codigoIE, ie.nombre as nombreIE, modalidad, comprobante_nun1, modoTara, destino, conductor, tipo_doc, num_doc, patente, patente_aceptado, observacion, observacion_aduana, contenedor_num, TRIM(peso_entrada) + 0 as peso_entrada, TRIM(peso_salida) + 0 as peso_salida, modoChasis, contenedor, manifiesto, id_ata, a.nombre as ata_nombre, a.CUIT as ata_cuit, mercaderia, t.nacionalidad " +
					"from taras t " +
					"left join clientes c on c.codigo = t.id_cliente " +
					"left join productos p on p.codigo = t.id_producto " +
					"left join transportes tra on tra.codigo = t.id_transporte " +
					"left join procedencias pro on pro.codigo = t.id_procedencia " +
					"left join importadores_exportadores ie on ie.codigo = t.id_imp_exp " +
					"left join ata a on a.codigo = t.id_ata ";

	public TareDaoImpl() throws SQLException{
		super(Tare.class);
	}	

	@Override
	public Long save(Tare entity) {
		try {
			if(entity.getIdtaras() == null)
				create(entity);
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getIdtaras();
	}

	@Override
	public long checkPending(String patente) {
		long total = 0;		
		try {
			total = queryRawValue(SQL_COUNT_PENDING, patente);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return total;		
	}

	@Override
	public List<Tare> findByField(String field, String data, boolean salidasPendientes) {		
		String selectWithFilter = SQL_SELECT_ALL + " where 1=1 ";
		if(salidasPendientes) {
			selectWithFilter += " and peso_salida is null ";
		}
		if(field.equals("Número de Transacción")){
			selectWithFilter += " and transaccion like '%?%' ";
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
		logger.info(selectWithFilter);
		List<Tare> tares = new ArrayList<Tare>();
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(selectWithFilter);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				Tare taras = new Tare();
				populateBean(rs, taras);
				tares.add(taras);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return tares;
	}

	@Override
	public List<Tare> findByFieldInformes(String field, String data, Date fechaDesde, Date fechaHasta) {
		String selectWithFilter = SQL_SELECT_ALL + " where 1=1 ";
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
		logger.info(selectWithFilter);
		List<Tare> tares = new ArrayList<Tare>();
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(selectWithFilter);
			//--- Execute SQL SELECT
			ResultSet rs = ps.executeQuery();
			while ( rs.next() ) {
				Tare taras = new Tare();
				populateBean(rs, taras);
				tares.add(taras);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return tares;
	}
	
	
	private void populateBean(ResultSet rs, Tare taras) throws SQLException{
		taras.setIdtaras(rs.getLong("idtaras")); // java.lang.Integer
		if ( rs.wasNull() ) { taras.setIdtaras(null); }; // not primitive number => keep null value if any
		taras.setTransaccion(rs.getString("transaccion")); // java.lang.String
		taras.setFechaEntrada(rs.getTimestamp("fecha_entrada")); // java.util.Date
		taras.setFechaSalida(rs.getTimestamp("fecha_salida")); // java.util.Date
		taras.setBalanza(rs.getString("balanza")); // java.lang.String

		Client cli = new Client();
		cli.setCodigo(rs.getLong("id_cliente"));
		cli.setNombre(rs.getString("nombreCli"));
		if(rs.wasNull()){
			taras.setCliente(null);
		} else
			taras.setCliente(cli); // java.lang.Integer

		Transport tra = new Transport();		
		tra.setCuit(rs.getString("cuitTra"));
		tra.setCodigo(rs.getLong("id_transporte"));
		tra.setNombre(rs.getString("nombreTra"));
		if(rs.wasNull()){
			taras.setTransporte(null);
		} else
			taras.setTransporte(tra); // java.lang.Integer

		Origin procedencias = new Origin();
		procedencias.setCodigo(rs.getLong("id_procedencia"));
		procedencias.setNombre(rs.getString("nombrePro"));
		if(rs.wasNull()){
			taras.setProcedencias(null);
		} else
			taras.setProcedencias(procedencias);


		Product productos = new Product();
		productos.setCodigo(rs.getLong("id_producto"));
		productos.setNombre(rs.getString("nombreProducto"));
		if(rs.wasNull()){
			taras.setProducto(null);
		} else
			taras.setProducto(productos);
		
		if(rs.getString("nombreIE") != null) {
			ImportAndExport ie = new ImportAndExport();
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
			Patent p = new Patent();
			p.setCodigo(rs.getString("patente"));
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
	}
}

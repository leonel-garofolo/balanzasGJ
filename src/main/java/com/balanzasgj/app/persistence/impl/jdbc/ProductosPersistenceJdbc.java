/*
 * Created on 2019-06-27 ( Date ISO 2019-06-27 - Time 20:59:47 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Named;

import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.persistence.ProductosPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

/**
 * Productos persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("ProductosPersistence")
public class ProductosPersistenceJdbc extends GenericJdbcDAO<Productos> implements ProductosPersistence{

	private final static String SQL_SELECT_ALL = 
		"select codigo, nombre from productos"; 

	private final static String SQL_SELECT = 
		"select codigo, nombre from productos where codigo = ?";

	private final static String SQL_INSERT = 
		"insert into productos ( nombre ) values ( ? )";

	private final static String SQL_UPDATE = 
		"update productos set nombre = ? where codigo = ?";

	private final static String SQL_DELETE = 
		"delete from productos where codigo = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from productos";

	private final static String SQL_COUNT = 
		"select count(*) from productos where codigo = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public ProductosPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(Productos record, long value) {
		record.setCodigo(value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Productos productos) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, productos.getCodigo() ) ; // "codigo" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Productos productos) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "codigo" is auto-incremented => no set in insert		
		setValue(ps, i++, productos.getNombre() ) ; // "nombre" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Productos productos) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, productos.getNombre() ) ; // "nombre" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, productos.getCodigo() ) ; // "codigo" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param codigo;
	 * @return the new instance
	 */
	private Productos newInstanceWithPrimaryKey( Long codigo ) {
		Productos productos = new Productos();
		productos.setCodigo( codigo );
		return productos ;
	}

	//----------------------------------------------------------------------
	@Override
	protected Productos newInstance() {
		return new Productos() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected Productos populateBean(ResultSet rs, Productos productos) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		productos.setCodigo(rs.getLong("codigo")); // java.lang.Integer
		if ( rs.wasNull() ) { productos.setCodigo(null); }; // not primitive number => keep null value if any
		productos.setNombre(rs.getString("nombre")); // java.lang.String
		return productos ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public Productos findById( Long codigo ) {
		Productos productos = newInstanceWithPrimaryKey( codigo ) ;
		if ( super.doSelect(productos) ) {
			return productos ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	public List<Productos> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param productos
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( Productos productos ) {
		return super.doSelect(productos) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param productos
	 */
	public long insert(Productos productos) {
		Long key = super.doInsertAutoIncr(productos);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public Productos create(Productos productos) {
		insert(productos);
		return productos ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean update(Productos productos) {
		int r = super.doUpdate(productos);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public Productos save(Productos productos) {
		if ( super.doExists(productos) ) {
			super.doUpdate(productos);
		}
		else {
			super.doInsert(productos);
		}
		return productos ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean deleteById( Long codigo ) {
		Productos productos = newInstanceWithPrimaryKey( codigo ) ;
		int r = super.doDelete(productos);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	
	public boolean delete( Productos productos ) {
		int r = super.doDelete(productos);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param codigo;
	 * @return
	 */
	// @Override
	public boolean exists( Long codigo ) {
		Productos productos = newInstanceWithPrimaryKey( codigo ) ;
		return super.doExists(productos);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param productos
	 * @return
	 */
	// @Override
	public boolean exists( Productos productos ) {
		return super.doExists(productos);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
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

}

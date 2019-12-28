/*
 * Created on 2019-07-06 ( Date ISO 2019-07-06 - Time 10:30:48 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */

package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Named;

import com.balanzasgj.app.model.Comunicaciones;
import com.balanzasgj.app.persistence.ComunicacionesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

/**
 * Comunicaciones persistence implementation 
 * 
 * @author Telosys
 *
 */
@Named("ComunicacionesPersistence")
public class ComunicacionesPersistenceJdbc extends GenericJdbcDAO<Comunicaciones> implements ComunicacionesPersistence {

	private final static String SQL_SELECT_ALL = 
		"select idcomunicaciones, nombre, idindicadores from comunicaciones"; 

	private final static String SQL_SELECT = 
		"select idcomunicaciones, nombre, idindicadores from comunicaciones where idcomunicaciones = ?";

	private final static String SQL_INSERT = 
		"insert into comunicaciones ( nombre, idindicadores ) values ( ?, ? )";
	private final static String SQL_INSERT_2 = 
			"insert into comunicaciones ( idcomunicaciones, nombre, idindicadores ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update comunicaciones set nombre = ?, idindicadores = ? where idcomunicaciones = ?";

	private final static String SQL_DELETE = 
		"delete from comunicaciones where idcomunicaciones = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from comunicaciones";

	private final static String SQL_COUNT = 
		"select count(*) from comunicaciones where idcomunicaciones = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public ComunicacionesPersistenceJdbc() {
		super();
	}

    //----------------------------------------------------------------------
	@Override
	protected void setAutoIncrementedKey(Comunicaciones record, long value) {
		record.setIdcomunicaciones(value);
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Comunicaciones comunicaciones) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, comunicaciones.getIdcomunicaciones() ) ; // "idcomunicaciones" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Comunicaciones comunicaciones) throws SQLException {
		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "idcomunicaciones" is auto-incremented => no set in insert		
		setValue(ps, i++, comunicaciones.getNombre() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, comunicaciones.getIdindicadores() ) ; // "idindicadores" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Comunicaciones comunicaciones) throws SQLException {
		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, comunicaciones.getNombre() ) ; // "nombre" : java.lang.String
		setValue(ps, i++, comunicaciones.getIdindicadores() ) ; // "idindicadores" : java.lang.Integer
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, comunicaciones.getIdcomunicaciones() ) ; // "idcomunicaciones" : java.lang.Integer
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param idcomunicaciones;
	 * @return the new instance
	 */
	private Comunicaciones newInstanceWithPrimaryKey( Long idcomunicaciones ) {
		Comunicaciones comunicaciones = new Comunicaciones();
		comunicaciones.setIdcomunicaciones( idcomunicaciones );
		return comunicaciones ;
	}

	//----------------------------------------------------------------------
	@Override
	protected Comunicaciones newInstance() {
		return new Comunicaciones() ;
	}

    //----------------------------------------------------------------------
	@Override
	protected Comunicaciones populateBean(ResultSet rs, Comunicaciones comunicaciones) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		comunicaciones.setIdcomunicaciones(rs.getLong("idcomunicaciones")); // java.lang.Integer
		if ( rs.wasNull() ) { comunicaciones.setIdcomunicaciones(null); }; // not primitive number => keep null value if any
		comunicaciones.setNombre(rs.getString("nombre")); // java.lang.String
		comunicaciones.setIdindicadores(rs.getInt("idindicadores")); // java.lang.Integer
		if ( rs.wasNull() ) { comunicaciones.setIdindicadores(null); }; // not primitive number => keep null value if any
		return comunicaciones ;
	}

	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public Comunicaciones findById( Long idcomunicaciones ) {
		Comunicaciones comunicaciones = newInstanceWithPrimaryKey( idcomunicaciones ) ;
		if ( super.doSelect(comunicaciones) ) {
			return comunicaciones ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public List<Comunicaciones> findAll() {
		return super.doSelectAll();
	}

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param comunicaciones
	 * @return true if found, false if not found
	 */
	//@Override
	public boolean load( Comunicaciones comunicaciones ) {
		return super.doSelect(comunicaciones) ;
	}

    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param comunicaciones
	 */
	public long insert(Comunicaciones comunicaciones) {
		Long key = super.doInsertAutoIncr(comunicaciones);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public Comunicaciones create(Comunicaciones comunicaciones) {
		insert(comunicaciones);
		return comunicaciones ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public boolean update(Comunicaciones comunicaciones) {
		int r = super.doUpdate(comunicaciones);
		return r > 0 ;

	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public Comunicaciones save(Comunicaciones comunicaciones) {
		if ( super.doExists(comunicaciones) ) {
			super.doUpdate(comunicaciones);
		}
		else {			
			comunicaciones.setIdcomunicaciones(doInsertAutoIncr(comunicaciones));
		}
		return comunicaciones ;
	}	
	
	protected Long doInsertAutoIncr(Comunicaciones com) {
		Long generatedKey = 0L ;
		Connection conn = null;
		try {
			conn = getConnection();
			PreparedStatement ps = conn.prepareStatement( SQL_INSERT_2, PreparedStatement.RETURN_GENERATED_KEYS );
			//--- Call specific method to set the values to be inserted
			
			setValue(ps, 1, com.getIdcomunicaciones() ) ;
			setValuesForInsert(ps, 2, com); 
			//--- Execute SQL INSERT
			ps.executeUpdate();
			//--- Retrieve the generated key 
			ResultSet rs = ps.getGeneratedKeys();
			if ( rs.next() ) {
				generatedKey = rs.getLong(1);
			}
			rs.close();
			//--- End
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeConnection(conn);
		}
		return generatedKey ;
	}	

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */
	@Override
	public boolean deleteById( Long idcomunicaciones ) {
		Comunicaciones comunicaciones = newInstanceWithPrimaryKey( idcomunicaciones ) ;
		int r = super.doDelete(comunicaciones);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interface 
	 */	
	public boolean delete( Comunicaciones comunicaciones ) {
		int r = super.doDelete(comunicaciones);
		return r > 0 ;
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param idcomunicaciones;
	 * @return
	 */
	// @Override
	public boolean exists( Long idcomunicaciones ) {
		Comunicaciones comunicaciones = newInstanceWithPrimaryKey( idcomunicaciones ) ;
		return super.doExists(comunicaciones);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param comunicaciones
	 * @return
	 */
	// @Override
	public boolean exists( Comunicaciones comunicaciones ) {
		return super.doExists(comunicaciones);
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

}

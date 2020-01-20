package com.balanzasgj.app.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

@SuppressWarnings("rawtypes")
public class UpdateDB extends GenericJdbcDAO{
	final static Logger logger = Logger.getLogger(UpdateDB.class);
	private int currentVersion; // ultimo script corrido	
	private String query;	
	
	public void run()  {
		Connection conn = null;
		Statement st = null;		
		query = "";	
		try {		
			conn = getConnection();
			createTableVersion();			
			st = conn.createStatement();			
			if(currentVersion < 1) {
				query = "alter table ata ADD COLUMN nacionalidad VARCHAR(255)";
				st.execute(query);
				query = "ALTER TABLE clientes ADD COLUMN CUIT VARCHAR(45) NULL AFTER nombre";
				st.execute(query);				
				insertQueryExecute(1);
			}
			
		}catch (Exception e) {
			logger.error("update DDBB ERROR", e);
		}finally {			
			closeConnection(conn, st);
		}		
	}
	
	private void createTableVersion() {
		Connection conn = null;
		Statement st = null;
		try {		
			conn = getConnection();
			st = conn.createStatement();
			query = "SHOW TABLES LIKE 'versionado'";
			boolean existTable = false;
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				existTable = true;
			}
			rs.close();
						
			if(existTable) {
				query = "select max(numero_sql) as numero_sql from versionado";
				rs = st.executeQuery(query);
				if(rs.next()) {
					currentVersion= rs.getInt("numero_sql");
				}
				rs.close();
			}else {
				query = "CREATE TABLE versionado ( " + 
						"  idversionado INT NOT NULL AUTO_INCREMENT, " + 
						"  numero_sql INT NOT NULL, " + 
						"  update_sql DATETIME NULL DEFAULT current_timestamp(), " + 
						"  PRIMARY KEY (idversionado))";
				st.execute(query);	
				currentVersion= 0;
			}
			st.close();
		}catch (Exception e) {
			logger.error("createTableVersion ERROR", e);
		}finally {
			closeConnection(conn);
		}	
	}
	
	private void insertQueryExecute(int version) {
		Connection conn = null;
		Statement st = null;
		try {		
			conn = getConnection();					
			st = conn.createStatement();
			query = "insert into versionado (numero_sql) values(" + version + ")";
			st.execute(query);
			st.close();
		}catch (Exception e) {
			logger.error("createTableVersion ERROR", e);
		}finally {
			closeConnection(conn);
		}
	}

	@Override
	protected Object newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlSelectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getSqlCountAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Object bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Object bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Object bean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object populateBean(ResultSet rs, Object bean) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setAutoIncrementedKey(Object bean, long id) {
		// TODO Auto-generated method stub
		
	}
}

package com.balanzasgj.app.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.balanzasgj.app.persistence.impl.jdbc.commons.DataSourceProvider;

@SuppressWarnings("rawtypes")
public class UpdateDB extends UtilDB{
	
	final static Logger logger = Logger.getLogger(UpdateDB.class);
	private int currentVersion; // ultimo script corrido
	private String query;

	public void run() {
		Connection conn = null;
		Statement st = null;
		query = "";
		try {
			conn = DataSourceProvider.getConnection();
			createTableVersion();
			st = conn.createStatement();
			if (currentVersion < 1) {
				try {
					query = "alter table ata ADD COLUMN nacionalidad VARCHAR(255)";
					st.execute(query);	
				}catch (Exception e) {					
				}				
				try {
					query = "ALTER TABLE clientes ADD COLUMN CUIT VARCHAR(45) NULL AFTER nombre";
					st.execute(query);	
				}catch (Exception e) {					
				}
				
				try {
					query = "delete from comunicaciones";
					st.execute(query);
				}catch (Exception e) {
				}
				try {
					query = "alter table taras add COLUMN observacion_aduana VARCHAR(255) NULL AFTER observacion";
					st.execute(query);
				}catch (Exception e) {
				}
				insertQueryExecute(1);
			}

			if (currentVersion < 2) {
				query = "alter table taras add column nacionalidad varchar(255) NULL";
				st.execute(query);
				insertQueryExecute(2);
			}
			if (currentVersion < 3) {
				query = "CREATE TABLE `reports` ( " +
						"  `id` int(11) NOT NULL AUTO_INCREMENT, " +
						"  `tara_id` int(11) NOT NULL, " +
						"  `count` int(11) NOT NULL DEFAULT '1', " +
						"  PRIMARY KEY (`id`), " +
						"  KEY `FK_reports_tara_idx` (`tara_id`), " +
						"  CONSTRAINT `FK_reports_tara` FOREIGN KEY (`tara_id`) REFERENCES `taras` (`idtaras`) ON DELETE NO ACTION ON UPDATE NO ACTION " +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8";
				st.execute(query);
				query = "insert into parametros_globales(id, value) values('VALIDACION_ENTRADA', '')";
				st.execute(query);
				query = "insert into parametros_globales(id, value) values('VALIDACION_SALIDA', '7,8,9,10')";
				st.execute(query);
				insertQueryExecute(3);
			}
			if (currentVersion < 4) {
				query = "alter table indicadores add column is_eje bit(1) NULL";
				st.execute(query);
				insertQueryExecute(4);
			}
			if (currentVersion < 5) {
				query = "alter table patentes add column nombre varchar(255) NULL";
				st.execute(query);
				insertQueryExecute(5);
			}
			if (currentVersion < 6) {
				query = "CREATE TABLE `remito_field` ( " +
						"  `id` int(11) NOT NULL AUTO_INCREMENT, " +
						"  `dato` varchar(100) NOT NULL, " +
						"  `pos_x` varchar(10) NULL, " +
						"  `pos_y` varchar(10) NULL, " +
						"  PRIMARY KEY (`id`) " +
						") ENGINE=InnoDB DEFAULT CHARSET=utf8";
				st.execute(query);
				insertQueryExecute(6);
			}					

		} catch (Exception e) {
			logger.error("update DDBB ERROR", e);
		} finally {
			closeConnection(conn, st);
		}
	}
	


	private void createTableVersion() {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DataSourceProvider.getConnection();
			st = conn.createStatement();
			query = "SHOW TABLES LIKE 'versionado'";
			boolean existTable = false;
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				existTable = true;
			}
			rs.close();

			if (existTable) {
				query = "select max(numero_sql) as numero_sql from versionado";
				rs = st.executeQuery(query);
				if (rs.next()) {
					currentVersion = rs.getInt("numero_sql");
				}
				rs.close();
			} else {
				query ="SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'";
				st.execute(query);				
				
				query = "CREATE TABLE versionado (idversionado INT NOT NULL AUTO_INCREMENT, "
						+ "  numero_sql INT NOT NULL, update_sql DATETIME NULL, "
						+ "  PRIMARY KEY (idversionado))";
				st.execute(query);
				currentVersion = 0;
			}
			st.close();
		} catch (Exception e) {
			logger.error("createTableVersion ERROR", e);
		} finally {
			closeConnection(conn);
		}
	}

	private void insertQueryExecute(int version) {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DataSourceProvider.getConnection();
			st = conn.createStatement();
			query = "insert into versionado (numero_sql, update_sql) values(" + version + ", current_timestamp())";
			st.execute(query);
			st.close();
		} catch (Exception e) {
			logger.error("createTableVersion ERROR", e);
		} finally {
			closeConnection(conn);
		}
	}
	
	public void dropDatabase() {
		Connection conn = null;
		Statement st = null;
		try {
			conn = DataSourceProvider.getConnection();
			st = conn.createStatement();
			query = "DROP DATABASE sist_pesada";
			st.execute(query);
			query = "CREATE DATABASE sist_pesada";
			st.execute(query);
			st.close();
		} catch (Exception e) {
			logger.error("createTableVersion ERROR", e);
		} finally {
			closeConnection(conn);
		}
	}
}

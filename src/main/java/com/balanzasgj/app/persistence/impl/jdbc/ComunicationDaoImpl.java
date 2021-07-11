package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Comunication;
import com.balanzasgj.app.persistence.ComunicationDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class ComunicationDaoImpl extends GenericJdbcDAO<Comunication, Long> implements ComunicationDao{
	final static Logger logger = Logger.getLogger(ComunicationDaoImpl.class);

	public ComunicationDaoImpl() throws SQLException{
		super(Comunication.class);
	}	

	@Override
	public Long save(Comunication entity) {
		try {
			if(entity.getIdcomunicaciones() == null)
				create(entity);
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getIdcomunicaciones();
	}
}

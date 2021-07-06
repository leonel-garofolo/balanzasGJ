package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Transport;
import com.balanzasgj.app.persistence.TransportDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class TransportDaoImpl extends GenericJdbcDAO<Transport, Long> implements TransportDao{
	final static Logger logger = Logger.getLogger(TransportDaoImpl.class);

	public TransportDaoImpl() throws SQLException{
		super(Transport.class);
	}	

	@Override
	public Long save(Transport entity) {
		try {
			if(entity.getCodigo() == null)
				entity.setCodigo((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getCodigo();
	}
}

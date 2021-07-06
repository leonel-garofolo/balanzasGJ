package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Client;
import com.balanzasgj.app.persistence.ClientDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class ClientDaoImpl extends GenericJdbcDAO<Client, Long> implements ClientDao{
	final static Logger logger = Logger.getLogger(ClientDaoImpl.class);

	public ClientDaoImpl() throws SQLException{
		super(Client.class);
	}	

	@Override
	public Long save(Client entity) {
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

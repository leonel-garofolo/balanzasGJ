package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.persistence.GlobalParameterDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class GlobalParameterDaoImpl extends GenericJdbcDAO<GlobalParameter, String> implements GlobalParameterDao{
	final static Logger logger = Logger.getLogger(GlobalParameterDaoImpl.class);

	public GlobalParameterDaoImpl() throws SQLException{
		super(GlobalParameter.class);
	}

	@Override
	public GlobalParameter getById(String parameter) {
		try {
			return queryForId(parameter);
		} catch (SQLException e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public void save(GlobalParameter entity) {
		try {
			if(!idExists(entity.getId()))
				create(entity);
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}		
	}	
}

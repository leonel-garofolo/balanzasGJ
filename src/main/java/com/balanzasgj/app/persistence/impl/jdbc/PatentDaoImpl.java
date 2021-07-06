package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Patent;
import com.balanzasgj.app.persistence.PatentDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class PatentDaoImpl extends GenericJdbcDAO<Patent, String> implements PatentDao{
	final static Logger logger = Logger.getLogger(PatentDaoImpl.class);

	public PatentDaoImpl() throws SQLException{
		super(Patent.class);
	}	

	@Override
	public void save(Patent entity) {
		try {
			Patent find = queryForSameId(entity);
			if(find == null)
				entity.setCodigo((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}		
	}
}

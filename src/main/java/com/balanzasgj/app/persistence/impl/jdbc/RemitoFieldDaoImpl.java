package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.RemitoField;
import com.balanzasgj.app.persistence.RemitoFieldDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class RemitoFieldDaoImpl extends GenericJdbcDAO<RemitoField, Long> implements RemitoFieldDao{
	final static Logger logger = Logger.getLogger(RemitoFieldDaoImpl.class);

	public RemitoFieldDaoImpl() throws SQLException{
		super(RemitoField.class);
	}	

	@Override
	public Long save(RemitoField entity) {
		try {
			if(entity.getId() == null)
				entity.setId((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getId();
	}
}

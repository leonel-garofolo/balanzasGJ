package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Origin;
import com.balanzasgj.app.persistence.OriginDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class OriginDaoImpl extends GenericJdbcDAO<Origin, Long> implements OriginDao{
	final static Logger logger = Logger.getLogger(OriginDaoImpl.class);

	public OriginDaoImpl() throws SQLException{
		super(Origin.class);
	}	

	@Override
	public Long save(Origin entity) {
		try {
			if(entity.getCodigo() == null)
				create(entity);
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getCodigo();
	}
}

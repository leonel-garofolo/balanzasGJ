package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Indicator;
import com.balanzasgj.app.persistence.IndicatorDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class IndicatorDaoImpl extends GenericJdbcDAO<Indicator, Long> implements IndicatorDao{
	final static Logger logger = Logger.getLogger(IndicatorDaoImpl.class);

	public IndicatorDaoImpl() throws SQLException{
		super(Indicator.class);
	}	

	@Override
	public Long save(Indicator entity) {
		try {
			if(entity.getIdindicadores() == null)
				entity.setIdindicadores((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getIdindicadores();
	}
}

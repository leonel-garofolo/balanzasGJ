package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Axis;
import com.balanzasgj.app.persistence.AxisDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class AxisDaoImpl extends GenericJdbcDAO<Axis, Long> implements AxisDao{
	final static Logger logger = Logger.getLogger(AxisDaoImpl.class);

	public AxisDaoImpl() throws SQLException{
		super(Axis.class);
	}	

	@Override
	public Long save(Axis entity) {
		try {
			if(entity.getIdEje() == null)
				entity.setIdEje((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getIdEje();
	}
}

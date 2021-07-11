package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Operation;
import com.balanzasgj.app.persistence.OperationDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class OperationDaoImpl extends GenericJdbcDAO<Operation, Long> implements OperationDao{
	final static Logger logger = Logger.getLogger(OperationDaoImpl.class);

	public OperationDaoImpl() throws SQLException{
		super(Operation.class);
	}	

	@Override
	public Long save(Operation entity) {
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

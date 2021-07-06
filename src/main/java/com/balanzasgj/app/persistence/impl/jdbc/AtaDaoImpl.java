package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.persistence.AtaDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class AtaDaoImpl extends GenericJdbcDAO<Ata, Long> implements AtaDao{
	final static Logger logger = Logger.getLogger(AtaDaoImpl.class);

	public AtaDaoImpl() throws SQLException{
		super(Ata.class);
	}	

	@Override
	public Long save(Ata entity) {
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

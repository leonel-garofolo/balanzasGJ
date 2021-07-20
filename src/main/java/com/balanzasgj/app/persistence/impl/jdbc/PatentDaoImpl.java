package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;
import java.util.Date;

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
			if(entity.getNombre() == null)
				entity.setNombre(entity.getCodigo());
			if(entity.getUpdate() == null)
				entity.setUpdate(new Date());

			if(entity.getTara() == null)
				entity.setTara(0.0);

			if(idExists(entity.getCodigo()))
				update(entity);
			else
				create(entity);
		} catch (SQLException e) {
			logger.error(e);
		}		
	}
}

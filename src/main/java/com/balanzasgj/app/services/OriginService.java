package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Origin;
import com.balanzasgj.app.persistence.OriginDao;
import com.balanzasgj.app.persistence.impl.jdbc.OriginDaoImpl;

public class OriginService {
	final static Logger logger = Logger.getLogger(OriginService.class);
	
	private OriginDao originDao;
	
	public OriginService() {
		try {
			this.originDao = new OriginDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Origin> findAll(){
		try {
			return originDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Origin>();
	}
	
	public Origin findById(long idTara) {
		try {
			return originDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Origin entity) {
		return originDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			originDao.deleteById(id);
		} catch (SQLException e) {
			logger.error(e);
		}	
	}
}

package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Transport;
import com.balanzasgj.app.persistence.TransportDao;
import com.balanzasgj.app.persistence.impl.jdbc.TransportDaoImpl;

public class TransportService {
	final static Logger logger = Logger.getLogger(TransportService.class);
	
	private TransportDao TransportDao;
	
	public TransportService() {
		try {
			this.TransportDao = new TransportDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Transport> findAll(){
		try {
			return TransportDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Transport>();
	}
	
	public Transport findById(long idTara) {
		try {
			return TransportDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Transport entity) {
		return TransportDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			TransportDao.deleteById(id);
		} catch (SQLException e) {
			logger.error(e);
		}	
	}
}

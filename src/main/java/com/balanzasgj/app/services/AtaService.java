package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Ata;
import com.balanzasgj.app.persistence.AtaDao;
import com.balanzasgj.app.persistence.impl.jdbc.AtaDaoImpl;

public class AtaService {
	final static Logger logger = Logger.getLogger(AtaService.class);
	
	private AtaDao ataDao;
	
	public AtaService() {
		try {
			this.ataDao = new AtaDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Ata> findAll(){
		try {
			return ataDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Ata>();
	}
	
	public Ata findById(long idTara) {
		try {
			return ataDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Ata entity) {
		return ataDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			ataDao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}

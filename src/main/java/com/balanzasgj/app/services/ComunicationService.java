package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Comunication;
import com.balanzasgj.app.persistence.ComunicationDao;
import com.balanzasgj.app.persistence.impl.jdbc.ComunicationDaoImpl;

public class ComunicationService {
	final static Logger logger = Logger.getLogger(ComunicationService.class);
	
	private ComunicationDao comunicationDao;
	
	public ComunicationService() {
		try {
			this.comunicationDao = new ComunicationDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Comunication> findAll(){
		try {
			return comunicationDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Comunication>();
	}
	
	public Comunication findById(long idTara) {
		try {
			return comunicationDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Comunication entity) {
		return comunicationDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			comunicationDao.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

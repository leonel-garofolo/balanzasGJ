package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Patent;
import com.balanzasgj.app.persistence.PatentDao;
import com.balanzasgj.app.persistence.impl.jdbc.PatentDaoImpl;

public class PatentService {
	final static Logger logger = Logger.getLogger(PatentService.class);
	
	private PatentDao patentDao;
	
	public PatentService() {
		try {
			this.patentDao = new PatentDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Patent> findAll(){
		try {
			return patentDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Patent>();
	}
	
	public Patent findById(String patent) {
		try {
			return patentDao.queryForId(patent);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public void save(Patent entity) {
		patentDao.save(entity);
	}

	public void deleteById(String patent) {
		try {
			patentDao.deleteById(patent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

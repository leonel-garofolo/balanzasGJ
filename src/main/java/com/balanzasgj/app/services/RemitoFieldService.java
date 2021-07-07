package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.RemitoField;
import com.balanzasgj.app.persistence.RemitoFieldDao;
import com.balanzasgj.app.persistence.impl.jdbc.RemitoFieldDaoImpl;

public class RemitoFieldService {
	final static Logger logger = Logger.getLogger(RemitoFieldService.class);
	
	private RemitoFieldDao remitoFieldDao;
	
	public RemitoFieldService() {
		try {
			this.remitoFieldDao = new RemitoFieldDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<RemitoField> findAll(){
		try {
			return remitoFieldDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<RemitoField>();
	}
	
	public RemitoField findById(long idTara) {
		try {
			return remitoFieldDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(RemitoField entity) {
		return remitoFieldDao.save(entity);
	}
	
	public void deleteAll() {		
		try {
			List<RemitoField> fields= remitoFieldDao.queryForAll();
			for (RemitoField remitoField : fields) {
				remitoFieldDao.deleteById(remitoField.getId());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public void deleteById(Long id) {
		try {
			remitoFieldDao.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

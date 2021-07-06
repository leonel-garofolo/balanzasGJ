package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Indicator;
import com.balanzasgj.app.persistence.IndicatorDao;
import com.balanzasgj.app.persistence.impl.jdbc.IndicatorDaoImpl;

public class IndicatorService {
	final static Logger logger = Logger.getLogger(IndicatorService.class);
	
	private IndicatorDao indicatorDao;
	
	public IndicatorService() {
		try {
			this.indicatorDao = new IndicatorDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Indicator> findAll(){
		try {
			return indicatorDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Indicator>();
	}
	
	public Indicator findById(long idTara) {
		try {
			return indicatorDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Indicator entity) {
		return indicatorDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			indicatorDao.deleteById(id);
		} catch (SQLException e) {
			logger.error(e);
		}	
	}

	public boolean existName(String nombre) {
		try {
			List<Indicator> indicators = indicatorDao.queryForEq("nombre", nombre);
			return indicators.isEmpty();
		} catch (SQLException e) {
			logger.error(e);
		}
		return false;
	}
}

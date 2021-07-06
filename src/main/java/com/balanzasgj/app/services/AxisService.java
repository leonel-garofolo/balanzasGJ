package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Axis;
import com.balanzasgj.app.persistence.AxisDao;
import com.balanzasgj.app.persistence.impl.jdbc.AxisDaoImpl;

public class AxisService {
	final static Logger logger = Logger.getLogger(AxisService.class);
	
	private AxisDao axisDao;
	
	public AxisService() {
		try {
			this.axisDao = new AxisDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Axis> findAll(){
		try {
			return axisDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Axis>();
	}
	
	public Axis findById(long id) {
		try {
			return axisDao.queryForId(id);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Axis entity) {
		return axisDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			axisDao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public List<Axis> findAllByTare(Long idtaras) {
		try {
			return axisDao.queryForEq("idTaras", idtaras);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
}

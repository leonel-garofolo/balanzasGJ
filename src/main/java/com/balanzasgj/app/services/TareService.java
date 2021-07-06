package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.persistence.TareDao;
import com.balanzasgj.app.persistence.impl.jdbc.TareDaoImpl;

public class TareService {
	final static Logger logger = Logger.getLogger(TareService.class);
	
	public static enum ReportFilter {
		B_TODO("TODO"),
		B_NUMERO("Numero de Transaccion"),
		B_PATENTE("Patente"),
		B_PRODUCTO("Producto"),
		B_CLIENTE("Cliente"),
		B_TRANSPORTE("Transporte"),
		B_PROCEDENCIA("Procedencia");
		
		public final String label;

	    private ReportFilter(String label) {
	        this.label = label;
	    }
	}

	
	private TareDao tareDao;
	
	public TareService() {
		try {
			this.tareDao = new TareDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Tare> findAll(){
		try {
			return tareDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Tare>();
	}
	
	public Tare findById(long idTara) {
		try {
			return tareDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Tare entity) {
		return tareDao.save(entity);
	}

	public List<Tare> findByField(String field, String data, boolean salidasPendientes) {		
		return tareDao.findByField(field, data, salidasPendientes);
	}
	
	public List<Tare> findByFieldInformes(String field, String data, Date fechaDesde, Date fechaHasta){
		return tareDao.findByFieldInformes(field, data, fechaDesde, fechaHasta);
	}

	public boolean checkPending(String patente) {		
		return tareDao.checkPending(patente) > 0;
	}
}

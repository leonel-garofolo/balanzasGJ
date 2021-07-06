package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.ImportAndExport;
import com.balanzasgj.app.persistence.ImportAndExportDao;
import com.balanzasgj.app.persistence.impl.jdbc.ImportAndExportDaoImpl;

public class ImportAndExportService {
	final static Logger logger = Logger.getLogger(ImportAndExportService.class);
	
	private ImportAndExportDao ImportAndExportDao;
	
	public ImportAndExportService() {
		try {
			this.ImportAndExportDao = new ImportAndExportDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<ImportAndExport> findAll(){
		try {
			return ImportAndExportDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<ImportAndExport>();
	}
	
	public ImportAndExport findById(long id) {
		try {
			return ImportAndExportDao.queryForId(id);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(ImportAndExport entity) {
		return ImportAndExportDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			ImportAndExportDao.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

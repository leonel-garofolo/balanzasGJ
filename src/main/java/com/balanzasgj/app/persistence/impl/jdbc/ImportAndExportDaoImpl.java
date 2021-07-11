package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.ImportAndExport;
import com.balanzasgj.app.persistence.ImportAndExportDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class ImportAndExportDaoImpl extends GenericJdbcDAO<ImportAndExport, Long> implements ImportAndExportDao{
	final static Logger logger = Logger.getLogger(ImportAndExportDaoImpl.class);

	public ImportAndExportDaoImpl() throws SQLException{
		super(ImportAndExport.class);
	}	

	@Override
	public Long save(ImportAndExport entity) {
		try {
			if(entity.getCodigo() == null)
				create(entity);
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getCodigo();
	}
}

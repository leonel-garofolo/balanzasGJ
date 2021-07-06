package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Report;
import com.balanzasgj.app.persistence.ReportDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class ReportDaoImpl extends GenericJdbcDAO<Report, Long> implements ReportDao{
	final static Logger logger = Logger.getLogger(ReportDaoImpl.class);

	public ReportDaoImpl() throws SQLException{
		super(Report.class);
	}	

	@Override
	public Long save(Report entity) {
		try {
			if(entity.getId() == null)
				entity.setId((long)create(entity));				
			else
				update(entity);	
		} catch (SQLException e) {
			logger.error(e);
		}
							
		return entity.getId();
	}
}

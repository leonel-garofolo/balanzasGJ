package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.persistence.ProductDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class ProductDaoImpl extends GenericJdbcDAO<Product, Long> implements ProductDao{
	final static Logger logger = Logger.getLogger(ProductDaoImpl.class);

	public ProductDaoImpl() throws SQLException{
		super(Product.class);
	}	

	@Override
	public Long save(Product entity) {
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

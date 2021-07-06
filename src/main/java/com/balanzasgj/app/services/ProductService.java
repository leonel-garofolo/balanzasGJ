package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.persistence.ProductDao;
import com.balanzasgj.app.persistence.impl.jdbc.ProductDaoImpl;

public class ProductService {
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	private ProductDao productDao;
	
	public ProductService() {
		try {
			this.productDao = new ProductDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Product> findAll(){
		try {
			return productDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Product>();
	}
	
	public Product findById(long idTara) {
		try {
			return productDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Product entity) {
		return productDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			productDao.deleteById(id);
		} catch (SQLException e) {
			logger.error(e);
		}	
	}
}

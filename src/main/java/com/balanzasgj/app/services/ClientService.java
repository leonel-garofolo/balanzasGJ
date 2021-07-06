package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.Client;
import com.balanzasgj.app.persistence.ClientDao;
import com.balanzasgj.app.persistence.impl.jdbc.ClientDaoImpl;

public class ClientService {
	final static Logger logger = Logger.getLogger(ClientService.class);
	
	private ClientDao clientDao;
	
	public ClientService() {
		try {
			this.clientDao = new ClientDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<Client> findAll(){
		try {
			return clientDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<Client>();
	}
	
	public Client findById(long idTara) {
		try {
			return clientDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(Client entity) {
		return clientDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			clientDao.deleteById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

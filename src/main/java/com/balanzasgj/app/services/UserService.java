package com.balanzasgj.app.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.User;
import com.balanzasgj.app.persistence.UserDao;
import com.balanzasgj.app.persistence.impl.jdbc.UserDaoImpl;

public class UserService {
	final static Logger logger = Logger.getLogger(UserService.class);
	
	private UserDao userDao;
	
	public UserService() {
		try {
			this.userDao = new UserDaoImpl();
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	public List<User> findAll(){
		try {
			return userDao.queryForAll();
		} catch (SQLException e) {
			logger.error(e);			
		}
		return new ArrayList<User>();
	}
	
	public User findById(long idTara) {
		try {
			return userDao.queryForId(idTara);
		} catch (SQLException e) {
			logger.error(e);			
		}
		return null;
	}
	
	public Long save(User entity) {
		return userDao.save(entity);
	}

	public void deleteById(Long id) {
		try {
			userDao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public User loadForNombre(String text) {
		List<User> users = new ArrayList<User>();
		try {
			users = userDao.queryForEq("nombre", text);
			if(!users.isEmpty())
				return users.get(0);
		} catch (SQLException e) {
			logger.error(e);
		}		
		return null;
	}

	public List<User> loadForPerfil(long id) {
		try {
			return userDao.queryForEq("id_perfil", id);			
		} catch (SQLException e) {
			logger.error(e);
		}		
		return new ArrayList<User>();
	}
}

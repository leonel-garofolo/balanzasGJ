package com.balanzasgj.app.persistence.impl.jdbc;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.balanzasgj.app.model.User;
import com.balanzasgj.app.persistence.UserDao;
import com.balanzasgj.app.persistence.impl.jdbc.commons.GenericJdbcDAO;

public class UserDaoImpl extends GenericJdbcDAO<User, Long> implements UserDao{
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() throws SQLException{
		super(User.class);
	}	

	@Override
	public Long save(User entity) {
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

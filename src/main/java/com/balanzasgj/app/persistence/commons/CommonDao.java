package com.balanzasgj.app.persistence.commons;

import com.j256.ormlite.dao.Dao;

public interface CommonDao<T, S> extends Dao<T, S>{

	Long save(T entity);
}

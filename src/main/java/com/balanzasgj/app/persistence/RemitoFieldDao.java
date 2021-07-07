package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.RemitoField;
import com.j256.ormlite.dao.Dao;

public interface RemitoFieldDao extends Dao<RemitoField, Long> {
	Long save(RemitoField entity);
}

package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.Patent;
import com.j256.ormlite.dao.Dao;

public interface PatentDao extends Dao<Patent, String> {
	void save(Patent entity);
}

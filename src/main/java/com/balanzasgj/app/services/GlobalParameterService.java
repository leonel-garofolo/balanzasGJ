package com.balanzasgj.app.services;

import java.sql.Blob;
import java.sql.SQLException;

import com.balanzasgj.app.model.GlobalParameter;
import com.balanzasgj.app.persistence.GlobalParameterDao;
import com.balanzasgj.app.persistence.impl.jdbc.GlobalParameterDaoImpl;

public class GlobalParameterService {
    private GlobalParameterDao globalParameterDao;

    public GlobalParameterService(){
        try {
			this.globalParameterDao = new GlobalParameterDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public String get(final String param) {
        final GlobalParameter pg = this.globalParameterDao.getById(param);
        return pg == null ? "" : pg.getValue();
    }

    public Blob getBlob(final String param) {
    	final GlobalParameter pg = this.globalParameterDao.getById(param);
        return pg == null ? null : pg.getValueByte();
    }

    public void save(final String param, final String value){
        final GlobalParameter pg = new GlobalParameter();
        pg.setId(param);
        pg.setValue(value.trim());
        globalParameterDao.save(pg);
    }

    public void saveBlob(final String param, final Blob value){
        final GlobalParameter pg = new GlobalParameter();
        pg.setId(param);
        pg.setValue("");
        pg.setValueByte(value);
        globalParameterDao.save(pg);
    }
}

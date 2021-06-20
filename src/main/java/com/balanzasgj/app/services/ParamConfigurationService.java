package com.balanzasgj.app.services;

import com.balanzasgj.app.model.ParametrosGlobales;
import com.balanzasgj.app.persistence.ParametrosGlobalesPersistence;
import com.balanzasgj.app.persistence.impl.jdbc.ParametrosGlobalesPersistenceJdbc;

import java.sql.Blob;

public class ParamConfigurationService {
    private ParametrosGlobalesPersistence parametrosGlobalesPersistence;

    public ParamConfigurationService(){
        this.parametrosGlobalesPersistence = new ParametrosGlobalesPersistenceJdbc();
    }

    public String get(final String param) {
        final ParametrosGlobales pg = new ParametrosGlobales();
        pg.setId(param);
        parametrosGlobalesPersistence.load(pg);
        return pg.getValue() == null ? "" : pg.getValue();
    }

    public Blob getBlob(final String param) {
        final ParametrosGlobales pg = new ParametrosGlobales();
        pg.setId(param);
        parametrosGlobalesPersistence.load(pg);
        return pg.getValueByte();
    }

    public void save(final String param, final String value){
        final ParametrosGlobales pg = new ParametrosGlobales();
        pg.setId(param);
        pg.setValue(value.trim());
        parametrosGlobalesPersistence.save(pg);
    }

    public void saveBlob(final String param, final Blob value){
        final ParametrosGlobales pg = new ParametrosGlobales();
        pg.setId(param);
        pg.setValue("");
        pg.setValueByte(value);
        parametrosGlobalesPersistence.save(pg);
    }
}

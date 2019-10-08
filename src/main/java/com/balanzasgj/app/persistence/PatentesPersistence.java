package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.Patentes;

public interface PatentesPersistence extends CommonPersistence<Patentes>{

	Patentes findById(String codigo);

	boolean deleteById(String codigo);

}

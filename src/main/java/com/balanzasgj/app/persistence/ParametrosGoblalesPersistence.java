package com.balanzasgj.app.persistence;

import java.util.List;

import com.balanzasgj.app.basic.bean.ParametrosGoblales;

public interface ParametrosGoblalesPersistence {
	List<ParametrosGoblales> findAll();
	ParametrosGoblales findById( String codigo );
	boolean load( ParametrosGoblales entidad );
	ParametrosGoblales save(ParametrosGoblales clientes);
	boolean deleteById( String codigo );
	long countAll();
}

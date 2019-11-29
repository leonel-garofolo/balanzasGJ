package com.balanzasgj.app.persistence;

import java.util.List;

import com.balanzasgj.app.model.ParametrosGlobales;

public interface ParametrosGlobalesPersistence {
	List<ParametrosGlobales> findAll();
	ParametrosGlobales findById( String codigo );
	boolean load( ParametrosGlobales entidad );
	ParametrosGlobales save(ParametrosGlobales clientes);
	boolean deleteById( String codigo );
	long countAll();
}

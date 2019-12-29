package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.Indicadores;

public interface IndicadoresPersistence extends CommonPersistence<Indicadores>{
	public boolean existName(String name);		
}	

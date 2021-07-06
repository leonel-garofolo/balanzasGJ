package com.balanzasgj.app.persistence;

import java.util.Date;
import java.util.List;

import com.balanzasgj.app.model.Tare;
import com.balanzasgj.app.persistence.commons.CommonDao;

public interface TareDao extends CommonDao<Tare, Long>{

	long checkPending(String patente);
	
	List<Tare> findByField(String field, String data, boolean salidasPendientes);
	
	List<Tare> findByFieldInformes(String field, String data, Date fechaDesde, Date fechaHasta);

}

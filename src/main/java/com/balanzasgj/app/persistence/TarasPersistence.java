package com.balanzasgj.app.persistence;

import java.util.Date;
import java.util.List;

import com.balanzasgj.app.model.Taras;

public interface TarasPersistence extends CommonPersistence<Taras>{
    List<Taras> findByField(String field, String data, boolean salidasPendientes);
    List<Taras> findByFieldInformes(String field, String data, Date fechaDesde, Date fechaHasta);
	boolean checkPending(String patente);
}

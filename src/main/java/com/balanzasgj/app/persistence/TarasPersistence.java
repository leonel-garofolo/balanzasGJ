package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.Taras;

import java.util.List;

public interface TarasPersistence extends CommonPersistence<Taras>{
    List<Taras> findByField(String field, String data, boolean salidasPendientes);
    List<Taras> findByFieldInformes(String field, String data);
}

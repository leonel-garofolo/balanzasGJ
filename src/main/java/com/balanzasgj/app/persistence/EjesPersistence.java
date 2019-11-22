package com.balanzasgj.app.persistence;

import java.util.List;

import com.balanzasgj.app.model.Ejes;

public interface EjesPersistence extends CommonPersistence<Ejes>{
	List<Ejes> findAll(Long idTara);

}

package com.balanzasgj.app.persistence;

import java.util.List;

public interface CommonPersistence<T> {
	List<T> findAll();
	T findById( Long codigo );
	boolean load( T entidad );
	T save(T clientes);
	boolean deleteById( Long codigo );
	long countAll();
}

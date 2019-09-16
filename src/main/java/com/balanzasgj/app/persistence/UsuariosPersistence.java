package com.balanzasgj.app.persistence;

import java.util.List;

import com.balanzasgj.app.model.Usuarios;

public interface UsuariosPersistence extends CommonPersistence<Usuarios>{
	
	List<Usuarios> loadForPerfil(long idPerfil);
}

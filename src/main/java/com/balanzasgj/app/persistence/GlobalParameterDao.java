package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.GlobalParameter;

public interface GlobalParameterDao {
	
	GlobalParameter getById(String parameter);
	
	void save(GlobalParameter globalParameter);

}

package com.balanzasgj.app.persistence;

import com.balanzasgj.app.model.Reports;

public interface ReportsPersistence extends CommonPersistence<Reports>{
	
	Reports fintByTaraId(Long taraId);
	
}

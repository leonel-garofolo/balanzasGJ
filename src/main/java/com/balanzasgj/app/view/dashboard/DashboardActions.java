package com.balanzasgj.app.view.dashboard;

import com.balanzasgj.app.view.DashboardView.PANEL;

public interface DashboardActions {
	
	void showCenter(PANEL panel);
	
	void showMain();
	
	void showSuccess(String msj);
	
	void showError(String msj);
}

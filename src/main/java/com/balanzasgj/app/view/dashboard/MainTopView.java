package com.balanzasgj.app.view.dashboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MainTopView extends HBox {
	
	public MainTopView() {
		initialize();
	}
	
	private void initialize() {		
		setWidth(Double.MAX_VALUE);
		setAlignment(Pos.TOP_CENTER);
		this.getChildren().add(new Button("a"));
		this.getChildren().add(new Button("b"));
		this.getChildren().add(new Button("c"));
	}
}

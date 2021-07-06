package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Patent;

import javafx.scene.control.TableCell;

public class PatenteTableCell<Taras> extends TableCell<Taras, Patent> {
	@Override
	protected void updateItem(Patent item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getPatente());
	}
}
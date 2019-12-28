package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Patentes;

import javafx.scene.control.TableCell;

public class PatenteTableCell<Taras> extends TableCell<Taras, Patentes> {
	@Override
	protected void updateItem(Patentes item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getPatente());
	}
}
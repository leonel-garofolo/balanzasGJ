package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Transportes;
import javafx.scene.control.TableCell;

public class TransportesTableCell<Taras> extends TableCell<Taras, Transportes> {
	@Override
	protected void updateItem(Transportes item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
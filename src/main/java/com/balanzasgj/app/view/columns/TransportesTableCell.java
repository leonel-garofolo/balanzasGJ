package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Transport;
import javafx.scene.control.TableCell;

public class TransportesTableCell<Taras> extends TableCell<Taras, Transport> {
	@Override
	protected void updateItem(Transport item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
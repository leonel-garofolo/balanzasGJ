package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Procedencias;
import javafx.scene.control.TableCell;

public class ProcedenciasTableCell<Taras> extends TableCell<Taras, Procedencias> {
	@Override
	protected void updateItem(Procedencias item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
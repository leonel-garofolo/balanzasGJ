package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Origin;
import javafx.scene.control.TableCell;

public class ProcedenciasTableCell<Taras> extends TableCell<Taras, Origin> {
	@Override
	protected void updateItem(Origin item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
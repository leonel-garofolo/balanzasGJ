package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.ImportadoresExportadores;

import javafx.scene.control.TableCell;

public class ImpExpTableCell<Taras> extends TableCell<Taras, ImportadoresExportadores> {
	@Override
	protected void updateItem(ImportadoresExportadores item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.ImportAndExport;

import javafx.scene.control.TableCell;

public class ImpExpTableCell<Taras> extends TableCell<Taras, ImportAndExport> {
	@Override
	protected void updateItem(ImportAndExport item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
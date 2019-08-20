package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Productos;
import javafx.scene.control.TableCell;

public class ProductosTableCell<Taras> extends TableCell<Taras, Productos> {
	@Override
	protected void updateItem(Productos item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Product;
import javafx.scene.control.TableCell;

public class ProductosTableCell<Taras> extends TableCell<Taras, Product> {
	@Override
	protected void updateItem(Product item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}
package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Client;
import com.balanzasgj.app.model.Origin;
import com.balanzasgj.app.model.Product;
import com.balanzasgj.app.model.Transport;
import javafx.scene.control.TableCell;

public class ClientesTableCell<Taras> extends TableCell<Taras, Client> {
	@Override
	protected void updateItem(Client item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}






package com.balanzasgj.app.view.columns;

import com.balanzasgj.app.model.Clientes;
import com.balanzasgj.app.model.Procedencias;
import com.balanzasgj.app.model.Productos;
import com.balanzasgj.app.model.Transportes;
import javafx.scene.control.TableCell;

public class ClientesTableCell<Taras> extends TableCell<Taras, Clientes> {
	@Override
	protected void updateItem(Clientes item, boolean empty) {
		super.updateItem(item, empty);
		setText(empty || item == null ? "" : item.getNombre());
	}
}






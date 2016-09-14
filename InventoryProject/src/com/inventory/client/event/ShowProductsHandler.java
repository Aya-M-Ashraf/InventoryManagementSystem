package com.inventory.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ShowProductsHandler extends EventHandler {
	public void onShowProducts(ShowProductsEvent showProductsEvent);
}

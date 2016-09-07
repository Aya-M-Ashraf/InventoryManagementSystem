package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ShowProductsEvent  extends GwtEvent<ShowProductsHandler>{

	public static Type<ShowProductsHandler> TYPE = new Type<ShowProductsHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowProductsHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowProductsHandler handler) {
		handler.onShowProducts(this);
		
	}

}

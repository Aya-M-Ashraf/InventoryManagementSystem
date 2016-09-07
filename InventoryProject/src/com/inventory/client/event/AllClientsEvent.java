package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AllClientsEvent extends GwtEvent<AllClientsHandler>{

	public static Type<AllClientsHandler> TYPE = new Type<AllClientsHandler>();
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AllClientsHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AllClientsHandler handler) {
		handler.onAllClients(this);
		
	}

}

package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class GetOrdersEvent extends GwtEvent<GetOrdersEventHandler> {
	public static Type<GetOrdersEventHandler> TYPE = new Type<GetOrdersEventHandler>();
	private final int id;

	public GetOrdersEvent(int id) {
		this.id = id;
	}

	@Override
	public Type<GetOrdersEventHandler> getAssociatedType() {
		return TYPE;
	}

	public int getId() {
		return id;
	}

	@Override
	protected void dispatch(GetOrdersEventHandler handler) {
		handler.onGetOrders(this);
	}
}

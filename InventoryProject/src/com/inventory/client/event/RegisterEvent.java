package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.inventory.client.event.RegisterHandler;

public class RegisterEvent extends GwtEvent<RegisterHandler> {

	public static Type<RegisterHandler> TYPE = new Type<RegisterHandler>();

	@Override
	public Type<RegisterHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RegisterHandler handler) {
		handler.onButtonClicked(this);
	}

}

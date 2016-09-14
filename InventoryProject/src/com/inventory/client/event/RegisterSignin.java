package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class RegisterSignin  extends GwtEvent<RegisterSigninHandler> {

	public static Type<RegisterSigninHandler> TYPE = new Type<RegisterSigninHandler>();

	@Override
	public Type<RegisterSigninHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RegisterSigninHandler handler) {
		handler.onRegister(this);
	}

}

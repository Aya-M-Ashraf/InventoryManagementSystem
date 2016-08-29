package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ForgetPasswordEvent extends GwtEvent<ForgetPasswordHandler> {

	
	public static Type<ForgetPasswordHandler> TYPE = new Type<>();
	

	public ForgetPasswordEvent() {
		super();
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ForgetPasswordHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ForgetPasswordHandler handler) {
		handler.onForgetPassword(this);
	}

}

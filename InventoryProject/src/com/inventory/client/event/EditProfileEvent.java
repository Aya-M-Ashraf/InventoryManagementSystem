package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

public class EditProfileEvent extends GwtEvent<EditProfileHandler>{
	
	public static Type<EditProfileHandler> TYPE = new Type<EditProfileHandler>();

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<EditProfileHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditProfileHandler handler) {
		handler.onEditProfile(this);
	}
}

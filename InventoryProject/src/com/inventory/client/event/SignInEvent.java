package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.inventory.shared.dto.UserDTO;
//import com.test.entity.User;

public class SignInEvent extends GwtEvent<SignInEventHandler> {

	public static Type<SignInEventHandler> TYPE = new Type<SignInEventHandler>();
	
	private UserDTO user;
	
	public SignInEvent() {
		super();
	}

	public SignInEvent(UserDTO user){
		super();
		this.user = user;
	}
	
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<SignInEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SignInEventHandler handler) {
		handler.onSignIn(this);
	}

}

package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.inventory.shared.dto.UserDTO;

public class AllClientsEvent extends GwtEvent<AllClientsHandler>{

	public static Type<AllClientsHandler> TYPE = new Type<AllClientsHandler>();
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
	
	public AllClientsEvent(UserDTO user) {
		super();
		this.user = user;
	}

	public AllClientsEvent() {
		super();
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AllClientsHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AllClientsHandler handler) {
		handler.onAllClients(this);
		
	}

}

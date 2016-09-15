package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.inventory.shared.dto.UserDTO;

public class GetOrdersEvent extends GwtEvent<GetOrdersEventHandler> {
	public static Type<GetOrdersEventHandler> TYPE = new Type<GetOrdersEventHandler>();
	private final int id;
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

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

	public GetOrdersEvent(int id, UserDTO user) {
		super();
		this.id = id;
		this.user = user;
	}

	
}

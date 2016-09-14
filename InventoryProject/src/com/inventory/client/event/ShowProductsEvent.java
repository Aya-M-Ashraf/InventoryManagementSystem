package com.inventory.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.inventory.shared.dto.UserDTO;

public class ShowProductsEvent extends GwtEvent<ShowProductsHandler> {

	public static Type<ShowProductsHandler> TYPE = new Type<ShowProductsHandler>();
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public ShowProductsEvent() {
		super();
	}

	public ShowProductsEvent(UserDTO user) {
		super();
		this.user = user;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ShowProductsHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ShowProductsHandler handler) {
		handler.onShowProducts(this);

	}

}

package com.inventory.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.inventory.client.GreetingServiceAsync;
import com.inventory.client.event.LogOutEvent;
//import com.test.entity.User;
import com.inventory.shared.dto.UserDTO;

public class WelcomePresenter implements Presenter {

	public interface Display {
		HasValue<String> getUsername();

		HasValue<String> getId();

		HasValue<String> getEmail();

		HasValue<String> getAddress();

		HasClickHandlers getLogOutBtn();

		Widget asWidget();
	}

	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private final Display display;
	private final UserDTO user;

	public WelcomePresenter(HandlerManager eventBus, GreetingServiceAsync rpcService, Display display, UserDTO user) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		this.display = display;
		this.user = user;
		setUserDisplay();
		bind();
	}

	public void bind() {
		display.getLogOutBtn().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogOutEvent());
			}
		});
	}

	public void setUserDisplay() {
		display.getUsername().setValue(user.getUsername());
		display.getId().setValue(Integer.toString(user.getId()));
		display.getEmail().setValue(user.getEmail());
		display.getAddress().setValue(user.getAddress());
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
	}

}
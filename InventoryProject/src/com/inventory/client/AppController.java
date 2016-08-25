package com.inventory.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inventory.client.presenter.ManagerHomePresenter;
import com.inventory.client.presenter.Presenter;
import com.inventory.client.view.ManagerHome;


public class AppController implements Presenter, ValueChangeHandler<String> {
	
	private final HandlerManager eventBus;
	private final GreetingServiceAsync rpcService;
	private HasWidgets container;

	public AppController(GreetingServiceAsync rpcService, HandlerManager eventBus) {
		System.out.println("***************** in the constructor of the AppController");

		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
	}
	
	public void go(final HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("initialState");
			Presenter presenter = new ManagerHomePresenter(eventBus, rpcService, new ManagerHome());
			presenter.go(container);
			
		} else {
			/*History.fireCurrentHistoryState();*/
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			if (token.equals("button")) {
				
			}

		}
	}

}

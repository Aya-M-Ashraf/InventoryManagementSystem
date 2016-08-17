package com.inventory.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class InventoryProject implements EntryPoint {

	@Override
	public void onModuleLoad() {
		System.out.println("***************** in the on moduleLoad()");
		GreetingServiceAsync rpcService = GWT.create(GreetingService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(rpcService, eventBus);
		appViewer.go(RootPanel.get());

	}

}

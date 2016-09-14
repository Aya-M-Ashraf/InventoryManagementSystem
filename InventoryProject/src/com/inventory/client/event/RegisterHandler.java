package com.inventory.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.inventory.client.event.RegisterEvent;

public interface RegisterHandler extends EventHandler {
	void onButtonClicked(RegisterEvent buttonClickedEvent);
	
}

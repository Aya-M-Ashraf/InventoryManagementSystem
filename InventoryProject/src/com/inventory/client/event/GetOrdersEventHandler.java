package com.inventory.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface GetOrdersEventHandler extends EventHandler {
  void onGetOrders(GetOrdersEvent event);
}

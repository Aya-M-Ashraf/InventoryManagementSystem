package com.inventory.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.test.entity.Product;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getAllProducts(AsyncCallback<List<Product>> callback);

}

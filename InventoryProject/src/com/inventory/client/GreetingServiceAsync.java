package com.inventory.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inventory.shared.dto.UserDTO;
import com.test.entity.Product;
//import com.test.entity.User;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void signIn(UserDTO user, AsyncCallback<UserDTO> callback);
	void forgetPassword(String emailAddress, AsyncCallback<Void> callback);
	void getAllProducts(AsyncCallback<List<Product>> callback)throws IllegalArgumentException;


}

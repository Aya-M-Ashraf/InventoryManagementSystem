package com.inventory.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.test.entity.Product;
import com.inventory.shared.dto.UserDTO;
//import com.test.entity.User;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<Product> getAllProducts() throws IllegalArgumentException;
	UserDTO signIn(UserDTO user) throws IllegalArgumentException;
	void forgetPassword(String emailAddress) throws IllegalArgumentException;
}

package com.inventory.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inventory.shared.dto.UserDTO;
import com.inventory.shared.dto.ProductDTO;
import com.test.entity.Product;
//import com.test.entity.User;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	void signIn(UserDTO user, AsyncCallback<UserDTO> callback);
	void forgetPassword(String emailAddress, AsyncCallback<Void> callback);
	void getAllProducts(AsyncCallback<List<ProductDTO>> callback)throws IllegalArgumentException;
	void saveEditedProducts(ArrayList<ProductDTO> gridList, HashSet<Integer> changedIds, AsyncCallback<Void> callback);
	void deleteProduct(ProductDTO product, AsyncCallback<Void> callback);


}

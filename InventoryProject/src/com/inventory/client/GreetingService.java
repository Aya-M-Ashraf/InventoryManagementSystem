package com.inventory.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inventory.shared.dto.ProductDTO;
import com.test.entity.Product;
import com.inventory.shared.dto.UserDTO;
//import com.test.entity.User;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<ProductDTO> getAllProducts() throws IllegalArgumentException;
	void saveEditedProducts(ArrayList<ProductDTO> gridList, HashSet<Integer> changedIds )throws IllegalArgumentException;
	void deleteProduct(ProductDTO product)throws IllegalArgumentException;
	UserDTO signIn(UserDTO user) throws IllegalArgumentException;
	void forgetPassword(String emailAddress) throws IllegalArgumentException;
}

package com.inventory.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inventory.shared.dto.UserDTO;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.OrderDTO;
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
	void updateProfile(String email, String oldPasswd, String newPasswd, AsyncCallback<Void> callback);
	void addProduct(ProductDTO newProduct, InventoryDTO inventoryDTO, AsyncCallback<ProductDTO> asyncCallback);
	void getAllActiveProducts(AsyncCallback<List<ProductDTO>> asyncCallback);
	void makeOrder(OrderDTO order, UserDTO user, AsyncCallback<Void> asyncCallback);
	void getAllUsers(AsyncCallback<List<UserDTO>> callback);
	void getAllOrdersForXClient(int id, AsyncCallback<List<OrderDTO>> callback);
	void getUserName(int id, AsyncCallback<String> callback);
	void addProductByXML(String fileName, AsyncCallback<ArrayList<ProductDTO>> callback);
	void getAllOrdersForManager(AsyncCallback<ArrayList<OrderDTO>> callback);
	void changeOrderStatus(OrderDTO orderDTO, String value, AsyncCallback<Void> callback);
	void addUser(UserDTO userDto, AsyncCallback<Void> callback);
}

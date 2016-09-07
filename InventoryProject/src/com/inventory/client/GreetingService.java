package com.inventory.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.OrderDTO;
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
	void updateProfile(String email, String oldPasswd, String newPasswd) throws IllegalArgumentException;
	ProductDTO addProduct(ProductDTO newProduct, InventoryDTO inventoryDTO);
	List<ProductDTO> getAllActiveProducts();
	void makeOrder(OrderDTO order, UserDTO user);
	List<UserDTO> getAllUsers();
	List<OrderDTO> getAllOrdersForXClient(int id) throws IllegalArgumentException;
	public String getUserName(int id) throws IllegalArgumentException;
	ArrayList<ProductDTO> addProductByXML(String fileName)throws IllegalArgumentException;
	ArrayList<OrderDTO> getAllOrdersForManager() throws IllegalArgumentException;
	void changeOrderStatus(OrderDTO orderDTO, String value)throws IllegalArgumentException;
	public void addUser(UserDTO userDto) throws IllegalArgumentException;
}

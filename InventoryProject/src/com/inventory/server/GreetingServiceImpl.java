package com.inventory.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inventory.client.GreetingService;
import com.inventory.shared.dto.InventoryDTO;
import com.inventory.shared.dto.ProductDTO;
import com.inventory.shared.util.EntityMapper;
import com.test.controller.ProductControllerLocal;
import com.test.controller.UserControllerLocal;
import com.test.entity.Product;
import com.inventory.shared.dto.UserDTO;
import com.inventory.shared.util.EntityMapper;
import com.test.entity.User;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@EJB
	public ProductControllerLocal productController;

	@EJB
	private UserControllerLocal userController;

	private EntityMapper mapper = new EntityMapper();

	@Override
	public List<ProductDTO> getAllProducts() throws IllegalArgumentException {

		List<Product> products = productController.getAllProducts();
		List<ProductDTO> productsDTOs = new ArrayList<>();

		for (Product product : products) {
			productsDTOs.add(mapper.mapProductToProductDto(product));
		}

		return productsDTOs;
	}

	@Override
	public void saveEditedProducts(ArrayList<ProductDTO> gridList, HashSet<Integer> changedIds) throws IllegalArgumentException {
		ArrayList<Object> gridInfo = new ArrayList<>();
		List<Product> products = new ArrayList<>();

		for (ProductDTO product : gridList) {
			products.add(mapper.mapProductDtoToProduct(product));
		}
		gridInfo.add(products);
		gridInfo.add(changedIds);
		productController.saveEditedProducts(gridInfo);

	}

	@Override
	public void deleteProduct(ProductDTO product) throws IllegalArgumentException {
		Product deletedProduct = mapper.mapProductDtoToProduct(product);
		productController.deleteProduct(deletedProduct);
		
	}

	@Override
	public UserDTO signIn(UserDTO sentUserDto) throws IllegalArgumentException {
		System.out.println("***************** IN RPC *****************");
		if (sentUserDto != null) {
			User tinyUser = new User();
			tinyUser.setEmail(sentUserDto.getEmail());
			tinyUser.setPassword(sentUserDto.getPassword());
			User normalUser = userController.signIn(tinyUser);
			if (normalUser != null && normalUser.getPassword().equals(tinyUser.getPassword())) {
				UserDTO fullUserDto = mapper.mapUserToUserDto(normalUser);
				return fullUserDto;
			} else
				return null;
		} else
			return null;
	}

	@Override
	public void forgetPassword(String emailAddress) throws IllegalArgumentException {
		if(emailAddress !=null){
			userController.forgetPasswordController(emailAddress);
		}
	}

	@Override
	public void updateProfile(String email, String oldPasswd, String newPasswd) throws IllegalArgumentException {
		userController.updateProfileController(email, oldPasswd, newPasswd);
	}

	@Override
	public ProductDTO addProduct(ProductDTO product, InventoryDTO inventoryDTO) {
		Product addedProduct = productController.addProduct(mapper.mapProductDtoToProduct(product), mapper.mapInventoryDtoToInventory(inventoryDTO));
		return mapper.mapProductToProductDto(addedProduct);
	}

}

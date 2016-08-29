package com.inventory.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inventory.client.GreetingService;
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
	public List<Product> getAllProducts() throws IllegalArgumentException {

	
		List<Product> products = productController.getAllProducts(); 
		return products;
	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		
		return null;
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

}

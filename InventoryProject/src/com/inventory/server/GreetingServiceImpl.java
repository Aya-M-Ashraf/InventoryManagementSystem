package com.inventory.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inventory.client.GreetingService;
import com.test.controller.ProductControllerLocal;
import com.test.entity.Product;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@EJB
	public ProductControllerLocal productController;

	@Override
	public List<Product> getAllProducts() throws IllegalArgumentException {

		
		List<Product> products = productController.getAllProducts(); 
		return products;
	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		
		return null;
	}

}

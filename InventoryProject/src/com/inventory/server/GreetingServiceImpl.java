package com.inventory.server;

import java.util.List;

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

		return (List<Product>)productController.getAllProducts();
	}

	@Override
	public String greetServer(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}

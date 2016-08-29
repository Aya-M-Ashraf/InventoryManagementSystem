package com.inventory.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inventory.client.GreetingService;
import com.inventory.shared.dto.ProductDTO;
import com.inventory.shared.util.EntityMapper;
import com.test.controller.ProductControllerLocal;
import com.test.entity.Product;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@EJB
	public ProductControllerLocal productController;

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

}

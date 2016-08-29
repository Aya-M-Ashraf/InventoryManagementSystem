package com.inventory.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inventory.shared.dto.ProductDTO;
import com.test.entity.Product;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<ProductDTO> getAllProducts() throws IllegalArgumentException;
	void saveEditedProducts(ArrayList<ProductDTO> gridList, ArrayList<Integer> changedIds )throws IllegalArgumentException;
}

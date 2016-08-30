package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.test.entity.Product;

@Local
public interface ProductControllerLocal {
	
	public List<Product> getAllProducts();
	public void saveEditedProducts(ArrayList<Object> gridInfo);
	public void deleteProduct(Product deletedProduct);
}

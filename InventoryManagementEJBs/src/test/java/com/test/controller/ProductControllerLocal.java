package com.test.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.test.entity.Inventory;
import com.test.entity.Product;
import com.test.util.RealNameDTO;

@Local
public interface ProductControllerLocal {
	
	public List<Product> getAllProducts();
	public void saveEditedProducts(ArrayList<Object> gridInfo);
	public void deleteProduct(Product deletedProduct);
	public Product addProduct(Product product, Inventory inventory);
	public ArrayList<Product> addProductByXml(File file);
	public RealNameDTO getRealFilePath(String fakePath);
}

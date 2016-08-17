package com.test.controller;

import java.util.List;

import javax.ejb.Local;

import com.test.daos.ProductDao;
import com.test.entity.Product;

@Local
public interface ProductControllerLocal {
	
	public List<Product> getAllProducts();
}

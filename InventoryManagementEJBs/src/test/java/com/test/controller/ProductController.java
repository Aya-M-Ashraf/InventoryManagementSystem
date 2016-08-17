package com.test.controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.test.daos.ProductDao;
import com.test.entity.Product;

/**
 * Session Bean implementation class ProductController
 */
@Stateless
@LocalBean
public class ProductController implements ProductControllerLocal {

	private ProductDao productDao = new ProductDao();

    public ProductController() {
    }

	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

}

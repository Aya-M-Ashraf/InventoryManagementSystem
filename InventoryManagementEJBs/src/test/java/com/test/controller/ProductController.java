package com.test.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		System.out.println("--------- inside the controller");
		Logger logger = Logger.getLogger(this.getClass().getSimpleName());
		logger.log(Level.SEVERE, "--------- inside the controller");
		return productDao.findAll();
	}

}

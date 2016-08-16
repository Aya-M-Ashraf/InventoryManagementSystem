package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.test.daos.ProductDao;

/**
 * Session Bean implementation class ProductController
 */
@Stateless
@LocalBean
public class ProductController implements ProductControllerLocal {

	
	private ProductDao productDao = new ProductDao();
	
	
    /**
	 * @return the productDao
	 */
	@Override
	public ProductDao getProductDao() {
		return productDao;
	}


	/**
	 * @param productDao the productDao to set
	 */
	@Override
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}


	/**
     * Default constructor. 
     */
    public ProductController() {
        // TODO Auto-generated constructor stub
    }

}

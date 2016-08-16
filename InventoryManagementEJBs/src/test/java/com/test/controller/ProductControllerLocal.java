package com.test.controller;

import javax.ejb.Local;

import com.test.daos.ProductDao;

@Local
public interface ProductControllerLocal {
	public ProductDao getProductDao();
	public void setProductDao(ProductDao productDao);
}

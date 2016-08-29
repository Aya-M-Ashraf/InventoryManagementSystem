package com.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.ProductDao;
import com.test.entity.Product;

/**
 * Session Bean implementation class ProductController
 */
@Stateless
@LocalBean
public class ProductController implements ProductControllerLocal {
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private ProductDao productDao = new ProductDao();

	public ProductController() {
	}

	@Override
	public List<Product> getAllProducts() {
		productDao.setEntityManager(em);
		return productDao.findAll();
	}

	@Override
	public void saveEditedProducts(ArrayList<Object> gridInfo) {
		productDao.setEntityManager(em);
		ArrayList<Product> allProducts = (ArrayList<Product>) gridInfo.get(0);
		ArrayList<Integer> editedInedexes = (ArrayList<Integer>) gridInfo.get(1);
		System.out.println("-------------" + editedInedexes.size());
		for (Integer index : editedInedexes) {
			try {
				System.out.println("--------- inside the Controller in SaveEditedProducts()" + allProducts.get(index));
				productDao.update(allProducts.get(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

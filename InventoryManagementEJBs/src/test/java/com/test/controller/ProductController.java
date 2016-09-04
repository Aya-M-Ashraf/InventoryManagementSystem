package com.test.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.ProductDao;
import com.test.entity.Inventory;
import com.test.entity.Product;

@Stateless
@LocalBean
public class ProductController implements ProductControllerLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private ProductDao productDao = new ProductDao();

	public ProductController() {
	}

	@Override
	public List<Product> getAllProducts() { // and set Expiration Alarm if found
		productDao.setEntityManager(em);

		return productDao.findAll();
	}

	@Override
	public void saveEditedProducts(ArrayList<Object> gridInfo) {
		productDao.setEntityManager(em);
		ArrayList<Product> allProducts = (ArrayList<Product>) gridInfo.get(0);
		HashSet<Integer> editedInedexes = (HashSet<Integer>) gridInfo.get(1);
		System.out.println("-------------" + editedInedexes.size());
		for (Integer index : editedInedexes) {
			try {
				productDao.update(allProducts.get(index));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteProduct(Product deletedProduct) {
		productDao.setEntityManager(em);
		try {
			productDao.makeTransient(deletedProduct);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public Product addProduct(Product product, Inventory inventory) {
		productDao.setEntityManager(em);
		try {
			System.out.println("in product controller addproduct");
			inventory.setProduct(product);
			product.setInventory(inventory);			
			Product persistedProduct = productDao.findById(productDao.makePersistent(product).getId());
			System.out.println("---- id returned in product controller after adding "+persistedProduct.getId());
			return persistedProduct;
		} catch (Exception e) {
			System.out.println("***************** exceptioaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaan");
			e.printStackTrace();
			return null;
		}
	}

}

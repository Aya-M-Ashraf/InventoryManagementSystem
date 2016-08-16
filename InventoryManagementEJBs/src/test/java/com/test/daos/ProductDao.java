package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.Product;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ProductDao
 */
@Stateless
@LocalBean
public class ProductDao extends GenericDao<Product, Integer> implements ProductDaoLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	public ProductDao() {
		super(Product.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		if (em == null)
			System.out.println("EM is nulllllll");

		return em;

	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}

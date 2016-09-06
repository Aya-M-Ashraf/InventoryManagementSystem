package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.Product;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Session Bean implementation class ProductDao
 */

public class ProductDao extends GenericDao<Product, Integer> {

	private EntityManager entityManager;

	public ProductDao() {
		super(Product.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllActiveProducts() {
		byte b = 1;
		return (List<Product>) entityManager.createQuery("FROM Product p WHERE p.status = :pStatus")
				.setParameter("pStatus", b).getResultList();
	}

}

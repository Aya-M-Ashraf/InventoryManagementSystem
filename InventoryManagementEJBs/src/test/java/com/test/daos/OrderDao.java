package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OrderDao
 */

public class OrderDao extends GenericDao<Order, Integer> {

	private EntityManager entityManager;

	public OrderDao() {
		super(Order.class);
	}

	@Override
	protected EntityManager getEntityManager() {

		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

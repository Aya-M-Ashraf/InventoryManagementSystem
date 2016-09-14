package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.Order;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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


	public List<Order> getAllOrderforXClientDao(int id) {
		
		System.out.println("In Order Method Dao");
		TypedQuery<Order> clientQuery = getEntityManager().createNamedQuery("Order.findByUserId", Order.class)
				.setParameter("id", id);
		List<Order> orders = clientQuery.getResultList();
		return orders;
	}

}

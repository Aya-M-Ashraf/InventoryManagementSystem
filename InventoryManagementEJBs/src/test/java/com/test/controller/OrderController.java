package com.test.controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.test.daos.OrderDao;
import com.test.entity.Order;

/**
 * Session Bean implementation class OrderController
 */
@Stateless
@LocalBean
public class OrderController implements OrderControllerLocal {
	
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private OrderDao orderDao = new OrderDao();

	/**
	 * @return the orderDao
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}

	/**
	 * @param orderDao
	 *            the orderDao to set
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * Default constructor.
	 */
	public OrderController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Order> getAllOrderforXClient(int id) {
		TypedQuery<Order> clientQuery = em.createNamedQuery("Order.findByUserId", Order.class)
				.setParameter("id", id);
		List<Order> orders = clientQuery.getResultList();
		return orders;
	}

}

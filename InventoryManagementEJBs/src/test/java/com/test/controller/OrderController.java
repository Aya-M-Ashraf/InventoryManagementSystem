package com.test.controller;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.test.daos.OrderDao;
import com.test.daos.OrderStatusDao;
import com.test.entity.Order;
import com.test.entity.OrderStatus;
import com.test.entity.Order;

/**
 * Session Bean implementation class OrderController
 */
@Stateless
@LocalBean
public class OrderController implements OrderControllerLocal {
	
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager entityManager;

	private OrderDao orderDao = new OrderDao();  //before use it setEntitymanager first
	private OrderStatusDao orderStatusDao = new OrderStatusDao(); //before use it setEntitymanager first
	
	
	public OrderDao getOrderDao() {
		return orderDao;
	}


	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	@Override
	public List<Order> getAllOrderforXClient(int id) {
		TypedQuery<Order> clientQuery = entityManager.createNamedQuery("Order.findByUserId", Order.class)
				.setParameter("id", id);
		List<Order> orders = clientQuery.getResultList();
		return orders;
	}


	@Override
	public void addOrder(Order order) {
		orderDao.setEntityManager(entityManager);
		orderStatusDao.setEntityManager(entityManager);
	    order.setOrderStatus(orderStatusDao.findById(1));
		try {
			orderDao.makePersistent(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

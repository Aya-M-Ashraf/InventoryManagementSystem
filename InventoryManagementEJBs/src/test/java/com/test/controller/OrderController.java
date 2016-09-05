package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.OrderDao;
import com.test.daos.OrderStatusDao;
import com.test.entity.Order;
import com.test.entity.OrderStatus;

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
	
	
    /**
	 * @return the orderDao
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}


	/**
	 * @param orderDao the orderDao to set
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

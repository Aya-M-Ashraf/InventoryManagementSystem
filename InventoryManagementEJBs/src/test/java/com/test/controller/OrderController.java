package com.test.controller;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	private OrderDao orderDao = new OrderDao();  //before use it setEntitymanager first
	
	
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


}

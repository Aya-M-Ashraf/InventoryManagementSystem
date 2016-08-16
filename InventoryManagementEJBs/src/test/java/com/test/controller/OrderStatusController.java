package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.test.daos.OrderStatusDao;

/**
 * Session Bean implementation class OrderStatusController
 */
@Stateless
@LocalBean
public class OrderStatusController implements OrderStatusControllerLocal {

	private OrderStatusDao orderStatusDao = new OrderStatusDao();
	
	
	
    /**
	 * @return the orderStatusDao
	 */
	public OrderStatusDao getOrderStatusDao() {
		return orderStatusDao;
	}



	/**
	 * @param orderStatusDao the orderStatusDao to set
	 */
	public void setOrderStatusDao(OrderStatusDao orderStatusDao) {
		this.orderStatusDao = orderStatusDao;
	}



	/**
     * Default constructor. 
     */
    public OrderStatusController() {
        // TODO Auto-generated constructor stub
    }

}

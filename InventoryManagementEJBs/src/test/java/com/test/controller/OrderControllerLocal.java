package com.test.controller;

import java.util.List;

import javax.ejb.Local;

import com.test.entity.Order;

import com.test.entity.Order;

@Local
public interface OrderControllerLocal {

	public List<Order> getAllOrderforXClient(int id);
	public List<Order> getProductOrders(int productId);
	public List<Order> getAllOrderforManager();
	public void changeOrderStatus(Order order);
	void addOrder(Order order);
}

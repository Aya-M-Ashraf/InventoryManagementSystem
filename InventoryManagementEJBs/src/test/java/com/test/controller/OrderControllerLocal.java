package com.test.controller;

import javax.ejb.Local;

import com.test.entity.Order;

@Local
public interface OrderControllerLocal {

	void addOrder(Order order);

}

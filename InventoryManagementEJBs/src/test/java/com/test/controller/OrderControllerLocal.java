package com.test.controller;

import java.util.List;

import javax.ejb.Local;

import com.test.entity.Order;

import com.test.entity.Order;

@Local
public interface OrderControllerLocal {
	public List<Order> getAllOrderforXClient(int id);
}

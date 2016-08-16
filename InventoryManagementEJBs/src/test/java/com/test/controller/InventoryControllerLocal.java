package com.test.controller;

import javax.ejb.Local;

import com.test.daos.InventoryDao;

@Local
public interface InventoryControllerLocal {
	public InventoryDao getInventoryDao();
	public void setInventoryDao(InventoryDao inventoryDao);
}

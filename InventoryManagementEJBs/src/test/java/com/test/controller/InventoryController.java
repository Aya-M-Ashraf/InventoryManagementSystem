package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.InventoryDao;

/**
 * Session Bean implementation class InventoryController
 */
@Stateless
@LocalBean
public class InventoryController implements InventoryControllerLocal {
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private InventoryDao inventoryDao = new InventoryDao();  //before use it setEntitymanager first

	public InventoryController() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public InventoryDao getInventoryDao() {
		return inventoryDao;
	}

	@Override
	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	/**
	 * Default constructor.
	 */
	
}

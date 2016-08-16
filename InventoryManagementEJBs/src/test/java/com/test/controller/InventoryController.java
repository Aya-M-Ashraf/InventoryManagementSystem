package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.test.daos.InventoryDao;

/**
 * Session Bean implementation class InventoryController
 */
@Stateless
@LocalBean
public class InventoryController implements InventoryControllerLocal {

	private InventoryDao inventoryDao = new InventoryDao();
	
	
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
    public InventoryController() {
        // TODO Auto-generated constructor stub
    }

   
}

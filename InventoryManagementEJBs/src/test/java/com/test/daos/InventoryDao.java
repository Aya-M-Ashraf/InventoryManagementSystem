package com.test.daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.test.dao.impl.GenericDao;
import com.test.entity.Inventory;

/**
 * Session Bean implementation class InventoryDao
 */
 
public class InventoryDao extends GenericDao<Inventory,Integer>   {

	
	private EntityManager entityManager;
	
    public InventoryDao() {
        super(Inventory.class);
    }

	@Override
	protected EntityManager getEntityManager() {
			return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


}

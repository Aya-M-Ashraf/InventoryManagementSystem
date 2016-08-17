package com.test.daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.test.dao.impl.GenericDao;
import com.test.entity.Inventory;

/**
 * Session Bean implementation class InventoryDao
 */
 
public class InventoryDao extends GenericDao<Inventory,Integer>   {

//	@PersistenceContext(unitName = "InventoryManagementEJBs")
//    private EntityManager em;
	
	private EntityManager em = Persistence.createEntityManagerFactory("InventoryManagementEJBs").createEntityManager();
	
    public InventoryDao() {
        super(Inventory.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}

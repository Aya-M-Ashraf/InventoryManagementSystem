package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.Inventory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class InventoryDao
 */
@Stateless
@LocalBean
public class InventoryDao extends GenericDao<Inventory,Integer> implements InventoryDaoLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
    private EntityManager em;
	
    public InventoryDao() {
        super(Inventory.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}

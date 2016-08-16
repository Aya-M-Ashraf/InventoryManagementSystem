package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.OrderStatus;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OredrStatusDao
 */
 
public class OrderStatusDao extends GenericDao<OrderStatus,Integer>  {

//	@PersistenceContext(unitName = "InventoryManagementEJBs")
//    private EntityManager em;
	
	EntityManager em = Persistence.createEntityManagerFactory("InventoryManagementEJBs").createEntityManager();
	
    public OrderStatusDao() {
        super(OrderStatus.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}

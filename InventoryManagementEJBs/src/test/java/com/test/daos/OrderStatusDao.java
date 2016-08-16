package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.OrderStatus;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OredrStatusDao
 */
@Stateless
@LocalBean
public class OrderStatusDao extends GenericDao<OrderStatus,Integer> implements OredrStatusDaoLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
    private EntityManager em;
	
    public OrderStatusDao() {
        super(OrderStatus.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}

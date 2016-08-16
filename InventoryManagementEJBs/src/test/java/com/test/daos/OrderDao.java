package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class OrderDao
 */
@Stateless
@LocalBean
public class OrderDao extends GenericDao<Order,Integer> implements OrderDaoLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
    private EntityManager em;
	
    public OrderDao() {
    	super(Order.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}

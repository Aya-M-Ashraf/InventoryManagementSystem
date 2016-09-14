package com.test.controller;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.test.daos.OrderDao;
import com.test.daos.OrderStatusDao;
import com.test.entity.Order;

/**
 * Session Bean implementation class OrderController
 */
@Stateless
@LocalBean
public class OrderController implements OrderControllerLocal {
	
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager entityManager;

	private OrderDao orderDao = new OrderDao();  //before use it setEntitymanager first
	private OrderStatusDao orderStatusDao = new OrderStatusDao(); //before use it setEntitymanager first
	
	
	public OrderDao getOrderDao() {
		return orderDao;
	}


	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	@Override
	public List<Order> getAllOrderforXClient(int id) {
			orderDao.setEntityManager(entityManager);
		return orderDao.getAllOrderforXClientDao(id);
	}

	@Override
	public List<Order> getAllOrderforManager() {
		orderDao.setEntityManager(entityManager);
		return orderDao.findAll();
	}

	@Override
	public void changeOrderStatus(Order order) {
		orderDao.setEntityManager(entityManager);
		try {
			orderDao.update(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOrder(Order order) {
		orderDao.setEntityManager(entityManager);
		orderStatusDao.setEntityManager(entityManager);
	    order.setOrderStatus(orderStatusDao.findById(1));
		try {
			orderDao.makePersistent(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

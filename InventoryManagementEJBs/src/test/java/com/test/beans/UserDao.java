package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserDao
 */
@Stateless
@LocalBean
public class UserDao extends GenericDao<User,Integer> implements UserDaoLocal {

	@PersistenceContext(unitName = "InventoryManagementEJBs")
    private EntityManager em;

    public UserDao() {
        super(User.class);
    }      

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}

package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserDao
 */
 
public class UserDao extends GenericDao<User,Integer>  {

	
	private EntityManager entityManager;

    public UserDao() {
        super(User.class);
    }      

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


}

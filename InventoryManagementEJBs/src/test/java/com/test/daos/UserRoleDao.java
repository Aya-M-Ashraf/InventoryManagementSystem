package com.test.daos;

import com.test.dao.impl.GenericDao;
import com.test.entity.UserRole;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserRoleDao
 */
 
public class UserRoleDao extends GenericDao<UserRole,Integer>   {
	
	private EntityManager entityManager;
	
    public UserRoleDao() {
        super(UserRole.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    

}

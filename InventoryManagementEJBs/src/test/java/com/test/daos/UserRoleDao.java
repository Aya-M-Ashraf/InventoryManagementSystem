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
	
//	@PersistenceContext(unitName = "InventoryManagementEJBs")
//    private EntityManager em;
	
	EntityManager em = Persistence.createEntityManagerFactory("InventoryManagementEJBs").createEntityManager();
	
    public UserRoleDao() {
        super(UserRole.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
    

}

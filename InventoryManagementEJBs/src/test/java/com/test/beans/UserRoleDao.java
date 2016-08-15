package com.test.beans;

import com.test.dao.impl.GenericDao;
import com.test.entity.UserRole;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class UserRoleDao
 */
@Stateless
@LocalBean
public class UserRoleDao extends GenericDao<UserRole,Integer> implements UserRoleDaoLocal {
	
	@PersistenceContext(unitName = "InventoryManagementEJBs")
    private EntityManager em;
	
    public UserRoleDao() {
        super(UserRole.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
    

}

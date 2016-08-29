package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.UserRoleDao;

/**
 * Session Bean implementation class UserRolerController
 */
@Stateless
@LocalBean
public class UserRolerController implements UserRolerControllerLocal {
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	
	private UserRoleDao userRoleDao = new UserRoleDao(); //before use it setEntitymanager first
	
	
    /**
	 * @return the userRoleDao
	 */
	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}


	/**
	 * @param userRoleDao the userRoleDao to set
	 */
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}


	/**
     * Default constructor. 
     */
    public UserRolerController() {
        // TODO Auto-generated constructor stub
    }

}

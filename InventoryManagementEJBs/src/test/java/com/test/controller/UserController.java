package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.UserDao;

/**
 * Session Bean implementation class UserController
 */
@Stateless
@LocalBean
public class UserController implements UserControllerLocal {
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;

	private UserDao userDao = new UserDao();

    public UserController() {
        // TODO Auto-generated constructor stub
    }

}

package com.test.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.test.daos.UserDao;

/**
 * Session Bean implementation class UserController
 */
@Stateless
@LocalBean
public class UserController implements UserControllerLocal {

	private UserDao userDao = new UserDao();
	
	
    /**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}


	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	/**
     * Default constructor. 
     */
    public UserController() {
        // TODO Auto-generated constructor stub
    }

}

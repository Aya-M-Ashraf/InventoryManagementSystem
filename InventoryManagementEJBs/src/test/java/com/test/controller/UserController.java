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

    public UserController() {
        // TODO Auto-generated constructor stub
    }

}

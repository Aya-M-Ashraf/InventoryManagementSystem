package com.test.controller;

import javax.ejb.Local;

import com.test.entity.User;

@Local
public interface UserControllerLocal {
	User signIn(User user);
	void forgetPasswordController (String emailAddress);
}

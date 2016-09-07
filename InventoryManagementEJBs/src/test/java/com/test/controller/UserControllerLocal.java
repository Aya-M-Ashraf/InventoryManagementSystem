package com.test.controller;

import java.util.List;

import javax.ejb.Local;

import com.test.entity.User;

@Local
public interface UserControllerLocal {
	User signIn(User user);
	void forgetPasswordController (String emailAddress);
	void updateProfileController(String email, String oldPasswd, String newPasswd);
	public List<User> getAllClients() ; 
	public String getUserName(int id) ;
	void addUser(User user);
}

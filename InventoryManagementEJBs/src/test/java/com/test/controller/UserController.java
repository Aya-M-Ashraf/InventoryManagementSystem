package com.test.controller;

import java.security.SecureRandom;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.daos.UserDao;
import com.test.entity.User;
import com.test.util.PasswordSenderMail;

/**
 * Session Bean implementation class UserController
 */
@Stateless
@LocalBean
public class UserController implements UserControllerLocal {
	
	@PersistenceContext(unitName = "InventoryManagementEJBs")
	private EntityManager em;
	private UserDao userDao = new UserDao();
	private final int PASSWORD_LENGTH = 8;
	private static final Random RANDOM = new SecureRandom();

	public UserController() {
	}

	@Override
	public User signIn(User user) {
		userDao.setEm(em);
		User DbUser = userDao.findByEmail(user.getEmail());

		if (DbUser != null) {
			return DbUser;
		} else {
			return null;
		}
	}

	@Override
	public void forgetPasswordController(String emailAddress) {
		userDao.setEm(em);
		User retrivedUser = userDao.findByEmail(emailAddress);
		if(retrivedUser !=null){
			String generatedOne = generateRandomPassword();
			System.out.println("*************"+ generatedOne +"*************" );
			retrivedUser.setPassword(generatedOne);
			try {
				userDao.update(retrivedUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PasswordSenderMail.generateAndSendEmail(retrivedUser.getPassword(), emailAddress);
		}
	}
	
	public String generateRandomPassword() {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ123456789!@";
        String password = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            password += letters.substring(index, index + 1);
        }
        return password;
    }
}

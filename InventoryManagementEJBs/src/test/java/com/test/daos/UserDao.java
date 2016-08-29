package com.test.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.test.dao.impl.GenericDao;
import com.test.entity.User;

/**
 * Session Bean implementation class UserDao
 */

public class UserDao extends GenericDao<User, Integer> {

	EntityManager em;

	public UserDao() {
		super(User.class);
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public User findByEmail(String email) {
		TypedQuery<User> clientQuery = getEntityManager().createNamedQuery("User.findByEmail", User.class)
				.setParameter("email", email);
		try{
			User user = clientQuery.getSingleResult();
			return user;
		}
		catch(NoResultException e){
			return null;
		}
	}

}

package com.test.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.test.constants.UserRoleConstant;
import com.test.dao.impl.GenericDao;
import com.test.entity.User;

/**
 * Session Bean implementation class UserDao
 */

public class UserDao extends GenericDao<User, Integer> {

	private EntityManager entityManager;

	public UserDao() {
		super(User.class);
	}

	public void setEm(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
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
	public List<User> getAllClients() {
		TypedQuery<User> clientQuery = getEntityManager().createNamedQuery("User.findByUserRole", User.class)
				.setParameter("userRole", UserRoleConstant.CLIENT);

		List<User> clients = clientQuery.getResultList();
		return clients;
	}

	
	public String getUserName(int id) {
		return getEntityManager().find(User.class,id).getUsername();
	}

}

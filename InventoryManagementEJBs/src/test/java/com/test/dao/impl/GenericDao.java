package com.test.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.test.dao.interfaces.GenericDaoInt;

public abstract class GenericDao<T, ID extends Serializable> implements GenericDaoInt<T, ID> {

	private Class<T> persistentClass;

	protected abstract EntityManager getEntityManager();

	public GenericDao(Class<T> presistenetClass) {
		this.persistentClass = presistenetClass;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T findById(ID id)  {
		return (T) getEntityManager().find(getPersistentClass(), id);
	}

	@Override
	public List<T> findAll(){
		System.out.println("--------- inside the Daoooo");

		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(persistentClass));
		List<T> myResult = getEntityManager().createQuery(cq).getResultList(); 
		System.out.println("----------- size of result is "+myResult.size());
		return myResult;
	}

	@Override
	public T makePersistent(T entity) throws Exception {
		getEntityManager().persist(entity);
		getEntityManager().flush();
		return entity;
	}

	@Override
	public void makeTransient(T entity) throws Exception {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	@Override
	public void update(T entity) throws Exception {
		getEntityManager().merge(entity);
	}

}

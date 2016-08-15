package com.test.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoInt<T, ID extends Serializable> {

	List<T> findAll() throws Exception;

	T findById(ID id) throws Exception;

	T makePersistent(T entity) throws Exception;

	void makeTransient(T entity) throws Exception;

	void update(T entity) throws Exception;
}

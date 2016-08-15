package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.Product;



@Local
public interface ProductDaoLocal extends GenericDaoInt<Product, Integer> {

}

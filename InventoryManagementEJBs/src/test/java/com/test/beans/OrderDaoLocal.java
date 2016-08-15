package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.Order;

@Local
public interface OrderDaoLocal extends GenericDaoInt<Order, Integer>{

}

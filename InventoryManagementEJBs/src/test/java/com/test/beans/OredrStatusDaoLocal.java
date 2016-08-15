package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.OrderStatus;

@Local
public interface OredrStatusDaoLocal  extends GenericDaoInt<OrderStatus, Integer>{

}

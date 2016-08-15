package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.Inventory;

@Local
public interface InventoryDaoLocal extends GenericDaoInt<Inventory, Integer>{

}

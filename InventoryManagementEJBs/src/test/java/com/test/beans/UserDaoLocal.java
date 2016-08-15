package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.User;

@Local
public interface UserDaoLocal extends GenericDaoInt<User, Integer> {

}

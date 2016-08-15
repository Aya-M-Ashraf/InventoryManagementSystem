package com.test.beans;

import javax.ejb.Local;

import com.test.dao.interfaces.GenericDaoInt;
import com.test.entity.UserRole;

@Local
public interface UserRoleDaoLocal extends GenericDaoInt<UserRole, Integer> {

}

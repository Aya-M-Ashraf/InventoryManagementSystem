package com.test.beans;

import javax.ejb.Local;

@Local
public interface MyBeanLocal {
	public String sayHello(String input);
}

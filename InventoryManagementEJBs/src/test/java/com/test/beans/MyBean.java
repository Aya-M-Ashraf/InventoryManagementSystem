package com.test.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class MyBean
 */
@Stateless
@LocalBean
public class MyBean implements MyBeanLocal {

    /**
     * Default constructor. 
     */
    public MyBean() {
        // TODO Auto-generated constructor stub
    }

	public String sayHello(String input) {
		// TODO Auto-generated method stub
		return "Hello gddn, "  +input;
	}

}

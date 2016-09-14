package com.inventory.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String address;

	private String email;

	private String password;

	private String username;

	private List<OrderDTO> orders;

	private UserRoleDTO userRole;

	public UserDTO() {
		super();
	}

	public UserDTO(int id, String address, String email, String password, String username, List<OrderDTO> orders,
			UserRoleDTO userRole) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.password = password;
		this.username = username;
		this.orders = orders;
		this.userRole = userRole;
	}

	public UserDTO(int id, String address, String email, String password, String username, UserRoleDTO userRole) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.password = password;
		this.username = username;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

	public UserRoleDTO getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoleDTO userRole) {
		this.userRole = userRole;
	}

}

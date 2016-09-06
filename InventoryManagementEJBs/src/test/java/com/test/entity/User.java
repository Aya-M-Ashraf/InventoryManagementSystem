package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
@NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
@NamedQuery(name = "User.findByUserRoleId", query = "SELECT u FROM User u WHERE u.userRole.role= :userRole") })

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String address;

	private String email;

	private String password;

	private String username;

	// bi-directional many-to-one association to Order
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Order> Orders;

	// bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRole userRole;

	public User() {
	}

	public User(int id, String address, String email, String password, String username, List<Order> Orders,
			UserRole userRole) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.password = password;
		this.username = username;
		this.Orders = Orders;
		this.userRole = userRole;
	}


	public User(int id, String address, String email, String password, String username, UserRole userRole) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.password = password;
		this.username = username;
		this.userRole = userRole;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrders() {
		return this.Orders;
	}

	public void setOrders(List<Order> Orders) {
		this.Orders = Orders;
	}

	public Order addOrder(Order Order) {
		getOrders().add(Order);
		Order.setUser(this);

		return Order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
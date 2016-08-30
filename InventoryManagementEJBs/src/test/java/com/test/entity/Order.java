package com.test.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name = "the_order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="delivery_date")
	private Date deliveryDate;

	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
	private Date orderDate;

	private int quantity;

	@Column(name="total_weight")
	private double totalWeight;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="order_status_id")
	private OrderStatus orderStatus;

	//bi-directional many-to-one association to Product
	@ManyToOne
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Order() {
	}
	
	

	public Order(int id, Date deliveryDate, Date orderDate, int quantity, double totalWeight, OrderStatus orderStatus,
			Product product, User user) {
		super();
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.totalWeight = totalWeight;
		this.orderStatus = orderStatus;
		this.product = product;
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalWeight() {
		return this.totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
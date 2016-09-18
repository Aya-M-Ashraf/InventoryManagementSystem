package com.test.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "expiry_alarm")
	private byte expiryAlarm;

	@Temporal(TemporalType.DATE)
	@Column(name = "expiry_date")
	private Date expiryDate;

	private String name;

	private int quantity;

	private byte status;

	private int threshold;

	@Column(name = "threshold_alarm")
	private byte thresholdAlarm;

	private double weight;


	//bi-directional one-to-one association to Inventory
	@OneToOne(mappedBy="product",cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.REFRESH})
	private Inventory inventory;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="product",cascade = {CascadeType.MERGE,CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Order> orders;

	public Product() {
		expiryDate = new Date();
		name="";
		inventory=new Inventory();
	}

	public Product(int id, byte expiryAlarm, Date expiryDate, String name, int quantity, byte status, int threshold,
			byte thresholdAlarm, double weight, Inventory inventory, List<Order> orders) {
		super();
		this.id = id;
		this.expiryAlarm = expiryAlarm;
		this.expiryDate = expiryDate;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.threshold = threshold;
		this.thresholdAlarm = thresholdAlarm;
		this.weight = weight;
		this.inventory = inventory;
		this.orders = orders;
	}

	public Product(int id, byte expiryAlarm, Date expiryDate, String name, int quantity, byte status, int threshold,
			byte thresholdAlarm, double weight, Inventory inventory, Object object) {
		super();
		this.id = id;
		this.expiryAlarm = expiryAlarm;
		this.expiryDate = expiryDate;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.threshold = threshold;
		this.thresholdAlarm = thresholdAlarm;
		this.weight = weight;
		this.inventory = inventory;
	}

	public Product(byte expiryAlarm, Date expiryDate, String name, int quantity, byte status, int threshold,
			byte thresholdAlarm, double weight ,Inventory inventory) {
		super();
		this.expiryAlarm = expiryAlarm;
		this.expiryDate = expiryDate;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.threshold = threshold;
		this.thresholdAlarm = thresholdAlarm;
		this.weight = weight;
		this.inventory = inventory;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getExpiryAlarm() {
		return this.expiryAlarm;
	}

	public void setExpiryAlarm(byte expiryAlarm) {
		this.expiryAlarm = expiryAlarm;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getThreshold() {
		return this.threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public byte getThresholdAlarm() {
		return this.thresholdAlarm;
	}

	public void setThresholdAlarm(byte thresholdAlarm) {
		this.thresholdAlarm = thresholdAlarm;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setProduct(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setProduct(null);

		return order;
	}

}
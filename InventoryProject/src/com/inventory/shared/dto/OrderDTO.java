package com.inventory.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date deliveryDate;
	private Date orderDate;
	private int quantity;
	private double totalWeight;
	private OrderStatusDTO orderStatus;
	private ProductDTO product;
	private UserDTO userDto;

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public OrderDTO() {
		super();
	}

	public OrderDTO(int id, Date deliveryDate, Date orderDate, int quantity, double totalWeight,
			OrderStatusDTO orderStatus, ProductDTO product, UserDTO userDto) {
		super();
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.quantity = quantity;
		this.totalWeight = totalWeight;
		this.orderStatus = orderStatus;
		this.product = product;
		this.userDto = userDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public OrderStatusDTO getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusDTO orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

}

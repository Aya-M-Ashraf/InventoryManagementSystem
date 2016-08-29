package com.inventory.shared.dto;

import java.io.Serializable;

public class InventoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int quantity;
	private int quantityForOrder;
	private Integer productId;

	public InventoryDTO() {
		super();
	}

	public InventoryDTO(int quantity, int quantityForOrder, Integer productId) {
		super();
		this.quantity = quantity;
		this.quantityForOrder = quantityForOrder;
		this.productId = productId;
//		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantityForOrder() {
		return quantityForOrder;
	}

	public void setQuantityForOrder(int quantityForOrder) {
		this.quantityForOrder = quantityForOrder;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}

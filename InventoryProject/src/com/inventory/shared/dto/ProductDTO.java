package com.inventory.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private byte expiryAlarm;
	private Date expiryDate;
	private String name;
	private int quantity;
	private byte status;
	private int threshold;
	private byte thresholdAlarm;
	private double weight;
	private InventoryDTO inventory;


	public ProductDTO() {
		super();
	}

	public ProductDTO(int id, byte expiryAlarm, Date expiryDate, String name, int quantity, byte status, int threshold,
			byte thresholdAlarm, double weight, InventoryDTO inventory) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getExpiryAlarm() {
		return expiryAlarm;
	}

	public void setExpiryAlarm(byte expiryAlarm) {
		this.expiryAlarm = expiryAlarm;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public byte getThresholdAlarm() {
		return thresholdAlarm;
	}

	public void setThresholdAlarm(byte thresholdAlarm) {
		this.thresholdAlarm = thresholdAlarm;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public InventoryDTO getInventory() {
		return inventory;
	}

	public void setInventory(InventoryDTO inventory) {
		this.inventory = inventory;
	}

}

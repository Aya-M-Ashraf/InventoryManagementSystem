package com.test.util;

import javax.xml.datatype.XMLGregorianCalendar;

import com.test.entity.Inventory;
import com.test.entity.Product;
import com.test.xmlSchema.InventoryType;
import com.test.xmlSchema.ProductType;

public class EntityMapper {

	public Product mapProductTypeToProduct(ProductType productType) {
		boolean isExpiryAlarm = productType.isExpiryAlarm();
		boolean isStatus = productType.isStatus();
		boolean isThresholdAlarm = productType.isThresholdAlarm();
		XMLGregorianCalendar ExpiryDateProdType = productType.getExpiryDate();
		Product productEntity = new Product((byte) (isExpiryAlarm ? 1 : 0),
				ExpiryDateProdType.toGregorianCalendar().getTime(), productType.getName(),
				productType.getQuantity().intValue(), (byte) (isStatus ? 1 : 0), productType.getThreshold().intValue(),
				(byte) (isThresholdAlarm ? 1 : 0), productType.getWeight() /*, mapInventoryTypeToInventory(productType.getInventory()) */);
		return productEntity;
	}
	
	
	// BEFORE PRESISTING PRODUCT SET THE NULL FIELDS IN INVENTORY
	
	public Inventory mapInventoryTypeToInventory(InventoryType inventoryType){
		Inventory inventoryEntity = new Inventory();
		inventoryEntity.setQuantity(inventoryType.getQuantity().intValue());
		inventoryEntity.setQuantityForOrder(inventoryType.getQuantityForOrder().intValue());
		return inventoryEntity;
	}
}

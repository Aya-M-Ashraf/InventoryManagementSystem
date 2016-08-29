package com.test.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

@Entity
@Table(name = "inventory")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
		@NamedQuery(name = "Inventory.findByQuantity", query = "SELECT i FROM Inventory i WHERE i.quantity = :quantity"),
		@NamedQuery(name = "Inventory.findByQuantityForOrder", query = "SELECT i FROM Inventory i WHERE i.quantityForOrder = :quantityForOrder"),
		@NamedQuery(name = "Inventory.findByProductId", query = "SELECT i FROM Inventory i WHERE i.productId = :productId") })
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "quantity")
	private int quantity;
	@Basic(optional = false)
	@Column(name = "quantity_for_order")
	private int quantityForOrder;
	@Id
	@Basic(optional = false)
	@Column(name = "product_id")
	private Integer productId;
	@JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
	@OneToOne(optional = false)
	private Product product;

	public Inventory() {
	}
	
	

	public Inventory(int quantity, int quantityForOrder, Integer productId, Product product) {
		super();
		this.quantity = quantity;
		this.quantityForOrder = quantityForOrder;
		this.productId = productId;
		this.product = product;
	}



	public Inventory(Integer productId) {
		this.productId = productId;
	}

	public Inventory(Integer productId, int quantity, int quantityForOrder) {
		this.productId = productId;
		this.quantity = quantity;
		this.quantityForOrder = quantityForOrder;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (productId != null ? productId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Inventory)) {
			return false;
		}
		Inventory other = (Inventory) object;
		if ((this.productId == null && other.productId != null)
				|| (this.productId != null && !this.productId.equals(other.productId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "javaapplication4.Inventory[ productId=" + productId + " ]";
	}

}

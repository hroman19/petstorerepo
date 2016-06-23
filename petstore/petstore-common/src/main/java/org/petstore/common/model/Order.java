package org.petstore.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "order_ps")
@NamedQueries({
		@NamedQuery(name = "Order.getAllOrdersByUserId", query = "SELECT o FROM Order o WHERE o.userId = :userId"),
		@NamedQuery(name = "Order.getAllOrdersByUserIdAndProductId", query = "SELECT o FROM Order o WHERE o.userId = :userID AND o.product.id = :productID"),
})
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, updatable = false)
	private Product product;

	@Column(name = "time")
	private Date timeOfPurchase;

	@Column(name = "status")
	private String status;

	public Order() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}

	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

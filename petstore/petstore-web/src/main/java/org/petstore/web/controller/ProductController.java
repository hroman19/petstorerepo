package org.petstore.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.petstore.common.model.Order;
import org.petstore.common.model.OrderStatus;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.OrderService;
import org.petstore.ejb.service.ProductService;

@ManagedBean(name = "productController")
@ViewScoped
public class ProductController implements Serializable {
	@ManagedProperty("#{productFilterController}")
	private ProductFilterController filter;
	@EJB
	private ProductService productService;
	@EJB
	private OrderService orderService;

	private List<Product> products;
	private List<String> productTypes;

	@PostConstruct
	public void initFilter() {
		products = productService.getAll();
		productTypes = productService.getProductTypes();
	}

	public List<String> getProductTypes() {
		return productTypes;
	}

	public List<Product> getProducts() {
		return filter.filter(products);
	}

	public void deleteProduct(Product product) {
		product.setIsDeleted(true);
		productService.update(product);
	}

	public void restoreProduct(Product product) {
		product.setIsDeleted(false);
		productService.update(product);
	}

	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		return orderService.getAllOrdersByUserIdAndProductId(userID, productID);
	}

	public Order addToBucket(Integer userID, Integer productID) {
		Order order = new Order();
		Product product = new Product();
		product.setId(productID);
		order.setProduct(product);
		order.setUserId(userID);
		order.setStatus(OrderStatus.PENDING.toString());
		order.setTimeOfPurchase(new Date(System.currentTimeMillis()));
		orderService.add(order);
		return order;
	}

	public ProductFilterController getFilter() {
		return filter;
	}

	public void setFilter(ProductFilterController filter) {
		this.filter = filter;
	}
}
package org.petstore.web.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.common.model.Order;
import org.petstore.common.model.OrderStatus;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.OrderService;
import org.petstore.ejb.service.ProductService;

@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {

	@EJB
	private ProductService productService;
	@EJB
	private OrderService orderService;

	public List<Product> getProducts() {
		return productService.getAll();
	}

	public void deleteProduct(Product product) {
		product.setIsDeleted(true);
		productService.update(product);
	}

	public void restoreProduct(Product product) {
		product.setIsDeleted(false);
		productService.update(product);
	}

	public List<Order> 	getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		return orderService.getAllOrdersByUserIdAndProductId(userID, productID);
	}
	
	//Vitalii should fix
	public Order addToBucket(Integer userID, Integer productID) {
		Order order=new Order();
		Product product = new Product();
		product.setId(productID);
		order.setProduct(product);
		order.setUserId(userID);
		order.setStatus(OrderStatus.PENDING.toString());
		order.setTimeOfPurchase(new Date(System.currentTimeMillis()));
		orderService.add(order);
		return  order;
	}
	

	
	
	

}
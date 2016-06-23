package org.petstore.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.common.model.Order;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.OrderService;
import org.petstore.ejb.service.ProductService;


@ManagedBean(name="productController")
@SessionScoped
public class ProductController implements Serializable {
	
	@EJB
	private ProductService productService;
	
	@EJB
	private OrderService orderService;
	
	public List<Product> getProducts(){
		return productService.getAll();
	}
	
	public List<Order> 	getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		return orderService.getAllOrdersByUserIdAndProductId(userID, productID);
	}
	
	
	
	
	
}
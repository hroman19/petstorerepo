package org.petstore.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.common.model.Order;
import org.petstore.ejb.service.OrderService;

@ManagedBean(name="bucketController")
@SessionScoped
public class BucketController implements Serializable {
	
	@EJB
	private OrderService orderService;
	
	public List<Order> getOrders(Integer userId){
		return orderService.getByUserId(userId);
	}
	
	public void removeOrder(Order order){
		orderService.delete(order);
	}
	
}
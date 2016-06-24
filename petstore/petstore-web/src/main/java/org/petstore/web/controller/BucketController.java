package org.petstore.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import org.petstore.common.model.Order;
import org.petstore.common.model.OrderStatus;
import org.petstore.ejb.service.OrderService;

@ManagedBean(name="bucketController")
@RequestScoped
public class BucketController implements Serializable {
	
	@EJB
	private OrderService orderService;
	
	public List<Order> getOrders(Integer userId){
		return orderService.getByUserId(userId);
	}
	
	public void removeOrder(Order order){
		orderService.delete(order);
	}
	
	public void buyOrder(Order order){
		order.setStatus(OrderStatus.BOUGHT.toString());
		orderService.update(order);
	}
	
}
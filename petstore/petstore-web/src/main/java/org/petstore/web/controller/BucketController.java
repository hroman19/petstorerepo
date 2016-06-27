package org.petstore.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.petstore.common.model.Order;
import org.petstore.common.model.OrderStatus;
import org.petstore.ejb.service.OrderService;
import org.petstore.web.util.SessionUtils;

@ManagedBean(name = "bucketController")
@ViewScoped
public class BucketController implements Serializable {

	@EJB
	private OrderService orderService;

	private String status;

	public void removeOrder(Order order) {
		orderService.delete(order);
	}
	
	public void buyOrder(Order order) {
		order.setStatus(OrderStatus.BOUGHT.toString());
		orderService.update(order);
	}

	public List<String> getAllStatuses() {
		List<String> list = new ArrayList<>();
		list.add(OrderStatus.BOUGHT.toString());
		list.add(OrderStatus.PENDING.toString());
		list.add("DELETED");
		return list;
	}

	public List<Order> getOrdersByStatus(Integer userId) {
		List<Order> list;
		if (status != null) {
			status = status.equals("DELETED") ? OrderStatus.REMOVED_BY_ADMIN.toString() : status;
			list = orderService.getByStatus(status, userId);
			for (Order order : list) {
				System.out.println(order.getProduct().getName());
			}
		} else {
			list = orderService.getByUserId(userId);
		}
		return list;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
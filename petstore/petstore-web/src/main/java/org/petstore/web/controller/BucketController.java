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

	private String search;

	public void removeOrder(Order order) {
		orderService.delete(order);
	}

	public void buyOrder(Order order) {
		order.setStatus(OrderStatus.BOUGHT.toString());
		orderService.update(order);
	}

	public List<String> getAllStatuses() {
		List<String> list = new ArrayList<>();
		list.add(OrderStatus.BOUGHT.toString().toLowerCase());
		list.add(OrderStatus.PENDING.toString().toLowerCase());
		list.add("deleted");
		return list;
	}

	public List<Order> getOrders(Integer userId) {
		search = search == null ? "" : search;
		
		status = (status == null || status.equals("none")) ? "" : status.toUpperCase();
		status = status.equals("DELETED") ? OrderStatus.REMOVED_BY_ADMIN.toString() : status;
		
		return orderService.getByStatusAndName(status, search, userId);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
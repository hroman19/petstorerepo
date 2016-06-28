package org.petstore.ejb.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.petstore.common.model.Order;
import org.petstore.ejb.dao.OrderDAO;
import org.petstore.ejb.service.OrderService;

@Stateless
public class OrderServiceImpl extends GenericServiceImpl<Integer, Order> implements OrderService{
	@EJB
	private OrderDAO orderDAO;

	@PostConstruct
	void init() {
		this.setGenericDAO(orderDAO);
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	@Override
	public List<Order> getByUserId(Integer userId) {
		return orderDAO.getByUserId(userId);
	}

	@Override
	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {		
		return orderDAO.getAllOrdersByUserIdAndProductId(userID, productID);
	}

	@Override
	public List<Order> getByStatus(String status, Integer userId) {
		return orderDAO.getByStatus(status, userId);
	}
	
}

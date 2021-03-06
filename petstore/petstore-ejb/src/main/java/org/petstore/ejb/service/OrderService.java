package org.petstore.ejb.service;

import java.util.List;

import org.petstore.common.model.Order;

public interface OrderService extends GenericService<Integer, Order> {
	public List<Order> getByUserId(Integer userId);
	
	public List<Order> getByStatusAndName(String status, String productName, Integer userId);
	
	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) ;
	
}

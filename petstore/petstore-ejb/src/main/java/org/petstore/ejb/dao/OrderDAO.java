package org.petstore.ejb.dao;

import java.util.List;

import org.petstore.common.model.Order;

public interface OrderDAO extends GenericDAO<Integer, Order> {
	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID);
	
}

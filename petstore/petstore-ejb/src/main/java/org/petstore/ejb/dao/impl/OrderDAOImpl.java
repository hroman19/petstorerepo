package org.petstore.ejb.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.petstore.common.model.Order;
import org.petstore.ejb.dao.OrderDAO;

@Stateless
public class OrderDAOImpl extends GenericDAOImpl<Integer, Order> implements OrderDAO, Serializable {

	public OrderDAOImpl() {
		super(Order.class);
	}

	@Override
	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		TypedQuery<Order> query = getEntityManager().createNamedQuery("Order.getAllOrdersByUserIdAndProductId", Order.class);
		query.setParameter("userID", userID);
		query.setParameter("productID", productID);
		List<Order> results = query.getResultList();
		
		for (Order order : results) {
			System.err.println(order);
		}
				
		return results;
	}

}

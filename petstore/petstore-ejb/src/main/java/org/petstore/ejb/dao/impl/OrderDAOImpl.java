package org.petstore.ejb.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.petstore.common.model.Order;
import org.petstore.ejb.dao.OrderDAO;

@Stateless
public class OrderDAOImpl extends GenericDAOImpl<Integer, Order> implements OrderDAO, Serializable {

	public OrderDAOImpl() {
		super(Order.class);
	}

	@Override
	public List<Order> getByUserId(Integer userId) {
		entityManager.clear();
		Query query = entityManager.createNamedQuery("Order.getAllOrdersByUserId");
		query.setParameter("userId", userId);
		return query.getResultList();
	}
	@Override
	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		entityManager.clear();
		Query query = getEntityManager().createNamedQuery("Order.getAllOrdersByUserIdAndProductId");
		query.setParameter("userID", userID);
		query.setParameter("productID", productID);
		List<Order> results = query.getResultList();			
		return results;
	}

}

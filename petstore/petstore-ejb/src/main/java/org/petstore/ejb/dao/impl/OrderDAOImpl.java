package org.petstore.ejb.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.petstore.common.model.Order;
import org.petstore.ejb.dao.OrderDAO;


@Stateless
public class OrderDAOImpl extends GenericDAOImpl<Integer, Order> implements OrderDAO, Serializable {

	public OrderDAOImpl() {
		super(Order.class);
	}

}

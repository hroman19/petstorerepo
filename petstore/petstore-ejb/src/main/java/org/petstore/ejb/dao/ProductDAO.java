package org.petstore.ejb.dao;

import org.petstore.common.model.Product;

public interface ProductDAO extends GenericDAO<Integer, Product> {
	public double getProductWithMinPrice();
	public double getProductWithMaxPrice();
}

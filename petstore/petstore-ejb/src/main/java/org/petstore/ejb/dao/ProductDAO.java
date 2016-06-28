package org.petstore.ejb.dao;

import java.util.List;

import org.petstore.common.model.Product;

public interface ProductDAO extends GenericDAO<Integer, Product> {
	public double getProductWithMinPrice();
	public double getProductWithMaxPrice();
	public List<String> getProductTypes();
}

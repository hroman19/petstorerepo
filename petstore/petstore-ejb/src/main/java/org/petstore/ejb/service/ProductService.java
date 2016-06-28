package org.petstore.ejb.service;

import java.util.List;

import org.petstore.common.model.Product;

public interface ProductService extends GenericService<Integer, Product> {
	public double getProductWithMinPrice();

	public double getProductWithMaxPrice();

	public List<String> getProductTypes();
}

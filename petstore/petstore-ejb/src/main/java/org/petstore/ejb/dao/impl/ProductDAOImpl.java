package org.petstore.ejb.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.petstore.common.model.Product;
import org.petstore.ejb.dao.ProductDAO;

@Stateless
public class ProductDAOImpl extends GenericDAOImpl<Integer, Product> implements ProductDAO, Serializable{

	public ProductDAOImpl() {
		super(Product.class);
	}

	public double getProductWithMinPrice() {
		entityManager.clear();
		TypedQuery<Double> query =  (TypedQuery<Double>) entityManager.createNamedQuery("Product.getProductWithMinPrice");
		double result = query.getSingleResult();			
		return result;
	}
	public double getProductWithMaxPrice() {
		entityManager.clear();
		TypedQuery<Double> query =  (TypedQuery<Double>) entityManager.createNamedQuery("Product.getProductWithMaxPrice");
		double result = query.getSingleResult();			
		return result;
	}

}

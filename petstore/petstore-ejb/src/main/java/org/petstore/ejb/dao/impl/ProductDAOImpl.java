package org.petstore.ejb.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.petstore.common.model.Product;
import org.petstore.ejb.dao.ProductDAO;

@Stateless
public class ProductDAOImpl extends GenericDAOImpl<Integer, Product> implements ProductDAO, Serializable{

	public ProductDAOImpl() {
		super(Product.class);
	}

}

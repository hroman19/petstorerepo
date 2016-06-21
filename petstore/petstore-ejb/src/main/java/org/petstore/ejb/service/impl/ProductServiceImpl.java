package org.petstore.ejb.service.impl;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.petstore.common.model.Product;
import org.petstore.ejb.dao.ProductDAO;
import org.petstore.ejb.service.ProductService;



@Stateless
public class ProductServiceImpl extends GenericServiceImpl<Integer, Product> implements ProductService {
	@EJB
	private ProductDAO productDAO;

	@PostConstruct
	void init() {
		this.setGenericDAO(productDAO);
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	
}

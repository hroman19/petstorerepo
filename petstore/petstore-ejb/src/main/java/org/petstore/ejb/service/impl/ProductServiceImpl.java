package org.petstore.ejb.service.impl;

import java.util.List;

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

	public double getProductWithMinPrice() {
		return productDAO.getProductWithMinPrice();

	}

	public double getProductWithMaxPrice() {
		return productDAO.getProductWithMaxPrice();
	}
	
	public double getProductWithMinPriceWithType(String type) {
		return productDAO.getProductWithMinPriceWithType(type);
	}

	public double getProductWithMaxPriceWithType(String type) {
		return productDAO.getProductWithMaxPriceWithType(type);
	}

	public List<String> getProductTypes() {
		return productDAO.getProductTypes();
	}

}

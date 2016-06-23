package org.petstore.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.petstore.common.model.Product;
import org.petstore.ejb.service.ProductService;

@ManagedBean
public class AdminController {
	@EJB
	private ProductService productService;
	
	private Product product = new Product();
	
	public void addProduct(){
		
	}
	
	
	
	
}

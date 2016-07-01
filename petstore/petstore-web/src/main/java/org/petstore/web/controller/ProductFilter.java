package org.petstore.web.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.petstore.common.model.Product;
import org.petstore.ejb.service.ProductService;

import com.google.common.base.Strings;

@ViewScoped
@ManagedBean
public class ProductFilter implements Serializable {
	@EJB
	private ProductService productService;
	
	public int defaultMinPrice;
	public int defaultMaxPrice;

	public int minPrice;
	public int maxPrice;

	public String type;

	public String search;

	@PostConstruct
	public void init() {
		int defaultMinPrice = (int)productService.getProductWithMinPrice();
		int defaultMaxPrice = (int)productService.getProductWithMaxPrice();	
		this.defaultMinPrice = defaultMinPrice;
		this.defaultMaxPrice = defaultMaxPrice;
		minPrice = defaultMinPrice;
		maxPrice = defaultMaxPrice;
	}
	
	public List<Product> filter(List<Product> products){
		return products.stream().filter(x -> {
			boolean valid = true;
			if (!(x.getPrice() >= minPrice && x.getPrice() <= maxPrice)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(type) && !"none".equals(type) && !x.getType().equals(type)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(search)
					&& !x.getName().toLowerCase().contains(search.trim().toLowerCase())) {
				valid = false;
			}
			return valid;
		}).collect(Collectors.toList());
	}
	
	public void refresh(){
		int defaultMinPrice = (int)productService.getProductWithMinPrice();
		int defaultMaxPrice = (int)productService.getProductWithMaxPrice();
		
		this.defaultMinPrice = defaultMinPrice;
		this.defaultMaxPrice = defaultMaxPrice;
		minPrice = defaultMinPrice;
		maxPrice = defaultMaxPrice;
	}

	public int getDefaultMinPrice() {
		return defaultMinPrice;
	}

	public void setDefaultMinPrice(int defaultMinPrice) {
		this.defaultMinPrice = defaultMinPrice;
	}

	public int getDefaultMaxPrice() {
		return defaultMaxPrice;
	}

	public void setDefaultMaxPrice(int defaultMaxPrice) {
		this.defaultMaxPrice = defaultMaxPrice;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

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
public class ProductFilterController implements Serializable {
	@EJB
	private ProductService productService;

	public int defaultMinPrice;
	public int defaultMaxPrice;

	public int minPriceLimit;
	public int maxPriceLimit;

	public int minPrice;
	public int maxPrice;

	public String type;

	public String search;

	@PostConstruct
	public void init() {
		defaultMinPrice = (int) productService.getProductWithMinPrice();
		defaultMaxPrice = (int) productService.getProductWithMaxPrice();

		minPriceLimit = defaultMinPrice;
		maxPriceLimit = defaultMaxPrice;

		minPrice = defaultMinPrice;
		maxPrice = defaultMaxPrice;
	}

	public List<Product> filter(List<Product> products) {
		return products.stream().filter(x -> {
			boolean valid = true;
			if (!(x.getPrice() >= minPrice && x.getPrice() <= maxPrice)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(type) && !"none".equals(type) && !x.getType().equals(type)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(search) && !x.getName().toLowerCase().contains(search.trim().toLowerCase())) {
				valid = false;
			}
			return valid;
		}).collect(Collectors.toList());
	}

	private void restorePriceLimits() {
		minPriceLimit = defaultMinPrice;
		maxPriceLimit = defaultMaxPrice;
	}

	private void setPriceLimitsForType(String type) {
		minPriceLimit = (int) productService.getProductWithMinPriceWithType(type);
		maxPriceLimit = (int) productService.getProductWithMaxPriceWithType(type);
		if(minPrice < minPriceLimit){
			minPrice = minPriceLimit;
		}
		if(maxPrice > maxPriceLimit){
			maxPrice = maxPriceLimit;
		}
	}

	public void refresh() {
		defaultMinPrice = (int) productService.getProductWithMinPrice();
		defaultMaxPrice = (int) productService.getProductWithMaxPrice();

		minPriceLimit = defaultMinPrice;
		maxPriceLimit = defaultMaxPrice;

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

	public int getMinPriceLimit() {
		return minPriceLimit;
	}

	public void setMinPriceLimit(int minPriceLimit) {
		this.minPriceLimit = minPriceLimit;
	}

	public int getMaxPriceLimit() {
		return maxPriceLimit;
	}

	public void setMaxPriceLimit(int maxPriceLimit) {
		this.maxPriceLimit = maxPriceLimit;
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
		if(!Strings.isNullOrEmpty(type) && !"none".equals(type)){
			setPriceLimitsForType(type);
		}else {
			restorePriceLimits();
		}
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}

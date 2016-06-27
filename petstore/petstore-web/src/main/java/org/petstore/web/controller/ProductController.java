package org.petstore.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.petstore.common.model.Order;
import org.petstore.common.model.OrderStatus;
import org.petstore.common.model.Product;
import org.petstore.ejb.service.OrderService;
import org.petstore.ejb.service.ProductService;

import com.google.common.base.Strings;

@ManagedBean(name = "productController")
@ViewScoped
public class ProductController implements Serializable {

	@EJB
	private ProductService productService;
	@EJB
	private OrderService orderService;

	private Filter filter;
	private List<Product> products;
	private List<String> productTypes;

	@PostConstruct
	public void initFilter() {
		filter = new Filter((int) productService.getProductWithMinPrice(),
				(int) productService.getProductWithMaxPrice());
		products = productService.getAll();
		productTypes = productService.getProductTypes();
	}

	public List<String> getProductTypes() {
		return productTypes;
	}

	public List<Product> getProducts() {
		return products.stream().filter(x -> {
			boolean valid = true;
			if (!(x.getPrice() >= filter.minPrice && x.getPrice() <= filter.maxPrice)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(filter.type) && !"none".equals(filter.type) && !x.getType().equals(filter.type)) {
				valid = false;
			}
			if (!Strings.isNullOrEmpty(filter.search)
					&& !x.getName().toLowerCase().contains(filter.search.trim().toLowerCase())) {
				valid = false;
			}
			return valid;
		}).collect(Collectors.toList());
	}

	public void deleteProduct(Product product) {
		product.setIsDeleted(true);
		productService.update(product);
	}

	public void restoreProduct(Product product) {
		product.setIsDeleted(false);
		productService.update(product);
	}

	public List<Order> getAllOrdersByUserIdAndProductId(Integer userID, Integer productID) {
		return orderService.getAllOrdersByUserIdAndProductId(userID, productID);
	}

	public Order addToBucket(Integer userID, Integer productID) {
		Order order = new Order();
		Product product = new Product();
		product.setId(productID);
		order.setProduct(product);
		order.setUserId(userID);
		order.setStatus(OrderStatus.PENDING.toString());
		order.setTimeOfPurchase(new Date(System.currentTimeMillis()));
		orderService.add(order);
		return order;
	}

	public Filter getFilter() {
		return filter;
	}

	public class Filter {

		public int defaultMinPrice;
		public int defaultMaxPrice;

		public int minPrice;
		public int maxPrice;

		public String type;

		public String search;

		public Filter(int defaultMinPrice, int defaultMaxPrice) {
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

}
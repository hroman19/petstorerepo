package org.petstore.web.util;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.ejb.service.ProductService;

@ManagedBean
@SessionScoped
public class CategoryDataSource implements Serializable {
	@EJB
	private ProductService productService;

	public List<String> categories;

	@PostConstruct
	public void init() {
		categories = productService.getAll().stream().map(x -> x.getType()).distinct().collect(Collectors.toList());

		System.out.println(categories);
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> filterByName(String name) {
		return categories.stream().filter(x -> {
			return x.toLowerCase().startsWith(name.toLowerCase());
		}).collect(Collectors.toList());
	}
}
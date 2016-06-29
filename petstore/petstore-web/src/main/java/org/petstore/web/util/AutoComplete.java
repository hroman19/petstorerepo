package org.petstore.web.util;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AutoComplete implements Serializable {
	@ManagedProperty("#{categoryDataSource}")
	private CategoryDataSource ds;

	private String selectedCategory;

	public AutoComplete (){

	}

	public List<String> complete(String query){
		return ds.filterByName(query);
	}

	public String getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public CategoryDataSource getDs() {
		return ds;
	}

	public void setDs(CategoryDataSource ds) {
		this.ds = ds;
	}
}

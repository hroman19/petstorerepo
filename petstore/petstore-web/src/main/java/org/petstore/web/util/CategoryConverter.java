package org.petstore.web.util;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="categoryConverter")
public class CategoryConverter implements Converter {
	@ManagedProperty("#{categoryDataSource}")
	private CategoryDataSource ds;

	public CategoryDataSource getDs() {
		return ds;
	}

	public void setDs(CategoryDataSource ds) {
		this.ds = ds;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof String) {
			String category = (String) value;
			return category;
		}
		return "";
	}
}
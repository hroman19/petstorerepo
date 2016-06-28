package org.petstore.web.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "navigationController")
@RequestScoped
public class NavigationController {
	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	public String showPage() throws IOException {
		return pageId;
//		return pageId + "?redirect=true";
		
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//	    ec.redirect(pageId + "?redirect=true");
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}

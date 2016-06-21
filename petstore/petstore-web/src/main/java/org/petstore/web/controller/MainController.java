package org.petstore.web.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import com.petstore.service.UserService;

@Named("myBean")
@SessionScoped
public class MainController implements Serializable {
	
	private String email = "myemail@yahoo.com";
	
	@EJB
	private UserService userService;
	
	public void getUsers(){
		System.out.println("Users: "+userService.getAll());
	}
	
	//getter and setter methods for email property
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
}
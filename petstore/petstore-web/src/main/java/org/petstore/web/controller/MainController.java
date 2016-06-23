package org.petstore.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.common.model.User;
import org.petstore.ejb.service.UserService;


@ManagedBean(name="myBean")
@SessionScoped
public class MainController implements Serializable {
	
	private String email = "myemail@yahoo.com";
	private List<User> users;
	
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
package org.petstore.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.petstore.ejb.service.UserService;

@ManagedBean(name="userController")
public class UserController {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	
	@EJB
	private UserService userService;
	
	public boolean isFreeEmail(){
		return true;
	}
}

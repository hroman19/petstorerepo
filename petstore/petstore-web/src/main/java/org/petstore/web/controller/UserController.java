package org.petstore.web.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.petstore.common.model.User;
import org.petstore.ejb.service.UserService;

@ManagedBean(name = "userController")
public class UserController {

	@EJB
	private UserService userService;

	private User user;

	public UserController() {
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void signUp(){
		userService.add(user);
	}
}

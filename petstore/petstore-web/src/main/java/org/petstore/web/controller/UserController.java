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

	public String getUserFirstName() {
		return user.getFirstName();
	}

	public void setUserFirstName(String firstName) {
		user.setFirstName(firstName);
	}

	public String getUserLastName() {
		return user.getFirstName();
	}

	public void setUserLastName(String lastName) {
		user.setLastName(lastName);
	}

	public String getUserEmail() {
		return user.getEmail();
	}

	public void setUserEmail(String email) {
		user.setEmail(email);
	}

	public void setUserPassword(String password) {
		user.setPassword(password);
	}

	public String getUserPassword() {
		return user.getPassword();
	}
	
	public void printUser(){
		System.out.println(user.toString());
	}
}

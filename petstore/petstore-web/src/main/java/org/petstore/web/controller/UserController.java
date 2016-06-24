package org.petstore.web.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.petstore.common.model.User;
import org.petstore.ejb.service.UserService;
import org.petstore.web.util.SessionUtils;

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

	public void signUp() {
		userService.add(user);
	}

	public String signIn() {
		
		boolean valid;
		System.out.println("user : " + user.toString());
		User dbUser = userService.getByEmail(user.getEmail());
		if (dbUser == null) {
			valid = false;
		} else {
			System.out.println("dbUser : " + dbUser.toString());
			valid = user.getPassword().equals(dbUser.getPassword());
		}
		System.out.println("valid : " + valid);
		if (valid) {
			System.out.println("in valid block");
			HttpSession session = SessionUtils.getSession(); 
			dbUser.setPassword(null);
			session.setAttribute("user", dbUser);

			System.out.println("dbUser.getIsAdmin( : " + dbUser.getIsAdmin());
			return dbUser.getIsAdmin() ? "admin" : "index";
		} else {
			System.out.println("in invalid block");
			System.out.println(FacesContext.getCurrentInstance());
			FacesContext.getCurrentInstance().addMessage(
					null, 
					new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "index";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
}

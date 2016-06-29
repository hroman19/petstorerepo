package org.petstore.web.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.petstore.common.model.User;
import org.petstore.ejb.service.UserService;
import org.petstore.web.util.SecurityUtil;
import org.petstore.web.util.SessionUtils;
import org.primefaces.context.RequestContext;

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
		user.setIsAdmin(false);
		user.setIsDeleted(false);
		user.setPassword(SecurityUtil.hashPassword(user.getPassword()));
		userService.add(user);
		user = new User();
		RequestContext.getCurrentInstance().execute("$('.sign-up-modal').modal('hide');");
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign Up", "You are registrated"));
	}

	public String signIn() {
		boolean valid;
		User dbUser = userService.getByEmail(user.getEmail());
		if (dbUser == null) {
			valid = false;
		} else {
			valid = SecurityUtil.hashPassword(user.getPassword()).equals(dbUser.getPassword());
		}

		if (valid) {
			HttpSession session = SessionUtils.getSession();
			dbUser.setPassword(null);
			session.setAttribute("user", dbUser);
			user = new User();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign in", "Success"));
			return dbUser.getIsAdmin() ? "admin" : "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct email and password"));
			return "index";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
}

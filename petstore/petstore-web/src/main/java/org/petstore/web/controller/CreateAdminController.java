package org.petstore.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.petstore.common.model.User;
import org.petstore.ejb.service.UserService;

@ManagedBean
@SessionScoped
public class CreateAdminController {

	@EJB
	private UserService userService;
	private List<User> allUsers;
	User selectedUser;
	private List<User> filteredUser;

	@PostConstruct
	public void init() {
		allUsers = userService.getAll();
	}

	public void createDeleteAdmin(User user) {
		userService.update(user);
	}

	public void deleteRestoreUser(User user) {
		userService.update(user);
	}

	public List<User> getFilteredUser() {

		return allUsers.stream().filter(user -> {
			boolean valid = true;
			if (user.getIsDeleted()) {
				// valid = false;
			}
			if (user.getIsAdmin()) {
				// valid = false;
			}

			return valid;
		}).collect(Collectors.toList());

	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public void setFilteredUser(List<User> filteredUser) {
		this.filteredUser = filteredUser;
	}

}

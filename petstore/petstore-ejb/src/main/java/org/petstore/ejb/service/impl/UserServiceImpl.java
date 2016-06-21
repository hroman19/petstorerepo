package org.petstore.ejb.service.impl;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.petstore.common.model.User;
import org.petstore.ejb.dao.UserDAO;
import org.petstore.ejb.service.UserService;



@Stateless
public class UserServiceImpl extends GenericServiceImpl<Integer, User> implements UserService {
	@EJB
	private UserDAO userDAO;

	@PostConstruct
	void init() {
		this.setGenericDAO(userDAO);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}

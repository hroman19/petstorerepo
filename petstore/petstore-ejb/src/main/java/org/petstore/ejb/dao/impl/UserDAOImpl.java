package org.petstore.ejb.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.petstore.common.model.User;
import org.petstore.ejb.dao.UserDAO;

@Stateless
public class UserDAOImpl extends GenericDAOImpl<Integer, User> implements UserDAO, Serializable {

	public UserDAOImpl() {
		super(User.class);
	}

	
	
}

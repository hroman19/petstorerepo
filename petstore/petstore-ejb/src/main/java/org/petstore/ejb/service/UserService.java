package org.petstore.ejb.service;

import org.petstore.common.model.User;

public interface UserService extends GenericService<Integer, User> {

	public User getByEmail(String email);
	
}

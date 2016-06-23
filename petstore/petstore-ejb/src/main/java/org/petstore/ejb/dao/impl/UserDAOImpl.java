package org.petstore.ejb.dao.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.petstore.common.model.User;
import org.petstore.ejb.dao.UserDAO;

@Stateless
public class UserDAOImpl extends GenericDAOImpl<Integer, User> implements UserDAO, Serializable {

	private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM user_ps u " + "WHERE u.email = email";

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User getByEmail(String email) {
		try{
			Query query = entityManager.createNamedQuery("User.getUserByEmail");
			
			query.setParameter("email", email);
			return (User) query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
}

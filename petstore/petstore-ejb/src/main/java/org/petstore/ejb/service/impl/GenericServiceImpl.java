package org.petstore.ejb.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.petstore.ejb.dao.GenericDAO;
import org.petstore.ejb.service.GenericService;



@Stateful
public class GenericServiceImpl<PK, E extends Serializable> implements GenericService<PK, E> {
	
	private GenericDAO<PK, E> genericDAO;
	
	public GenericServiceImpl() {
	}

	public void setGenericDAO(GenericDAO<PK, E> genericDAO) {
		this.genericDAO = genericDAO;
	}

	public GenericDAO<PK, E> getGenericDAO() {
		return genericDAO;
	}
	
	
	@Override
	public E getById(PK id) {
		return genericDAO.getById(id);
	}

	@Override
	public List<E> getAll() {
		return genericDAO.getAll();
	}

	@Override
	public void add(E entity) {
		genericDAO.add(entity);
	}

	@Override
	public E update(E entity) {
		return genericDAO.update(entity);
	}

	@Override
	public void delete(E entity) {
		genericDAO.delete(entity);
	}

}

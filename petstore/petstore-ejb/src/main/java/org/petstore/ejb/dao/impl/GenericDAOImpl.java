package org.petstore.ejb.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.petstore.ejb.dao.GenericDAO;



@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GenericDAOImpl<PK, E extends Serializable> implements GenericDAO<PK, E>, Serializable {

	protected EntityManager entityManager = Persistence.createEntityManagerFactory("Petstore-PU").createEntityManager();
	protected Class<E> entityClass;

	public GenericDAOImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public E getById(PK id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<E> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteria = cb.createQuery(entityClass);
		Root<E> category = criteria.from(entityClass);
		criteria.select(category);
		return entityManager.createQuery(criteria).getResultList();
	}

	@Override
	public void add(E entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public E update(E entity) {
		entityManager.getTransaction().begin();
		E updatedEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		
		return updatedEntity;
	}

	@Override
	public void delete(E entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
}

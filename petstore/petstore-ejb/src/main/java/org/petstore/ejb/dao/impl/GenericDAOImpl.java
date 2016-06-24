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
		entityManager.clear();
		entityManager.getTransaction().begin();
		E e = entityManager.find(entityClass, id);
		entityManager.getTransaction().commit();
		return e;
	}

	@Override
	public List<E> getAll() {
		entityManager.clear();
		entityManager.getTransaction().begin();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteria = cb.createQuery(entityClass);
		Root<E> category = criteria.from(entityClass);
		criteria.select(category);
		List<E> list = entityManager.createQuery(criteria).getResultList();
		entityManager.getTransaction().commit();
		return list;
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
		E e = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return e;

	}

	@Override
	public void delete(E entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		entityManager.getTransaction().commit();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}

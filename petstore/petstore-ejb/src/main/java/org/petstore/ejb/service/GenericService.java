package org.petstore.ejb.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<PK, E extends Serializable> {
	E getById(PK id);

	List<E> getAll();

	void add(E entity);

	E update(E entity);

	void delete(E entity);
}

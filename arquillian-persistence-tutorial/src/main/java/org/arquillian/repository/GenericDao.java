package org.arquillian.repository;

import java.io.Serializable;
import java.util.List;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericDao<T, I extends Serializable> {

	@PersistenceContext
	private EntityManager em;

	private Class<T> classe;

	public GenericDao() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected T create(T entity) {
		em.persist(entity);
		em.flush();
		return entity;
	}

	protected T update(T entity) {
		em.merge(entity);
		em.flush();
		return entity;
	}
	
	protected T getBy(Serializable id) {
		return em.find(classe, id);
	}
	
	protected T delete(T entity) {
		em.remove(entity);
		em.flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Query query = em.createQuery("FROM " + classe.getName());
		return query.getResultList();
	}

}



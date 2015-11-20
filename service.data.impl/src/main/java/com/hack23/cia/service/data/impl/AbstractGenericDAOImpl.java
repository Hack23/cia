/*
 * Copyright 2010 James Pether Sörling
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/
package com.hack23.cia.service.data.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.CacheMode;

import com.hack23.cia.service.data.api.AbstractGenericDAO;
import com.hack23.cia.service.data.impl.util.LoadHelper;

/**
 * The Class AbstractGenericDAOImpl.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */


public abstract class AbstractGenericDAOImpl<T extends Serializable, ID extends Serializable>
implements AbstractGenericDAO<T, ID> {


	/** The criteria builder. */
	private CriteriaBuilder criteriaBuilder;

	/** The metamodel. */
	private Metamodel metamodel;

	/** The persistent class. */
	private final Class<T> persistentClass;

	/**
	 * Instantiates a new abstract generic dao impl.
	 *
	 * @param persistentClass
	 *            the persistent class
	 */
	protected AbstractGenericDAOImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Adds the cache hints.
	 *
	 * @param typedQuery
	 *            the typed query
	 * @param comment
	 *            the comment
	 */
	protected final void addCacheHints(final TypedQuery<?> typedQuery, final String comment) {
		typedQuery.setHint("org.hibernate.cacheMode", CacheMode.NORMAL);
		typedQuery.setHint("org.hibernate.cacheable",Boolean.TRUE);
		typedQuery.setHint("org.hibernate.comment", comment);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#delete(com.hack23.
	 * cia.model.common.api.ModelObject)
	 */
	@Override
	public final void delete(final T entity) {
		getEntityManager().remove(entity);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#findFirstByProperty
	 * (javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public final T findFirstByProperty(
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(getPersistentClass());
		final Root<T> root = criteriaQuery.from(getPersistentClass());
		criteriaQuery.select(root);
		final Predicate condition = criteriaBuilder.equal(root.get(property), value);
		criteriaQuery.where(condition);
		final TypedQuery<T> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findFirstByProperty");

		final List<T> resultList = typedQuery.getResultList();

		if (resultList.isEmpty()) {
			return null;
		} else {
			return LoadHelper.recursiveInitliaze(resultList.get(0));
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#findListByProperty
	 * (javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public final List<T> findListByProperty(
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(getPersistentClass());
		final Root<T> root = criteriaQuery.from(getPersistentClass());
		criteriaQuery.select(root);
		final Predicate condition = criteriaBuilder.equal(root.get(property), value);
		criteriaQuery.where(condition);
		final TypedQuery<T> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findListByProperty");
		return typedQuery.getResultList();
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public final List<T> findListByProperty(final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(persistentClass);
		final Root<T> root = criteriaQuery.from(persistentClass);
		criteriaQuery.select(root);

		final Object value=values[0];
		final SingularAttribute<T, ? extends Object> property = properties[0];
		Predicate condition;

		condition = QueryHelper.equalsIgnoreCaseIfStringPredicate(criteriaBuilder,root, value, property);

		if (values.length > 1) {
			for (int i = 1; i < properties.length; i++) {
				final SingularAttribute<T, ? extends Object> property2 = properties[i];
				final Object value2=values[i];
				final Predicate condition2 = QueryHelper.equalsIgnoreCaseIfStringPredicate(criteriaBuilder,root, value2, property2);

				condition = criteriaBuilder.and(condition,condition2);
			}
		}

		criteriaQuery.where(condition);

		final TypedQuery<T> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findListByProperty");

		return typedQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getAll()
	 */
	@Override
	public final List<T> getAll() {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(getPersistentClass());
		final Root<T> root = criteriaQuery.from(getPersistentClass());
		criteriaQuery.select(root);
		final TypedQuery<T> typedQuery = getEntityManager()
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "getAll");

		return typedQuery.getResultList();
	}

	/**
	 * Gets the criteria builder.
	 *
	 * @return the criteria builder
	 */
	public final CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	protected abstract EntityManager getEntityManager();

	/**
	 * Gets the metamodel.
	 *
	 * @return the metamodel
	 */
	protected final Metamodel getMetamodel() {
		return metamodel;
	}

	/**
	 * Gets the persistent class.
	 *
	 * @return the persistent class
	 */
	public final Class<T> getPersistentClass() {
		return this.persistentClass;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		this.criteriaBuilder = getEntityManager().getCriteriaBuilder();
		this.metamodel = getEntityManager().getMetamodel();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#load(java.io.Serializable
	 *)
	 */
	@Override
	public T load(final ID id) {
		return LoadHelper.recursiveInitliaze(getEntityManager().find(getPersistentClass(), id));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#save(com.hack23.cia
	 * .model.common.api.ModelObject)
	 */
	@Override
	public final T merge(final T entity) {
		return getEntityManager().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.api.AbstractGenericDAO#persist(java.util.
	 * List)
	 */
	@Override
	public final void persist(final List<T> list) {
		for (final T t : list) {
			getEntityManager().persist(t);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#persist(java.io.
	 * Serializable)
	 */
	@Override
	public final void persist(final T entity) {
		getEntityManager().persist(entity);
	}

}

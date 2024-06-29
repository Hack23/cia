/*
 * Copyright 2010-2024 James Pether Sörling
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

import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.CacheMode;
import org.springframework.stereotype.Repository;

import com.hack23.cia.service.data.api.DataViewer;
import com.hack23.cia.service.data.impl.util.LoadHelper;

/**
 * The Class DataViewerImpl.
 */

@Repository("DataViewer")
final class DataViewerImpl implements DataViewer {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/** The criteria builder. */
	private CriteriaBuilder criteriaBuilder;

	/**
	 * Instantiates a new data viewer impl.
	 */
	public DataViewerImpl() {
		super();
	}

	/**
	 * Adds the cache hints.
	 *
	 * @param typedQuery
	 *            the typed query
	 * @param comment
	 *            the comment
	 */
	private static void addCacheHints(final TypedQuery<?> typedQuery, final String comment) {
		typedQuery.setHint("org.hibernate.cacheMode", CacheMode.NORMAL);
		typedQuery.setHint("org.hibernate.cacheable", Boolean.TRUE);
		typedQuery.setHint("org.hibernate.comment", comment);
	}

	@Override
	public <T,V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property,final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {

		final CriteriaQuery<V> criteriaQuery = criteriaBuilder
				.createQuery(clazz2);
		final Root<V> root = criteriaQuery.from(clazz2);
		criteriaQuery.select(root);

		if (value instanceof String strValue) {
			final Expression<String> propertyObject = (Expression<String>) root.get(property2);
			final Predicate condition = criteriaBuilder.equal(criteriaBuilder.upper(propertyObject), strValue.toUpperCase(Locale.ENGLISH));
			criteriaQuery.where(condition);
		} else {
			final Predicate condition = criteriaBuilder.equal(root.get(property2), value);
			criteriaQuery.where(condition);
		}

		final TypedQuery<V> typedQuery = entityManager
				.createQuery(criteriaQuery);

		addCacheHints(typedQuery, "findByQueryProperty." + clazz.getSimpleName());


		final List<V> resultList = typedQuery.getResultList();

		if (!resultList.isEmpty()) {
			final List<T> findListByProperty = findListByProperty(clazz,property,resultList.get(0));
			if (!findListByProperty.isEmpty()) {
				return LoadHelper.recursiveInitialize(findListByProperty.get(0));
			}
		}

		return null;

	}

	@Override
	public <T> T findFirstByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		final List<T> resultList = findListByProperty(clazz,property,value);

		if (resultList.isEmpty()) {
			return null;
		} else {
			return LoadHelper.recursiveInitialize(resultList.get(0));
		}
	}


	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return findOrderedListByEmbeddedProperty(clazz,property,clazz2,property2,value,null);

	}

	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz, final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {

		return findOrderedListByProperty(clazz,null,values,properties);
	}


	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {

		return findOrderedListByProperty(clazz,property,value,null);
	}

	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		final Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);

		if (orderByProperty != null) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderByProperty)));
		}


		final Join<T, V> join = root.join(property);

		final Path<? extends Object> path = join.get(property2);

		final Predicate condition = criteriaBuilder.equal(path, value);

		criteriaQuery.where(condition);

		final TypedQuery<T> typedQuery = entityManager
				.createQuery(criteriaQuery);

		addCacheHints(typedQuery, "findListByEmbeddedProperty." + clazz.getSimpleName());


		return typedQuery.getResultList();
	}



	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<V, ? extends Object> orderByProperty) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		final Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);

		final Join<T, V> join = root.join(property);

		final Path<? extends Object> path = join.get(property2);

		if (orderByProperty != null) {
			criteriaQuery.orderBy(criteriaBuilder.desc(join.get(orderByProperty)));
		}

		final Predicate condition = criteriaBuilder.equal(path, value);

		criteriaQuery.where(condition);

		final TypedQuery<T> typedQuery = entityManager
				.createQuery(criteriaQuery);

		addCacheHints(typedQuery, "findListByEmbeddedProperty." + clazz.getSimpleName());


		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> findOrderedListByProperty(final Class<T> clazz, final SingularAttribute<T, ? extends Object> property,
			final Object value, final SingularAttribute<T, ? extends Object> orderByProperty) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		final Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);

		if (orderByProperty != null) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderByProperty)));
		}

		if (value instanceof String strValue) {
			final Expression<String> propertyObject = (Expression<String>) root.get(property);
			final Predicate condition = criteriaBuilder.equal(criteriaBuilder.upper(propertyObject),strValue.toUpperCase(Locale.ENGLISH));
			criteriaQuery.where(condition);

		} else {
			final Predicate condition = criteriaBuilder.equal(root.get(property), value);
			criteriaQuery.where(condition);

		}

		final TypedQuery<T> typedQuery = entityManager
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findListByProperty." + clazz.getSimpleName());

		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> findOrderedListByProperty(final Class<T> clazz, final SingularAttribute<T, ? extends Object> orderByProperty,
			final Object[] values, final SingularAttribute<T, ? extends Object>... properties) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		final Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);

		if (orderByProperty != null) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderByProperty)));
		}


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

		final TypedQuery<T> typedQuery = entityManager
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "findListByProperty." + clazz.getSimpleName());

		return typedQuery.getResultList();
	}

	@Override
	public <T> List<T> getAll(final Class<T> clazz) {
		return getAllOrderBy(clazz,null);
	}

	@Override
	public <T> List<T> getAllOrderBy(final Class<T> clazz, final SingularAttribute<T, ? extends Object> property) {
		return getInternalPageOrderBy(clazz, null, null, property);
	}


	@Override
	public <T> List<T> getPage(final Class<T> clazz, final int pageNr, final int resultPerPage) {
		return getPageOrderBy(clazz, pageNr, resultPerPage, null);
	}

	@Override
	public <T> List<T> getPageOrderBy(final Class<T> clazz, final int pageNr, final int resultPerPage,
			final SingularAttribute<T, ? extends Object> property) {
		return getInternalPageOrderBy(clazz, pageNr, resultPerPage, property);
	}

	private <T> List<T> getInternalPageOrderBy(final Class<T> clazz, final Integer pageNr,final Integer resultPerPage,final SingularAttribute<T, ? extends Object> property) {
		final CriteriaQuery<T> criteriaQuery = criteriaBuilder
				.createQuery(clazz);
		final Root<T> root = criteriaQuery.from(clazz);

		criteriaQuery.select(root);

		if (property != null) {
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(property)));
		}

		final TypedQuery<T> typedQuery = entityManager
				.createQuery(criteriaQuery);
		addCacheHints(typedQuery, "getAll." + clazz.getSimpleName());

		if (pageNr != null && resultPerPage != null) {
			typedQuery.setFirstResult((pageNr-1) * resultPerPage);
			typedQuery.setMaxResults(resultPerPage);

		}

		return typedQuery.getResultList();
	}

	@Override
	public <T> Long getSize(final Class<T> clazz) {
		final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));
		return entityManager.createQuery(countQuery).getSingleResult();
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		criteriaBuilder = entityManager.getCriteriaBuilder();
	}

	@Override
	public <T> T load(final Class<T> clazz,final Object id) {
		return LoadHelper.recursiveInitialize(entityManager.find(clazz, id));
	}


}

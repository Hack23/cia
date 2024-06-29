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

package com.hack23.cia.service.data.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

/**
 * The Interface AbstractGenericDAO.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public interface AbstractGenericDAO<T extends Serializable, I extends Serializable> {

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	void delete(T entity);

	/**
	 * Find first by property.
	 *
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the t
	 */
	T findFirstByProperty(SingularAttribute<T, ? extends Object> property, Object value);

	/**
	 * Find list by property.
	 *
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	List<T> findListByProperty(Object[] values, SingularAttribute<T, ? extends Object>... properties);

	/**
	 * Find list by property before date.
	 *
	 * @param beforeDate the before date
	 * @param dateField the date field
	 * @param values the values
	 * @param properties the properties
	 * @return the list
	 */
	List<T> findListByPropertyBeforeDate(Date beforeDate, SingularAttribute<T, Date> dateField, Object[] values,
			SingularAttribute<T, ? extends Object>... properties);

	/**
	 * Find list by property.
	 *
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<T> findListByProperty(SingularAttribute<T, ? extends Object> property, Object value);


	/**
	 * Find list by property in list.
	 *
	 * @param property
	 *            the property
	 * @param values
	 *            the values
	 * @return the list
	 */
	List<T> findListByPropertyInList(SingularAttribute<T, ? extends Object> property, Object[] values);

	/**
	 * Find list by embedded property.
	 *
	 * @param <V>
	 *            the value type
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @return the list
	 */
	<V> List<T> findListByEmbeddedProperty(SingularAttribute<T, V> property,Class<V> clazz2,SingularAttribute<V, ? extends Object> property2, Object value);

	/**
	 * Find ordered by property list by embedded property.
	 *
	 * @param <V>
	 *            the value type
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	<V> List<T> findOrderedByPropertyListByEmbeddedProperty(final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty);


	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<T> getAll();


	/**
	 * Gets the all order by.
	 *
	 * @param orderBy
	 *            the order by
	 * @return the all order by
	 */
	List<T> getAllOrderBy(final SingularAttribute<T, ? extends Object> orderBy);


	/**
	 * Gets the page.
	 *
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 * @return the page
	 */
	List<T> getPage(int pageNr,int resultPerPage);

	/**
	 * Gets the page order by.
	 *
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 * @param orderBy
	 *            the order by
	 * @return the page order by
	 */
	List<T> getPageOrderBy(int pageNr,int resultPerPage,final SingularAttribute<T, ? extends Object> orderBy);


	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	Long getSize();

	/**
	 * Load.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T load(I id);

	/**
	 * Merge.
	 *
	 * @param entity
	 *            the entity
	 * @return the t
	 */
	T merge(T entity);

	/**
	 * Persist.
	 *
	 * @param list
	 *            the list
	 */
	void persist(final List<T> list);

	/**
	 * Persist.
	 *
	 * @param entity
	 *            the entity
	 */
	void persist(T entity);

	/**
	 * Search.
	 *
	 * @param searchExpression
	 *            the search expression
	 * @param maxResults
	 *            the max results
	 * @param fields
	 *            the fields
	 * @return the list
	 */
	List<T> search(String searchExpression, Integer maxResults,String ...fields);

}

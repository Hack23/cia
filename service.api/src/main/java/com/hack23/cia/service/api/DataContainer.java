/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.api;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

/**
 * The Interface DataContainer.
 *
 * @param <T>
 *            the generic type
 * @param <I>
 *            the generic type
 */
public interface DataContainer<T extends Serializable, I extends Serializable> {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<T> getAll();

	/**
	 * Gets the all order by.
	 *
	 * @param property
	 *            the property
	 * @return the all order by
	 */
	List<T> getAllOrderBy(final SingularAttribute<T, ? extends Object> property);

	/**
	 * Gets the page.
	 *
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 * @return the page
	 */
	List<T> getPage(int pageNr, int resultPerPage);

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
	List<T> getPageOrderBy(int pageNr, int resultPerPage, final SingularAttribute<T, ? extends Object> orderBy);

	/**
	 * Gets the all by.
	 *
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the all by
	 */
	List<T> getAllBy(final SingularAttribute<T, ? extends Object> property, final Object value);

	/**
	 * Find list by property.
	 *
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	List<T> findListByProperty(final Object[] values, final SingularAttribute<T, ? extends Object>... properties);

	/**
	 * Find ordered list by property.
	 *
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	List<T> findOrderedListByProperty(final SingularAttribute<T, ? extends Object> property, final Object value,
			SingularAttribute<T, ? extends Object> orderByProperty);

	/**
	 * Find ordered list by property.
	 *
	 * @param orderByProperty
	 *            the order by property
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	List<T> findOrderedListByProperty(SingularAttribute<T, ? extends Object> orderByProperty, final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties);

	/**
	 * Find by query property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz2
	 * @param property2
	 *            the property2
	 * @param value
	 *            the value
	 * @return the t
	 */
	@SuppressWarnings("hiding")
	<T, V> T findByQueryProperty(Class<T> clazz, SingularAttribute<T, ? extends Object> property, Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value);

	/**
	 * Find list by embedded property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz2
	 * @param property2
	 *            the property2
	 * @param value
	 *            the value
	 * @return the list
	 */
	@SuppressWarnings("hiding")
	<T, V> List<T> findListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property, Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value);

	/**
	 * Find ordered list by embedded property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz2
	 * @param property2
	 *            the property2
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	@SuppressWarnings("hiding")
	<T, V> List<T> findOrderedListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property, Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<V, ? extends Object> orderByProperty);

	/**
	 * Find ordered by property list by embedded property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param clazz2
	 *            the clazz2
	 * @param property2
	 *            the property2
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	@SuppressWarnings("hiding")
	<T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<T, ? extends Object> orderByProperty);

	/**
	 * Load.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T load(I id);

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	Long getSize();

}

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
package com.hack23.cia.service.data.api;

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

/**
 * The Interface DataViewer.
 */
public interface DataViewer {

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
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @return the t
	 */
	<T,V> T findByQueryProperty(Class<T> clazz,
			SingularAttribute<T, ? extends Object> property,Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value);


	/**
	 * Find first by property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the t
	 */
	<T> T findFirstByProperty(Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value);

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
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @return the list
	 */
	<T,V> List<T> findListByEmbeddedProperty(Class<T> clazz,
			SingularAttribute<T, V> property,Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value);

	/**
	 * Find list by property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	<T> List<T> findListByProperty(Class<T> clazz, final Object[] values,final SingularAttribute<T, ? extends Object>... properties);


	/**
	 * Find list by property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the list
	 */
	<T> List<T> findListByProperty(Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value);



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
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	<T,V> List<T> findOrderedByPropertyListByEmbeddedProperty(Class<T> clazz,
			SingularAttribute<T, V> property,Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value,SingularAttribute<T, ? extends Object> orderByProperty);

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
	 *            the clazz 2
	 * @param property2
	 *            the property 2
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	<T,V> List<T> findOrderedListByEmbeddedProperty(Class<T> clazz,
			SingularAttribute<T, V> property,Class<V> clazz2,
			SingularAttribute<V, ? extends Object> property2, Object value,SingularAttribute<V, ? extends Object> orderByProperty);



	/**
	 * Find ordered list by property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @param orderByProperty
	 *            the order by property
	 * @return the list
	 */
	<T> List<T> findOrderedListByProperty(Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value,SingularAttribute<T, ? extends Object> orderByProperty);


	/**
	 * Find ordered list by property.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param orderByProperty
	 *            the order by property
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	<T> List<T> findOrderedListByProperty(Class<T> clazz, SingularAttribute<T, ? extends Object> orderByProperty,final Object[] values,final SingularAttribute<T, ? extends Object>... properties);


	/**
	 * Gets the all.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the all
	 */
	<T> List<T> getAll(Class<T> clazz);


	/**
	 * Gets the all order by.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param property
	 *            the property
	 * @return the all order by
	 */
	<T> List<T> getAllOrderBy(Class<T> clazz, SingularAttribute<T, ? extends Object> property);


	/**
	 * Gets the page.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 * @return the page
	 */
	<T> List<T> getPage(Class<T> clazz,int pageNr,int resultPerPage);

	/**
	 * Gets the page order by.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param pageNr
	 *            the page nr
	 * @param resultPerPage
	 *            the result per page
	 * @param property
	 *            the property
	 * @return the page order by
	 */
	<T> List<T> getPageOrderBy(Class<T> clazz,int pageNr,int resultPerPage,SingularAttribute<T, ? extends Object> property);


	/**
	 * Gets the size.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the size
	 */
	<T> Long getSize(final Class<T> clazz);

	/**
	 * Load.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @param id
	 *            the id
	 * @return the t
	 */
	<T> T load(Class<T> clazz,Object id);

}

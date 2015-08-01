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
 *	$Id: AbstractGenericDAO.java 6075 2015-05-20 22:43:45Z pether $
 *  $HeadURL: svn+ssh://svn.code.sf.net/p/cia/code/trunk/service.data.api/src/main/java/com/hack23/cia/service/data/api/AbstractGenericDAO.java $
 */

package com.hack23.cia.service.data.api;

import java.io.Serializable;
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
	 * @param property
	 *            the property
	 * @param value
	 *            the value
	 * @return the list
	 */
	List<T> findListByProperty(SingularAttribute<T, ? extends Object> property, Object value);


	/**
	 * Find list by property.
	 *
	 * @param values
	 *            the values
	 * @param properties
	 *            the properties
	 * @return the list
	 */
	List<T> findListByProperty(final Object[] values,final SingularAttribute<T, ? extends Object>... properties);

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<T> getAll();

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

}

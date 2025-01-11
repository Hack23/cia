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
package com.hack23.cia.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class GenericDataContainer.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
class GenericDataContainer<T extends Serializable, I extends Serializable> implements
DataContainer<T, I> {



	/** The data proxy. */
	private final DataViewer dataProxy;

	/** The clazz. */
	private final Class<T> clazz;

	/**
	 * Instantiates a new generic data container.
	 *
	 * @param clazz
	 *            the clazz
	 * @param dataViewer
	 *            the data viewer
	 */
	GenericDataContainer(final Class<T> clazz, final DataViewer dataViewer) {
		this.clazz = clazz;
		dataProxy = dataViewer;
	}

	@SuppressWarnings("hiding")
	@Override
	public final <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findByQueryProperty(clazz, property, clazz2, property2, value);
	}

	@SuppressWarnings("hiding")
	@Override
	public final <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findListByEmbeddedProperty(clazz, property, clazz2, property2, value);
	}



	@SuppressWarnings("unchecked")
	@Override
	public final List<T> findListByProperty(final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataProxy.findListByProperty(clazz, values, properties);
	}

	@SuppressWarnings("hiding")
	@Override
	public final <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedByPropertyListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}

	@SuppressWarnings("hiding")
	@Override
	public final <T, V> List<T> findOrderedListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<V, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}

	@Override
	public final List<T> findOrderedListByProperty(final SingularAttribute<T, ? extends Object> property, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedListByProperty(clazz,property,value,orderByProperty);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<T> findOrderedListByProperty(final SingularAttribute<T, ? extends Object> orderByProperty, final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataProxy.findOrderedListByProperty(clazz,orderByProperty,values, properties);
	}

	@Override
	public final List<T> getAll() {
		return dataProxy.getAll(clazz);
	}

	@Override
	public final List<T> getAllBy(
			final SingularAttribute<T, ? extends Object> property,
			final Object value) {
		return dataProxy.findListByProperty(clazz, property, value);
	}

	@Override
	public final List<T> getAllOrderBy(final SingularAttribute<T, ? extends Object> property) {
		return dataProxy.getAllOrderBy(clazz,property);
	}

	@Override
	public T load(final I id) {
		return dataProxy.load(clazz, id);

	}

	@Override
	public final List<T> getPage(final int pageNr, final int resultPerPage) {
		return dataProxy.getPage(clazz,pageNr, resultPerPage);
	}

	@Override
	public final List<T> getPageOrderBy(final int pageNr, final int resultPerPage, final SingularAttribute<T, ? extends Object> orderBy) {
		return dataProxy.getPageOrderBy(clazz,pageNr, resultPerPage, orderBy);
	}

	@Override
	public final Long getSize() {
		return dataProxy.getSize(clazz);
	}

}
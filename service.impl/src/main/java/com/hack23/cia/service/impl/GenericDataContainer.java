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
final class GenericDataContainer<T extends Serializable, ID extends Serializable> implements
DataContainer<T, ID> {



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
	public GenericDataContainer(final Class<T> clazz, final DataViewer dataViewer) {
		this.clazz = clazz;
		dataProxy = dataViewer;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<T> getAll() {
		return dataProxy.getAll(clazz);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllOrderBy(javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public List<T> getAllOrderBy(SingularAttribute<T, ? extends Object> property) {
		return dataProxy.getAllOrderBy(clazz,property);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<T> getAllBy(
			final SingularAttribute<T, ? extends Object> property,
			final Object value) {
		return dataProxy.findListByProperty(clazz, property, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#load(java.io.Serializable)
	 */
	@Override
	public T load(final ID id) {
		return dataProxy.load(clazz, id);

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<T> findListByProperty(final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataProxy.findListByProperty(clazz, values, properties);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findByQueryProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findByQueryProperty(clazz, property, clazz2, property2, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findListByEmbeddedProperty(clazz, property, clazz2, property2, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByProperty(javax.persistence.metamodel.SingularAttribute, java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<T> findOrderedListByProperty(SingularAttribute<T, ? extends Object> orderByProperty, Object[] values,
			SingularAttribute<T, ? extends Object>... properties) {
		return dataProxy.findOrderedListByProperty(clazz,orderByProperty,values, properties);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<V, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedByPropertyListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedByPropertyListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByProperty(javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public List<T> findOrderedListByProperty(SingularAttribute<T, ? extends Object> property, Object value,
			SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataProxy.findOrderedListByProperty(clazz,property,value,orderByProperty);
	}

}
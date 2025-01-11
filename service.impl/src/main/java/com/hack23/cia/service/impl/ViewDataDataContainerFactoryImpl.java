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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class ViewDataDataContainerFactoryImpl.
 */
@Component("ViewDataDataContainerFactory")
@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
final class ViewDataDataContainerFactoryImpl implements DataViewer,ViewDataDataContainerFactory {

	/** The data viewer. */
	@Autowired
	@Qualifier("DataViewer")
	private DataViewer dataViewer;


	/**
	 * Instantiates a new view data data container factory impl.
	 */
	public ViewDataDataContainerFactoryImpl() {
		super();
	}


	@Override
	public <T extends Serializable,I extends Serializable> DataContainer<T,I> createDataContainer(final Class<T> clazz) {
		return new GenericDataContainer<>(clazz,this);
	}


	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataViewer.findByQueryProperty(clazz, property, clazz2, property2, value);
	}


	@Override
	public <T> T findFirstByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		return dataViewer.findFirstByProperty(clazz, property, value);
	}


	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataViewer.findListByEmbeddedProperty(clazz, property, clazz2, property2, value);
	}



	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz, final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataViewer.findListByProperty(clazz, values, properties);
	}



	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		return dataViewer.findListByProperty(clazz, property, value);
	}



	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedByPropertyListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}



	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<V, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}



	@Override
	public <T> List<T> findOrderedListByProperty(final Class<T> clazz, final SingularAttribute<T, ? extends Object> property,
			final Object value, final SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedListByProperty(clazz,property,value,orderByProperty);
	}



	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findOrderedListByProperty(final Class<T> clazz, final SingularAttribute<T, ? extends Object> orderByProperty,
			final Object[] values, final SingularAttribute<T, ? extends Object>... properties) {
		return dataViewer.findOrderedListByProperty(clazz,orderByProperty,values,properties);
	}


	@Override
	public <T> List<T> getAll(final Class<T> clazz) {
		return dataViewer.getAll(clazz);
	}


	@Override
	public <T> List<T> getAllOrderBy(final Class<T> clazz, final SingularAttribute<T, ? extends Object> property) {
		return dataViewer.getAllOrderBy(clazz, property);
	}


	@Override
	public <T> T load(final Class<T> clazz, final Object id) {
		return dataViewer.load(clazz, id);
	}


	@Override
	public <T> List<T> getPage(final Class<T> clazz, final int pageNr, final int resultPerPage) {
		return dataViewer.getPage(clazz, pageNr, resultPerPage);
	}


	@Override
	public <T> List<T> getPageOrderBy(final Class<T> clazz, final int pageNr, final int resultPerPage,
			final SingularAttribute<T, ? extends Object> property) {
		return dataViewer.getPageOrderBy(clazz, pageNr, resultPerPage, property);
	}


	@Override
	public <T> Long getSize(final Class<T> clazz) {
		return dataViewer.getSize(clazz);
	}

}

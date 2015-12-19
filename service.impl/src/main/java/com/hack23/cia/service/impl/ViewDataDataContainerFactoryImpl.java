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
@Component
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class ViewDataDataContainerFactoryImpl implements DataViewer,ViewDataDataContainerFactory {

	/** The data viewer. */
	@Autowired
	@Qualifier("DataViewer")
	private DataViewer dataViewer;



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.impl.ViewDataDataContainerFactory#createDataContainer(java.lang.Class)
	 */
	@Override
	public <T extends Serializable,ID  extends Serializable> DataContainer<T,ID> createDataContainer(final Class<T> clazz) {
		return new GenericDataContainer<T, ID>(clazz,this);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findFirstByProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T> T findFirstByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		return dataViewer.findFirstByProperty(clazz, property, value);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findListByProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Object value) {
		return dataViewer.findListByProperty(clazz, property, value);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#getAll(java.lang.Class)
	 */
	@Override
	public <T> List<T> getAll(final Class<T> clazz) {
		return dataViewer.getAll(clazz);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#load(java.lang.Class, java.lang.Object)
	 */
	@Override
	public <T> T load(final Class<T> clazz, final Object id) {
		return dataViewer.load(clazz, id);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findListByProperty(java.lang.Class, java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public <T> List<T> findListByProperty(final Class<T> clazz, final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataViewer.findListByProperty(clazz, values, properties);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findByQueryProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataViewer.findByQueryProperty(clazz, property, clazz2, property2, value);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataViewer.findListByEmbeddedProperty(clazz, property, clazz2, property2, value);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#getAllOrderBy(java.lang.Class, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T> List<T> getAllOrderBy(Class<T> clazz, SingularAttribute<T, ? extends Object> property) {
		return dataViewer.getAllOrderBy(clazz, property);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findOrderedListByProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T> List<T> findOrderedListByProperty(Class<T> clazz, SingularAttribute<T, ? extends Object> property,
			Object value, SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedListByProperty(clazz,property,value,orderByProperty);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findOrderedListByProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public <T> List<T> findOrderedListByProperty(Class<T> clazz, SingularAttribute<T, ? extends Object> orderByProperty,
			Object[] values, SingularAttribute<T, ? extends Object>... properties) {
		return dataViewer.findOrderedListByProperty(clazz,orderByProperty,values,properties);
	}



	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.DataViewer#findOrderedListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<V, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}



	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<T, ? extends Object> orderByProperty) {
		return dataViewer.findOrderedByPropertyListByEmbeddedProperty(clazz,property,clazz2,property2,value,orderByProperty);
	}


}

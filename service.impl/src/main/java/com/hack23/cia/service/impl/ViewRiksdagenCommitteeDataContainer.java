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

import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.ViewRiksdagenCommitteeDAO;

/**
 * The Class ViewRiksdagenCommitteeDataContainer.
 */
@Component(value="ViewRiksdagenCommitteeDataContainer")
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class ViewRiksdagenCommitteeDataContainer implements DataContainer<ViewRiksdagenCommittee,String>{

	/** The view riksdagen committee dao. */
	@Autowired
	private ViewRiksdagenCommitteeDAO viewRiksdagenCommitteeDAO;

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<ViewRiksdagenCommittee> getAll() {
		return viewRiksdagenCommitteeDAO.getAll();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#load()
	 */
	@Override
	public ViewRiksdagenCommittee load(final String id) {
		for (final ViewRiksdagenCommittee riksdagenCommittee : viewRiksdagenCommitteeDAO.getAll()) {
			if (id.equals(riksdagenCommittee.getEmbeddedId().getOrgCode())) {
				return riksdagenCommittee;
			}

		}

		return null;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<ViewRiksdagenCommittee> getAllBy(
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property,
			final Object value) {
		return viewRiksdagenCommitteeDAO.findListByProperty(property, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<ViewRiksdagenCommittee> findListByProperty(
			final Object[] values,
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object>... properties) {
		return viewRiksdagenCommitteeDAO.findListByProperty(values, properties);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findByQueryProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return null;
	}

	@Override
	public List<ViewRiksdagenCommittee> getAllOrderBy(
			SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property) {
		return viewRiksdagenCommitteeDAO.getAllOrderBy(property);
	}

	@Override
	public List<ViewRiksdagenCommittee> findOrderedListByProperty(
			SingularAttribute<ViewRiksdagenCommittee, ? extends Object> orderByProperty, Object[] values,
			SingularAttribute<ViewRiksdagenCommittee, ? extends Object>... properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<V, ? extends Object> orderByProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(Class<T> clazz, SingularAttribute<T, V> property,
			Class<V> clazz2, SingularAttribute<V, ? extends Object> property2, Object value,
			SingularAttribute<T, ? extends Object> orderByProperty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewRiksdagenCommittee> findOrderedListByProperty(
			SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property, Object value,
			SingularAttribute<ViewRiksdagenCommittee, ? extends Object> orderByProperty) {
		// TODO Auto-generated method stub
		return null;
	}
}

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
@Component("ViewRiksdagenCommitteeDataContainer")
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class ViewRiksdagenCommitteeDataContainer implements DataContainer<ViewRiksdagenCommittee,String>{

	/** The view riksdagen committee dao. */
	@Autowired
	private ViewRiksdagenCommitteeDAO viewRiksdagenCommitteeDAO;

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<ViewRiksdagenCommittee> getAll() {
		return viewRiksdagenCommitteeDAO.getAll();
	}

	/** {@inheritDoc}
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

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<ViewRiksdagenCommittee> getAllBy(
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property,
			final Object value) {
		return viewRiksdagenCommitteeDAO.findListByProperty(property, value);
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<ViewRiksdagenCommittee> findListByProperty(
			final Object[] values,
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object>... properties) {
		return viewRiksdagenCommitteeDAO.findListByProperty(values, properties);
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findByQueryProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return null;
	}

	/** {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property, final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return null;
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#getAllOrderBy(javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public List<ViewRiksdagenCommittee> getAllOrderBy(
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property) {
		return viewRiksdagenCommitteeDAO.getAllOrderBy(property);
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByProperty(javax.persistence.metamodel.SingularAttribute, java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<ViewRiksdagenCommittee> findOrderedListByProperty(
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> orderByProperty, final Object[] values,
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object>... properties) {
		return null;
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T, V> List<T> findOrderedListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<V, ? extends Object> orderByProperty) {
		return null;
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedByPropertyListByEmbeddedProperty(java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Class, javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public <T, V> List<T> findOrderedByPropertyListByEmbeddedProperty(final Class<T> clazz, final SingularAttribute<T, V> property,
			final Class<V> clazz2, final SingularAttribute<V, ? extends Object> property2, final Object value,
			final SingularAttribute<T, ? extends Object> orderByProperty) {
		return null;
	}

	/* {@inheritDoc}
	 * @see com.hack23.cia.service.api.DataContainer#findOrderedListByProperty(javax.persistence.metamodel.SingularAttribute, java.lang.Object, javax.persistence.metamodel.SingularAttribute)
	 */
	@Override
	public List<ViewRiksdagenCommittee> findOrderedListByProperty(
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> property, final Object value,
			final SingularAttribute<ViewRiksdagenCommittee, ? extends Object> orderByProperty) {
		return null;
	}
}

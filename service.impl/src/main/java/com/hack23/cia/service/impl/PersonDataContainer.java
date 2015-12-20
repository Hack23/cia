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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.PersonDataDAO;

/**
 * The Class PersonDataContainer.
 */
@Component("PersonDataContainer")
@Transactional(propagation=Propagation.REQUIRED)
@Secured({"ROLE_ANONYMOUS","ROLE_USER", "ROLE_ADMIN" })
public final class PersonDataContainer implements DataContainer<PersonData,String>{

	/** The person data dao. */
	@Autowired
	private PersonDataDAO personDataDAO;

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<PersonData> getAll() {
		final List<PersonData> all = personDataDAO.getAll();
		Hibernate.initialize(all);

		for (final PersonData load: all) {
			Hibernate.initialize(load.getPersonAssignmentData());
			Hibernate.initialize(load.getPersonAssignmentData().getAssignmentList());
			Hibernate.initialize(load.getPersonDetailData());
			Hibernate.initialize(load.getPersonDetailData().getDetailList());
		}

		return all;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#load()
	 */
	@Override
	public PersonData load(final String id) {
		final PersonData load = personDataDAO.load(id);
		Hibernate.initialize(load.getPersonAssignmentData());
		Hibernate.initialize(load.getPersonAssignmentData().getAssignmentList());
		Hibernate.initialize(load.getPersonDetailData());
		Hibernate.initialize(load.getPersonDetailData().getDetailList());

		return load;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<PersonData> getAllBy(
			final SingularAttribute<PersonData, ? extends Object> property,
			final Object value) {
		return personDataDAO.findListByProperty(property, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<PersonData> findListByProperty(final Object[] values,
			final SingularAttribute<PersonData, ? extends Object>... properties) {
		return personDataDAO.findListByProperty(values, properties);
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
		return new ArrayList<T>();
	}

	@Override
	public List<PersonData> getAllOrderBy(SingularAttribute<PersonData, ? extends Object> property) {
		return personDataDAO.getAllOrderBy(property);
	}

	@Override
	public List<PersonData> findOrderedListByProperty(SingularAttribute<PersonData, ? extends Object> orderByProperty,
			Object[] values, SingularAttribute<PersonData, ? extends Object>... properties) {
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
	public List<PersonData> findOrderedListByProperty(SingularAttribute<PersonData, ? extends Object> property,
			Object value, SingularAttribute<PersonData, ? extends Object> orderByProperty) {
		// TODO Auto-generated method stub
		return null;
	}

}

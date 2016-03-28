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
package com.hack23.cia.service.data.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.external.riksdagen.person.impl.PersonData;
import com.hack23.cia.model.external.riksdagen.person.impl.PersonData_;
import com.hack23.cia.service.data.api.PersonDataDAO;

/**
 * The Class PersonDataDAOImpl.
 */
@Repository("PersonDataDAO")
public final class PersonDataDAOImpl extends AbstractGenericDAOImpl<PersonData, String> implements PersonDataDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new person data dao impl.
	 */
	public PersonDataDAOImpl() {
		super(PersonData.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<String> getIdList() {
		final CriteriaQuery<String> criteria = getCriteriaBuilder().createQuery(String.class);
		final Root<PersonData> root = criteria.from(PersonData.class);
		criteria.select(root.get(PersonData_.id));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	@Override
	public Long getSize() {
		return (long) getIdList().size();
	}

}

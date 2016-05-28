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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.system.impl.LanguageData;
import com.hack23.cia.model.internal.application.system.impl.LanguageData_;
import com.hack23.cia.service.data.api.LanguageDataDAO;

/**
 * The Class LanguageDataDAOImpl.
 */
@Repository("LanguageDataDAO")
public final class LanguageDataDAOImpl extends
AbstractGenericDAOImpl<LanguageData, Long> implements
LanguageDataDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new language data dao impl.
	 */
	public LanguageDataDAOImpl() {
		super(LanguageData.class);
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.impl.AbstractGenericDAOImpl#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}


	/**
	 * Gets the id list.
	 *
	 * @return the id list
	 */
	private List<Long> getIdList() {
		final CriteriaQuery<Long> criteria = getCriteriaBuilder().createQuery(
				Long.class);
		final Root<LanguageData> root = criteria
				.from(LanguageData.class);
		criteria.select(root.get(LanguageData_.hjid));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {
		return (long) getIdList().size();
	}
}

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee_;
import com.hack23.cia.service.data.api.ViewRiksdagenCommitteeDAO;

/**
 * The Class ViewRiksdagenCommitteeDAOImpl.
 */
@Repository("ViewRiksdagenCommitteeDAO")
public final class ViewRiksdagenCommitteeDAOImpl
		extends
		AbstractGenericDAOImpl<ViewRiksdagenCommittee, RiksdagenCommitteeEmbeddedId>
		implements ViewRiksdagenCommitteeDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new view riksdagen committee dao impl.
	 */
	public ViewRiksdagenCommitteeDAOImpl() {
		super(ViewRiksdagenCommittee.class);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.service.data.impl.AbstractRiksdagenDAOImpl#getEntityManager
	 * ()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.VoteDataDAO#getIdList()
	 */
	@Override
	public List<RiksdagenCommitteeEmbeddedId> getIdList() {
		final CriteriaQuery<RiksdagenCommitteeEmbeddedId> criteria = getCriteriaBuilder()
				.createQuery(RiksdagenCommitteeEmbeddedId.class);
		final Root<ViewRiksdagenCommittee> root = criteria
				.from(ViewRiksdagenCommittee.class);
		criteria.select(getCriteriaBuilder().construct(
				RiksdagenCommitteeEmbeddedId.class,
				root.get(ViewRiksdagenCommittee_.embeddedId)));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {

		final CriteriaBuilder cb = getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(ViewRiksdagenCommittee.class)));

		return getEntityManager().createQuery(cq).getSingleResult();

	}

}

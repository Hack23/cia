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

import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteDataEmbeddedId;
import com.hack23.cia.model.external.riksdagen.votering.impl.VoteData_;
import com.hack23.cia.service.data.api.VoteDataDAO;

/**
 * The Class VoteDataDAOImpl.
 */
@Repository("VoteDataDAO")
public final class VoteDataDAOImpl extends
AbstractGenericDAOImpl<VoteData, VoteDataEmbeddedId> implements VoteDataDAO {

	/** The entity manager. */
	@PersistenceContext(name = "ciaPersistenceUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new vote data dao impl.
	 */
	public VoteDataDAOImpl() {
		super(VoteData.class);
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.VoteDataDAO#getBallotIdList()
	 */
	@Override
	public List<VoteDataEmbeddedId> getBallotIdList() {
		final CriteriaQuery<VoteDataEmbeddedId> criteria = getCriteriaBuilder().createQuery(
				VoteDataEmbeddedId.class);
		final Root<VoteData> root = criteria.from(VoteData.class);
		criteria.select(root.get(VoteData_.embeddedId));
		criteria.distinct(true);
		return getEntityManager().createQuery(criteria).getResultList();
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
	public List<VoteDataEmbeddedId> getIdList() {
		final CriteriaQuery<VoteDataEmbeddedId> criteria = getCriteriaBuilder().createQuery(
				VoteDataEmbeddedId.class);
		final Root<VoteData> root = criteria.from(VoteData.class);
		criteria.select(getCriteriaBuilder().construct(VoteDataEmbeddedId.class,
				root.get(VoteData_.embeddedId)));
		return getEntityManager().createQuery(criteria).getResultList();
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.service.data.api.AbstractGenericDAO#getSize()
	 */
	@Override
	public Long getSize() {
		//		final CriteriaQuery<VoteDataEmbeddedId> criteria = getCriteriaBuilder().createQuery(
		//				VoteDataEmbeddedId.class);
		//		final Root<VoteData> root = criteria.from(VoteData.class);
		//		criteria.select(root.get(VoteData_.embeddedId));
		//		criteria.distinct(true);
		//		return (long) getEntityManager().createQuery(criteria).getResultList().size();




		final CriteriaBuilder cb = getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(VoteData.class)));

		return getEntityManager().createQuery(cq).getSingleResult();


	}


}

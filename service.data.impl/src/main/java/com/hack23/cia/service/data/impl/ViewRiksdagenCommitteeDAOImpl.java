/*
 * Copyright 2010-2024 James Pether Sörling
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
final class ViewRiksdagenCommitteeDAOImpl
		extends
		AbstractGenericDAOImpl<ViewRiksdagenCommittee, RiksdagenCommitteeEmbeddedId>
		implements ViewRiksdagenCommitteeDAO {

	/**
	 * Instantiates a new view riksdagen committee dao impl.
	 */
	public ViewRiksdagenCommitteeDAOImpl() {
		super(ViewRiksdagenCommittee.class);
	}

	@Override
	public List<RiksdagenCommitteeEmbeddedId> getIdList() {
		final CriteriaQuery<RiksdagenCommitteeEmbeddedId> criteria = getCriteriaBuilder()
				.createQuery(RiksdagenCommitteeEmbeddedId.class);
		final Root<ViewRiksdagenCommittee> root = criteria
				.from(ViewRiksdagenCommittee.class);
		criteria.select(root.get(ViewRiksdagenCommittee_.embeddedId));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}

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
package com.hack23.cia.service.data.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewMinistryDecisionImpact;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewMinistryDecisionImpactEmbeddedId;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewMinistryDecisionImpact_;
import com.hack23.cia.service.data.api.ViewMinistryDecisionImpactDAO;

/**
 * The Class ViewMinistryDecisionImpactDAOImpl.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@Repository("ViewMinistryDecisionImpactDAO")
final class ViewMinistryDecisionImpactDAOImpl
		extends
		AbstractGenericDAOImpl<ViewMinistryDecisionImpact, ViewMinistryDecisionImpactEmbeddedId>
		implements ViewMinistryDecisionImpactDAO {

	/**
	 * Instantiates a new view ministry decision impact dao impl.
	 */
	public ViewMinistryDecisionImpactDAOImpl() {
		super(ViewMinistryDecisionImpact.class);
	}

	@Override
	public List<ViewMinistryDecisionImpactEmbeddedId> getIdList() {
		final CriteriaQuery<ViewMinistryDecisionImpactEmbeddedId> criteria = getCriteriaBuilder()
				.createQuery(ViewMinistryDecisionImpactEmbeddedId.class);
		final Root<ViewMinistryDecisionImpact> root = criteria
				.from(ViewMinistryDecisionImpact.class);
		criteria.select(root.get(ViewMinistryDecisionImpact_.embeddedId));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}

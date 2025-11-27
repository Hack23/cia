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

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianDecisionPattern;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianDecisionPatternEmbeddedId;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianDecisionPattern_;
import com.hack23.cia.service.data.api.ViewRiksdagenPoliticianDecisionPatternDAO;

/**
 * The Class ViewRiksdagenPoliticianDecisionPatternDAOImpl.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@Repository("ViewRiksdagenPoliticianDecisionPatternDAO")
final class ViewRiksdagenPoliticianDecisionPatternDAOImpl
		extends
		AbstractGenericDAOImpl<ViewRiksdagenPoliticianDecisionPattern, ViewRiksdagenPoliticianDecisionPatternEmbeddedId>
		implements ViewRiksdagenPoliticianDecisionPatternDAO {

	/**
	 * Instantiates a new view riksdagen politician decision pattern dao impl.
	 */
	public ViewRiksdagenPoliticianDecisionPatternDAOImpl() {
		super(ViewRiksdagenPoliticianDecisionPattern.class);
	}

	@Override
	public List<ViewRiksdagenPoliticianDecisionPatternEmbeddedId> getIdList() {
		final CriteriaQuery<ViewRiksdagenPoliticianDecisionPatternEmbeddedId> criteria = getCriteriaBuilder()
				.createQuery(ViewRiksdagenPoliticianDecisionPatternEmbeddedId.class);
		final Root<ViewRiksdagenPoliticianDecisionPattern> root = criteria
				.from(ViewRiksdagenPoliticianDecisionPattern.class);
		criteria.select(root.get(ViewRiksdagenPoliticianDecisionPattern_.embeddedId));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}

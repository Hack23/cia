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

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrix;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenCoalitionAlignmentMatrix_;
import com.hack23.cia.service.data.api.ViewRiksdagenCoalitionAlignmentMatrixDAO;

/**
 * The Class ViewRiksdagenCoalitionAlignmentMatrixDAOImpl.
 *
 * @author intelligence-operative
 * @since v1.29 (Intelligence Operations Enhancement)
 */
@Repository("ViewRiksdagenCoalitionAlignmentMatrixDAO")
final class ViewRiksdagenCoalitionAlignmentMatrixDAOImpl
		extends
		AbstractGenericDAOImpl<ViewRiksdagenCoalitionAlignmentMatrix, ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId>
		implements ViewRiksdagenCoalitionAlignmentMatrixDAO {

	/**
	 * Instantiates a new view riksdagen coalition alignment matrix dao impl.
	 */
	public ViewRiksdagenCoalitionAlignmentMatrixDAOImpl() {
		super(ViewRiksdagenCoalitionAlignmentMatrix.class);
	}

	@Override
	public List<ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId> getIdList() {
		final CriteriaQuery<ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId> criteria = getCriteriaBuilder()
				.createQuery(ViewRiksdagenCoalitionAlignmentMatrixEmbeddedId.class);
		final Root<ViewRiksdagenCoalitionAlignmentMatrix> root = criteria
				.from(ViewRiksdagenCoalitionAlignmentMatrix.class);
		criteria.select(root.get(ViewRiksdagenCoalitionAlignmentMatrix_.embeddedId));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}

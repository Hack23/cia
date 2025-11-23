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

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hack23.cia.model.internal.application.data.impl.ViewDecisionTemporalTrends;
import com.hack23.cia.model.internal.application.data.impl.ViewDecisionTemporalTrends_;
import com.hack23.cia.service.data.api.ViewDecisionTemporalTrendsDAO;

/**
 * The Class ViewDecisionTemporalTrendsDAOImpl.
 * 
 * @author intelligence-operative
 * @since v1.35 (Decision Intelligence)
 */
@Repository("ViewDecisionTemporalTrendsDAO")
final class ViewDecisionTemporalTrendsDAOImpl
		extends
		AbstractGenericDAOImpl<ViewDecisionTemporalTrends, Date>
		implements ViewDecisionTemporalTrendsDAO {

	/**
	 * Instantiates a new view decision temporal trends dao impl.
	 */
	public ViewDecisionTemporalTrendsDAOImpl() {
		super(ViewDecisionTemporalTrends.class);
	}

	@Override
	public List<Date> getIdList() {
		final CriteriaQuery<Date> criteria = getCriteriaBuilder()
				.createQuery(Date.class);
		final Root<ViewDecisionTemporalTrends> root = criteria
				.from(ViewDecisionTemporalTrends.class);
		criteria.select(root.get(ViewDecisionTemporalTrends_.decisionDay));
		return getEntityManager().createQuery(criteria).getResultList();
	}

}

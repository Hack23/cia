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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPoliticianEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;

/**
 * The Class ViewRiksdagenVoteDataBallotPoliticianSummaryChartDataManagerImpl.
 */
@Service
public final class ViewRiksdagenVoteDataBallotPoliticianSummaryChartDataManagerImpl
		implements GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummary> {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new view riksdagen vote data ballot politician summary
	 * chart data manager impl.
	 */
	public ViewRiksdagenVoteDataBallotPoliticianSummaryChartDataManagerImpl() {
		super();
	}

	@Override
	public List<ViewRiksdagenVoteDataBallotPoliticianSummary> findByValue(final String id) {
		final DataContainer<ViewRiksdagenVoteDataBallotPoliticianSummary, RiksdagenVoteDataBallotPoliticianEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenVoteDataBallotPoliticianSummary.class);

		return politicianBallotSummaryDailyDataContainer.findOrderedByPropertyListByEmbeddedProperty(
				ViewRiksdagenVoteDataBallotPoliticianSummary.class,
				ViewRiksdagenVoteDataBallotPoliticianSummary_.embeddedId,
				RiksdagenVoteDataBallotPoliticianEmbeddedId.class,
				RiksdagenVoteDataBallotPoliticianEmbeddedId_.intressentId, id,
				ViewRiksdagenVoteDataBallotPoliticianSummary_.voteDate);

	}

}

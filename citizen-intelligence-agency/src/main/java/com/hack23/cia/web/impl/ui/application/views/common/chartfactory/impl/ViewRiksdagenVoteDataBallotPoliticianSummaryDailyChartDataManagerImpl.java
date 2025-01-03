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

import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPoliticianPeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPoliticianPeriodSummaryEmbeddedId_;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.chartfactory.api.GenericChartDataManager;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class ViewRiksdagenVoteDataBallotPoliticianSummaryDailyChartDataManagerImpl
		implements GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new view riksdagen vote data ballot politician summary
	 * daily chart data manager impl.
	 */
	public ViewRiksdagenVoteDataBallotPoliticianSummaryDailyChartDataManagerImpl() {
		super();
	}

	/**
	 * Find by value.
	 *
	 * @param value the value
	 * @return the list
	 */
	@Override
	public List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> findByValue(final String value) {
		final DataContainer<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily, RiksdagenVoteDataBallotPoliticianPeriodSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewRiksdagenVoteDataBallotPoliticianSummaryDaily.class);

		return politicianBallotSummaryDailyDataContainer.findListByEmbeddedProperty(
				ViewRiksdagenVoteDataBallotPoliticianSummaryDaily.class,
				ViewRiksdagenVoteDataBallotPoliticianSummaryDaily_.embeddedId,
				RiksdagenVoteDataBallotPoliticianPeriodSummaryEmbeddedId.class,
				RiksdagenVoteDataBallotPoliticianPeriodSummaryEmbeddedId_.intressentId, value);
	}

}

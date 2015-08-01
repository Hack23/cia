/*
 * Copyright 2014 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.common.chartfactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionSummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
public final class ViewRiksdagenCommitteeBallotDecisionSummaryChartDataManagerImpl implements GenericChartDataManager<ViewRiksdagenCommitteeBallotDecisionSummary> {

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private ApplicationManager applicationManager;

	/** The data map. */
	private Map<String, List<ViewRiksdagenCommitteeBallotDecisionSummary>> dataMap;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getDataMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeBallotDecisionSummary>> getDataMap() {
		initDataMap();
		return dataMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * findByValue(java.lang.String)
	 */
	@Override
	public List<ViewRiksdagenCommitteeBallotDecisionSummary> findByValue(
			final String value) {
		initDataMap();
		return dataMap.get(value);
	}

	/**
	 * Inits the data map.
	 */
	private void initDataMap() {
		if (dataMap == null) {
			final DataContainer<ViewRiksdagenCommitteeBallotDecisionSummary, ViewRiksdagenCommitteeBallotDecisionEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionSummary.class);

			dataMap = committeeBallotDecisionPartyDataContainer.getAll()
					.parallelStream().filter(t -> t != null)
					.collect(Collectors.groupingBy(t -> t.getOrg()));
		}
	}

}

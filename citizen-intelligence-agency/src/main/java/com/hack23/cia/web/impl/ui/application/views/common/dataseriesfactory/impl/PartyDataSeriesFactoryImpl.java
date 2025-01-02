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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;

import java.util.Optional;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary_;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.PartyDataSeriesFactory;

/**
 * The Class DataSeriesFactoryImpl.
 */
@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
public final class PartyDataSeriesFactoryImpl implements PartyDataSeriesFactory {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new party data series factory impl.
	 */
	public PartyDataSeriesFactoryImpl() {
		super();
	}

	@Override
	public DataSeries createChartTimeSeriesCurrentCommitteeByParty() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalActiveCommittee());
			}
		}
		return dataSeries;
	}

	@Override
	public DataSeries createChartTimeSeriesCurrentGovernmentByParty() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalActiveGovernment());
			}
		}
		return dataSeries;
	}

	@Override
	public DataSeries createPartyChartTimeSeriesAll() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			dataSeries = dataSeries.newSeries().add(data.getPartyName(),
					data.getHeadCount());
		}
		return dataSeries;
	}


	@Override
	public DataSeries createPartyChartTimeSeriesCurrent() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenPartySummary, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		for (final ViewRiksdagenPartySummary data : dataContainer.getAllOrderBy(ViewRiksdagenPartySummary_.currentAssignments)) {
			if (data != null && data.isActiveParliament()) {

				dataSeries = dataSeries.newSeries().add(getPartyName(data.getParty()),
						data.getTotalActiveParliament());
			}
		}
		return dataSeries;
	}

	/**
	 * Gets the party name.
	 *
	 * @param party
	 *            the party
	 * @return the party name
	 */
	private String getPartyName(final String party) {
		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final Optional<ViewRiksdagenParty> matchingObjects =dataContainer.getAll().stream().
			    filter(p -> p.getPartyId().equalsIgnoreCase(party)).
			    findFirst();

		if (matchingObjects.isPresent()) {
			return matchingObjects.get().getPartyName();

		} else {
			return party;
		}
	}

}

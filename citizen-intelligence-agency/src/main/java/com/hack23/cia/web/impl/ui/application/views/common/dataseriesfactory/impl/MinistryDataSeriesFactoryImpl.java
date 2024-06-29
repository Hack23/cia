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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.impl;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.api.MinistryDataSeriesFactory;

/**
 * The Class MinistryDataSeriesFactoryImpl.
 */
@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly = true)
public final class MinistryDataSeriesFactoryImpl implements MinistryDataSeriesFactory {

	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	/**
	 * Instantiates a new ministry data series factory impl.
	 */
	public MinistryDataSeriesFactoryImpl() {
		super();
	}




	@Override
	public DataSeries createChartTimeSeriesTotalDaysServedGovernmentByParty() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalDaysServedGovernment());
			}
		}
		return dataSeries;
	}

	@Override
	public DataSeries createMinistryChartTimeSeriesAll() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenMinistry, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenMinistry.class);

		for (final ViewRiksdagenMinistry data : dataContainer.getAll()) {
			dataSeries =dataSeries.newSeries().add(data.getNameId(),data.getTotalAssignments());
		}
		return dataSeries;
	}



	@Override
	public DataSeries createMinistryChartTimeSeriesCurrent() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenMinistry, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenMinistry.class);

		for (final ViewRiksdagenMinistry data : dataContainer.getAll()) {
			if (data.isActive()) {
				dataSeries =dataSeries.newSeries().add(data.getNameId(),data.getCurrentMemberSize());
			}
		}
		return dataSeries;
	}

}

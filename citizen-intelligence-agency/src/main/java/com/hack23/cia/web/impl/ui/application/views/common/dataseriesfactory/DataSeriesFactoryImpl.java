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
package com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory;

import org.dussan.vaadin.dcharts.data.DataSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommittee;
import com.hack23.cia.model.internal.application.data.ministry.impl.ViewRiksdagenMinistry;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenParty;
import com.hack23.cia.model.internal.application.data.party.impl.ViewRiksdagenPartySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public final class DataSeriesFactoryImpl implements DataSeriesFactory {

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory#createCommitteeChartTimeSeriesAll()
	 */
	@Override
	public DataSeries createCommitteeChartTimeSeriesAll() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		for (final ViewRiksdagenCommittee data : dataContainer.getAll()) {
			dataSeries =dataSeries.newSeries().add(data.getEmbeddedId().getDetail(),data.getTotalAssignments());
		}
		return dataSeries;
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
	@Override
	public DataSeries createCommitteeChartTimeSeriesCurrent() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenCommittee, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenCommittee.class);

		for (final ViewRiksdagenCommittee data : dataContainer.getAll()) {
			if (data.isActive()) {
				dataSeries =dataSeries.newSeries().add(data.getEmbeddedId().getDetail(),data.getCurrentMemberSize());
			}
		}
		return dataSeries;
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory#createChartTimeSeriesCurrentCommitteeByParty()
	 */
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


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory#createChartTimeSeriesTotalDaysServedCommitteeByParty()
	 */
	@Override
	public DataSeries createChartTimeSeriesTotalDaysServedCommitteeByParty() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenParty, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenParty.class);

		final DataContainer<ViewRiksdagenPartySummary, String> partySummarydataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		partySummarydataContainer.getAll();

		for (final ViewRiksdagenParty data : dataContainer.getAll()) {
			final ViewRiksdagenPartySummary summary = partySummarydataContainer.load(data.getPartyId());
			if (summary != null && summary.isActive()) {

				dataSeries =dataSeries.newSeries().add(data.getPartyName(),summary.getTotalDaysServedCommittee());
			}
		}
		return dataSeries;
	}




	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesAll()
	 */
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

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
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

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory#createChartTimeSeriesCurrentGovernmentByParty()
	 */
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


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.dataseriesfactory.DataSeriesFactory#createChartTimeSeriesTotalDaysServedGovernmentByParty()
	 */
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

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesAll()
	 */
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

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.user.common.AbstractRankingView#createChartTimeSeriesCurrent()
	 */
	@Override
	public DataSeries createPartyChartTimeSeriesCurrent() {
		DataSeries dataSeries = new DataSeries();

		final DataContainer<ViewRiksdagenPartySummary, String> dataContainer = applicationManager
				.getDataContainer(ViewRiksdagenPartySummary.class);

		for (final ViewRiksdagenPartySummary data : dataContainer.getAll()) {
			if (data != null && data.isActiveParliament()) {

				dataSeries = dataSeries.newSeries().add(data.getParty(),
						data.getTotalActiveParliament());
			}
		}
		return dataSeries;
	}

}

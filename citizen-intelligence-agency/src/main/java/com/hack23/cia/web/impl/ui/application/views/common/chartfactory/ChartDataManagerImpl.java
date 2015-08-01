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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.SeriesToggles;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.LegendRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Cursor;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.legend.EnhancedLegendRenderer;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hack23.cia.model.external.worldbank.data.impl.WorldBankData;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenCommitteeDecisionTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPartyPeriodSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.RiksdagenVoteDataBallotPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeBallotDecisionPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeDailySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenCommitteeDecisionTypeOrgDailySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPartySummaryDaily;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummary;
import com.hack23.cia.model.internal.application.data.committee.impl.ViewRiksdagenVoteDataBallotPoliticianSummaryDaily;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentOrgSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPartySummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentPersonSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.RiksdagenDocumentTypeSummaryEmbeddedId;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenDocumentTypeDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenOrgDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPartyDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.document.impl.ViewRiksdagenPoliticianDocumentDailySummary;
import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;

/**
 * The Class ChartDataManagerImpl.
 */
@Service
@Transactional
public final class ChartDataManagerImpl implements ChartDataManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ChartDataManagerImpl.class);


	/** The Constant NUMBER_TICKS_DATE. */
	private static final int NUMBER_TICKS_DATE = 8;


	/** The Constant DD_MMM_YYYY. */
	private static final String DD_MMM_YYYY = "dd-MMM-yyyy";


	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private ApplicationManager applicationManager;

	/** The committee ballot decision party map. */
	private Map<String, List<ViewRiksdagenCommitteeBallotDecisionPartySummary>> committeeBallotDecisionPartyMap;

	/** The committee ballot decision politician map. */
	private Map<String, List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary>> committeeBallotDecisionPoliticianMap;

	/** The committee decision type map. */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> committeeDecisionTypeMap;

	/** The committee decision type org map. */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> committeeDecisionTypeOrgMap;

	/** The document type map. */
	private Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> documentTypeMap;

	/** The party ballot map. */
	private Map<String, List<ViewRiksdagenVoteDataBallotPartySummary>> partyBallotMap;

	/** The party map. */
	private Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> partyMap;

	/** The politician ballot map. */
	private Map<String, List<ViewRiksdagenVoteDataBallotPoliticianSummary>> politicianBallotMap;

	/** The view riksdagen committee decision type daily summary map. */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> viewRiksdagenCommitteeDecisionTypeDailySummaryMap;

	/** The view riksdagen committee decision type org daily summary map. */
	private Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> viewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap;

	/** The view riksdagen org document daily summary map. */
	private Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> viewRiksdagenOrgDocumentDailySummaryMap;

	/** The view riksdagen party document daily summary map. */
	private Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> viewRiksdagenPartyDocumentDailySummaryMap;

	/** The view riksdagen politician document daily summary map. */
	private Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> viewRiksdagenPoliticianDocumentDailySummaryMap;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getCommitteeBallotDecisionPartyMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeBallotDecisionPartySummary>> getCommitteeBallotDecisionPartyMap() {
		initCommitteeBallotDecisionPartyMap();
		return committeeBallotDecisionPartyMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getCommitteeBallotDecisionPoliticianMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary>> getCommitteeBallotDecisionPoliticianMap() {
		initCommitteeBallotDecisionPoliticianMap();
		return committeeBallotDecisionPoliticianMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getCommitteeDecisionTypeMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> getCommitteeDecisionTypeMap() {
		initCommitteeDecisionTypeMap();
		return committeeDecisionTypeMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getCommitteeDecisionTypeOrgMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> getCommitteeDecisionTypeOrgMap() {
		initCommitteeDecisionTypeOrgMap();
		return committeeDecisionTypeOrgMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getDocumentTypeMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> getDocumentTypeMap() {
		initDocumentTypeMap();
		return documentTypeMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily()
	 */
	@Override
	public List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily() {
		initPartyMap();

		final Optional<Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>>> first = partyMap
				.entrySet()
				.stream()
				.sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1
						.getValue().size())

				).findFirst();

		if (first.isPresent()) {
			return first.get().getValue();
		} else {
			return new ArrayList<ViewRiksdagenVoteDataBallotPartySummaryDaily>();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getPartyMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> getPartyMap() {
		initPartyMap();

		return partyMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenCommitteeDecisionTypeDailySummaryMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> getViewRiksdagenCommitteeDecisionTypeDailySummaryMap() {
		initCommitteeDecisionTypeDailySummaryMap();

		return viewRiksdagenCommitteeDecisionTypeDailySummaryMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> getViewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap() {
		initCommitteeDecisionTypeOrgDailySummaryMap();

		return viewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenOrgDocumentDailySummaryMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> getViewRiksdagenOrgDocumentDailySummaryMap() {
		initOrgDocumentDailySummaryMap();

		return viewRiksdagenOrgDocumentDailySummaryMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenPartyDocumentDailySummaryMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenPartyDocumentDailySummary>> getViewRiksdagenPartyDocumentDailySummaryMap() {
		initPartyDocumentDailySummaryMap();

		return viewRiksdagenPartyDocumentDailySummaryMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenPoliticianDocumentDailySummaryMap()
	 */
	@Override
	public Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> getViewRiksdagenPoliticianDocumentDailySummaryMap() {
		initPoliticianDocumentDailySummaryMap();

		return viewRiksdagenPoliticianDocumentDailySummaryMap;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenVoteDataBallotPartySummary(java.lang.String)
	 */
	@Override
	public List<ViewRiksdagenVoteDataBallotPartySummary> getViewRiksdagenVoteDataBallotPartySummary(
			final String party) {
		initPartyBallotMap();

		return partyBallotMap.get(party);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenVoteDataBallotPartySummaryDaily(java.lang.String)
	 */
	@Override
	public List<ViewRiksdagenVoteDataBallotPartySummaryDaily> getViewRiksdagenVoteDataBallotPartySummaryDaily(
			final String party) {
		initPartyMap();

		return partyMap.get(party);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#
	 * getViewRiksdagenVoteDataBallotPoliticianSummary(java.lang.String)
	 */
	@Override
	public List<ViewRiksdagenVoteDataBallotPoliticianSummary> getViewRiksdagenVoteDataBallotPoliticianSummary(
			final String id) {
		initPoliticianBallotMap();
		return politicianBallotMap.get(id);
	}

	/**
	 * Inits the committee ballot decision party map.
	 */
	private void initCommitteeBallotDecisionPartyMap() {
		if (committeeBallotDecisionPartyMap == null) {
			final DataContainer<ViewRiksdagenCommitteeBallotDecisionPartySummary, ViewRiksdagenCommitteeBallotDecisionPartyEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPartySummary.class);

			committeeBallotDecisionPartyMap = committeeBallotDecisionPartyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getParty()));
		}
	}

	/**
	 * Inits the committee ballot decision politician map.
	 */
	private void initCommitteeBallotDecisionPoliticianMap() {
		if (committeeBallotDecisionPoliticianMap == null) {
			final DataContainer<ViewRiksdagenCommitteeBallotDecisionPoliticianSummary, ViewRiksdagenCommitteeBallotDecisionPoliticianEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeBallotDecisionPoliticianSummary.class);

			committeeBallotDecisionPoliticianMap = committeeBallotDecisionPartyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getIntressentId()));
		}
	}

	/**
	 * Inits the committee decision type daily summary map.
	 */
	private void initCommitteeDecisionTypeDailySummaryMap() {
		if (viewRiksdagenCommitteeDecisionTypeDailySummaryMap == null) {
			final DataContainer<ViewRiksdagenCommitteeDecisionTypeDailySummary, RiksdagenCommitteeDecisionTypeSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeDecisionTypeDailySummary.class);

			viewRiksdagenCommitteeDecisionTypeDailySummaryMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getDecisionType()));
		}
	}

	/**
	 * Inits the committee decision type map.
	 */
	private void initCommitteeDecisionTypeMap() {
		if (committeeDecisionTypeMap == null) {
			final DataContainer<ViewRiksdagenCommitteeDecisionTypeDailySummary, RiksdagenCommitteeDecisionTypeSummaryEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeDecisionTypeDailySummary.class);

			final Date now = new Date();
			final Date notBefore = new GregorianCalendar(2000,
					Calendar.JANUARY, 1).getTime();
			committeeDecisionTypeMap = committeeBallotDecisionPartyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null
							&& !t.getEmbeddedId().getDecisionDate().after(now)
							&& !notBefore.after(t.getEmbeddedId()
									.getDecisionDate()))
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getDecisionType()));
		}
	}

	/**
	 * Inits the committee decision type org daily summary map.
	 */
	private void initCommitteeDecisionTypeOrgDailySummaryMap() {
		if (viewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap == null) {
			final DataContainer<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary, RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeDecisionTypeOrgDailySummary.class);

			viewRiksdagenCommitteeDecisionTypeOrgDailySummaryMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getOrg().toUpperCase(Locale.ENGLISH).trim()));
		}
	}

	/**
	 * Inits the committee decision type org map.
	 */
	private void initCommitteeDecisionTypeOrgMap() {
		if (committeeDecisionTypeOrgMap == null) {
			final DataContainer<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary, RiksdagenCommitteeDecisionTypeOrgSummaryEmbeddedId> committeeBallotDecisionPartyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenCommitteeDecisionTypeOrgDailySummary.class);

			committeeDecisionTypeOrgMap = committeeBallotDecisionPartyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getOrg()));
		}
	}

	/**
	 * Inits the document type map.
	 */
	private void initDocumentTypeMap() {
		if (documentTypeMap == null) {
			final DataContainer<ViewRiksdagenDocumentTypeDailySummary, RiksdagenDocumentTypeSummaryEmbeddedId> documentTypeSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenDocumentTypeDailySummary.class);

			documentTypeMap = documentTypeSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null
							&& !t.getEmbeddedId().getPublicDate()
									.startsWith("19")
							&& "MOT:PROP:BET".contains(t.getEmbeddedId()
									.getDocumentType()))
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getDocumentType()));
		}

	}

	/**
	 * Inits the org document daily summary map.
	 */
	private void initOrgDocumentDailySummaryMap() {
		if (viewRiksdagenOrgDocumentDailySummaryMap == null) {
			final DataContainer<ViewRiksdagenOrgDocumentDailySummary, RiksdagenDocumentOrgSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenOrgDocumentDailySummary.class);

			viewRiksdagenOrgDocumentDailySummaryMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null
							&& !t.getEmbeddedId().getPublicDate()
									.startsWith("19"))
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getOrg().toUpperCase(Locale.ENGLISH).trim()));
		}
	}

	/**
	 * Inits the party ballot map.
	 */
	private void initPartyBallotMap() {
		if (partyBallotMap == null) {
			final DataContainer<ViewRiksdagenVoteDataBallotPartySummary, RiksdagenVoteDataBallotPartyEmbeddedId> partyBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummary.class);

			partyBallotMap = partyBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getParty()));
		}
	}

	/**
	 * Inits the party document daily summary map.
	 */
	private void initPartyDocumentDailySummaryMap() {
		if (viewRiksdagenPartyDocumentDailySummaryMap == null) {
			final DataContainer<ViewRiksdagenPartyDocumentDailySummary, RiksdagenDocumentPartySummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenPartyDocumentDailySummary.class);

			viewRiksdagenPartyDocumentDailySummaryMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getPartyShortCode()));
		}
	}

	/**
	 * Inits the party map.
	 */
	private void initPartyMap() {
		if (partyMap == null) {
			final DataContainer<ViewRiksdagenVoteDataBallotPartySummaryDaily, RiksdagenVoteDataBallotPartyPeriodSummaryEmbeddedId> partyBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenVoteDataBallotPartySummaryDaily.class);

			partyMap = partyBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getParty()));
		}
	}

	/**
	 * Inits the politician ballot map.
	 */
	private void initPoliticianBallotMap() {
		if (politicianBallotMap == null) {
			final DataContainer<ViewRiksdagenVoteDataBallotPoliticianSummary, RiksdagenVoteDataBallotPoliticianEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenVoteDataBallotPoliticianSummary.class);
			politicianBallotMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getIntressentId()));
		}
	}

	/**
	 * Inits the politician document daily summary map.
	 */
	private void initPoliticianDocumentDailySummaryMap() {
		if (viewRiksdagenPoliticianDocumentDailySummaryMap == null) {
			final DataContainer<ViewRiksdagenPoliticianDocumentDailySummary, RiksdagenDocumentPersonSummaryEmbeddedId> politicianBallotSummaryDailyDataContainer = applicationManager
					.getDataContainer(ViewRiksdagenPoliticianDocumentDailySummary.class);

			viewRiksdagenPoliticianDocumentDailySummaryMap = politicianBallotSummaryDailyDataContainer
					.getAll()
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getPersonId()));
		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#createPartyWinnerChart()
	 */
	@Override
	public DCharts createPartyWinnerChart() {


		final Map<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> map = getPartyMap();




		final DataSeries dataSeries = new DataSeries();

		final String dateFormatPatter="dd-MMM-yyyy";

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatPatter,Locale.ENGLISH);



		final Series series = new Series();

		for(final Entry<String, List<ViewRiksdagenVoteDataBallotPartySummaryDaily>> entry: map.entrySet()) {

			series.addSeries(
					new XYseries()
					.setLabel(entry.getKey()));

			dataSeries.newSeries();
			final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = entry.getValue();
			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily !=null) {
					dataSeries.add(simpleDateFormat.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),viewRiksdagenVoteDataBallotPartySummaryDaily.getPartyWonPercentage());
				}
			}

		}


		series.addSeries(
				new XYseries()
				.setLabel("Number ballots"));

		dataSeries.newSeries();
		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = getMaxSizeViewRiksdagenVoteDataBallotPartySummaryDaily();
		for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
			if (viewRiksdagenVoteDataBallotPartySummaryDaily !=null) {
				dataSeries.add(simpleDateFormat.format(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getVoteDate()),viewRiksdagenVoteDataBallotPartySummaryDaily.getNumberBallots());
			}
		}


		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#createDocumentTypeChart()
	 */
	@Override
	public DCharts createDocumentTypeChart() {

		final Map<String, List<ViewRiksdagenDocumentTypeDailySummary>> map = getDocumentTypeMap();




		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for(final Entry<String, List<ViewRiksdagenDocumentTypeDailySummary>> entry: map.entrySet()) {

			if (entry.getKey() != null) {
				series.addSeries(
						new XYseries()
						.setLabel(entry.getKey()));

				dataSeries.newSeries();
				final List<ViewRiksdagenDocumentTypeDailySummary> list = entry.getValue();
				for (final ViewRiksdagenDocumentTypeDailySummary viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
					if (viewRiksdagenVoteDataBallotPartySummaryDaily !=null) {
						dataSeries.add(viewRiksdagenVoteDataBallotPartySummaryDaily.getEmbeddedId().getPublicDate(),viewRiksdagenVoteDataBallotPartySummaryDaily.getTotal());
					}
				}
			}

		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.ChartDataManager#createDecisionTypeChart()
	 */
	@Override
	public DCharts createDecisionTypeChart() {

		final Map<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> map = getCommitteeDecisionTypeMap();




		final String dateFormatPatter = "dd-MMM-yyyy";

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormatPatter,Locale.ENGLISH);


		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		for(final Entry<String, List<ViewRiksdagenCommitteeDecisionTypeDailySummary>> entry: map.entrySet()) {

			if (entry.getKey() != null) {
				series.addSeries(
						new XYseries()
						.setLabel(entry.getKey()));

				dataSeries.newSeries();
				final List<ViewRiksdagenCommitteeDecisionTypeDailySummary> list = entry.getValue();
				for (final ViewRiksdagenCommitteeDecisionTypeDailySummary item : list) {
					if (item !=null) {
						dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getDecisionDate()),item.getTotal());
					}
				}
			}

		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createDecisionTypeChart(java.lang.String)
	 */
	@Override
	public DCharts createDecisionTypeChart(final String org) {



		final String dateFormatPatter = "dd-MMM-yyyy";

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormatPatter,Locale.ENGLISH);


		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();



		final Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> allMap = getCommitteeDecisionTypeOrgMap();

		final List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary> itemList = allMap.get(org.toUpperCase(Locale.ENGLISH).replace("_", "").trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null && t.getEmbeddedId()
							.getDecisionDate() !=null)
					.collect(
							Collectors.groupingBy(t -> t.getEmbeddedId()
									.getDecisionType()));


			for(final String key: map.keySet()) {

				series.addSeries(new XYseries().setLabel(key));

				dataSeries.newSeries();
				final List<ViewRiksdagenCommitteeDecisionTypeOrgDailySummary> list = map.get(key.toUpperCase(Locale.ENGLISH));
				for (final ViewRiksdagenCommitteeDecisionTypeOrgDailySummary item : list) {
					if (item !=null) {
						dataSeries.add(simpleDateFormat.format(item.getEmbeddedId().getDecisionDate()),item.getTotal());
					}
				}

			}
		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createDocumentHistoryChart(java.lang.String)
	 */
	@Override
	public DCharts createDocumentHistoryChart(final String org) {



		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();



		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap.get(org.toUpperCase(Locale.ENGLISH).replace("_", "").trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> StringUtils.defaultIfBlank(t.getDocumentType(), "NoInfo")));


			for(final String key: map.keySet()) {

				series.addSeries(new XYseries().setLabel(key));

				dataSeries.newSeries();
				final List<ViewRiksdagenOrgDocumentDailySummary> list = map.get(key.toUpperCase(Locale.ENGLISH));
				if (list !=null) {
					for (final ViewRiksdagenOrgDocumentDailySummary item : list) {
						if (item !=null) {
							dataSeries.add(item.getEmbeddedId().getPublicDate(),item.getTotal());
						}
					}
				} else {
					LOGGER.info("missing data for key:{}",key);
				}

			}
		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createDocumentMinistryHistoryChart(java.lang.String)
	 */
	@Override
	public DCharts createDocumentMinistryHistoryChart(final String org) {



		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap.get(org.toUpperCase(Locale.ENGLISH).replace("_", "").trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null && t.getEmbeddedId().getPublicDate()!=null)
					.collect(
							Collectors.groupingBy(t -> StringUtils.defaultIfBlank(t.getDocumentType(), "NoInfo")));


			for(final String key: map.keySet()) {

				series.addSeries(new XYseries().setLabel(key));

				dataSeries.newSeries();
				final List<ViewRiksdagenOrgDocumentDailySummary> list = map.get(key.toUpperCase(Locale.ENGLISH));
				if (list !=null) {
					for (final ViewRiksdagenOrgDocumentDailySummary item : list) {
						if (item !=null) {
							dataSeries.add(item.getEmbeddedId().getPublicDate(),item.getTotal());
						}
					}
				} else {
					LOGGER.info("missing data for key:{}",key);
				}

			}
		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}







	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createPartyLineChart(java.lang.String)
	 */
	@Override
	public DCharts createPartyLineChart(final String partyId) {

		final List<ViewRiksdagenVoteDataBallotPartySummaryDaily> list = getViewRiksdagenVoteDataBallotPartySummaryDaily(partyId);

		final Series series = new Series().addSeries(
				new XYseries().setLabel("Party Won")).addSeries(
						new XYseries().setLabel("Party Absent"));

		final DataSeries dataSeries = new DataSeries().newSeries();

		final String dateFormatPatter = "dd-MMM-yyyy";

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormatPatter,Locale.ENGLISH);

		if (list != null) {

			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
					dataSeries
					.add(simpleDateFormat
							.format(viewRiksdagenVoteDataBallotPartySummaryDaily
									.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPartySummaryDaily
									.getPartyWonPercentage());
				}
			}

			dataSeries.newSeries();

			for (final ViewRiksdagenVoteDataBallotPartySummaryDaily viewRiksdagenVoteDataBallotPartySummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPartySummaryDaily != null) {
					dataSeries
					.add(simpleDateFormat
							.format(viewRiksdagenVoteDataBallotPartySummaryDaily
									.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPartySummaryDaily
									.getPartyPercentageAbsent());
				}
			}
		}
		final Cursor cursor = new Cursor().setShow(true);

		final Options options = new Options().addOption(new SeriesDefaults())
				.addOption(createAxesXYDateFloat()).addOption(createHighLighterNorth()).addOption(cursor)
				.addOption(series).addOption(createLegendOutside());

		return new DCharts().setDataSeries(dataSeries)
				.setOptions(options).show();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createDocumentHistoryPartyChart(java.lang.String)
	 */
	@Override
	public DCharts createDocumentHistoryPartyChart(final String org) {



		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();



		final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> allMap = getViewRiksdagenOrgDocumentDailySummaryMap();

		final List<ViewRiksdagenOrgDocumentDailySummary> itemList = allMap.get(org.toUpperCase(Locale.ENGLISH).replace("_", "").trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenOrgDocumentDailySummary>> map = itemList.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> StringUtils.defaultIfBlank(t.getDocumentType(), "NoInfo")));


			for(final String key: map.keySet()) {

				series.addSeries(new XYseries().setLabel(key));

				dataSeries.newSeries();
				final List<ViewRiksdagenOrgDocumentDailySummary> list = map.get(key.toUpperCase(Locale.ENGLISH));
				if (list !=null) {
					for (final ViewRiksdagenOrgDocumentDailySummary item : list) {
						if (item !=null) {
							dataSeries.add(item.getEmbeddedId().getPublicDate(),item.getTotal());
						}
					}
				} else {
					LOGGER.info("missing data for key:{}",key);
				}

			}
		}



		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(createOptionsXYDateFloatLegendOutside(series))
		.show();
	}







	/** The data chart manager. */
	@Autowired
	private GenericChartDataManager<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> dataChartManager;



	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createPersonLineChart(java.lang.String)
	 */
	@Override
	public DCharts createPersonLineChart(final String personId) {

		final List<ViewRiksdagenVoteDataBallotPoliticianSummaryDaily> list = dataChartManager.findByValue(personId);

		final Series series = new Series()
				.addSeries(new XYseries().setLabel("Won"))
				.addSeries(new XYseries().setLabel("Party Rebel"))
				.addSeries(new XYseries().setLabel("Absent"))
				.addSeries(new XYseries().setLabel("Number ballots"));

		final DataSeries dataSeries = new DataSeries().newSeries();

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DD_MMM_YYYY, Locale.ENGLISH);

		if (list != null) {

			for (final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily viewRiksdagenVoteDataBallotPoliticianSummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPoliticianSummaryDaily != null) {
					dataSeries
							.add(simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getWonPercentage());
				}
			}

			dataSeries.newSeries();

			for (final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily viewRiksdagenVoteDataBallotPoliticianSummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPoliticianSummaryDaily != null) {
					dataSeries
							.add(simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getRebelPercentage());
				}
			}

			dataSeries.newSeries();
			for (final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily viewRiksdagenVoteDataBallotPoliticianSummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPoliticianSummaryDaily != null) {
					dataSeries
							.add(simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getPoliticianPercentageAbsent());
				}
			}

			dataSeries.newSeries();
			for (final ViewRiksdagenVoteDataBallotPoliticianSummaryDaily viewRiksdagenVoteDataBallotPoliticianSummaryDaily : list) {
				if (viewRiksdagenVoteDataBallotPoliticianSummaryDaily != null) {
					dataSeries
							.add(simpleDateFormat
									.format(viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getEmbeddedId().getVoteDate()),
									viewRiksdagenVoteDataBallotPoliticianSummaryDaily
											.getNumberBallots());
				}
			}

		}

		final Cursor cursor = new Cursor().setShow(true);

		final Options options = new Options().addOption(new SeriesDefaults())
				.addOption(createAxesXYDateFloat()).addOption(createHighLighterNorth()).addOption(cursor)
				.addOption(series).addOption(createLegendOutside());

		return new DCharts().setDataSeries(dataSeries).setOptions(options)
				.show();
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createPersonDocumentHistoryChart(java.lang.String)
	 */
	@Override
	public DCharts createPersonDocumentHistoryChart(final String personId) {

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DD_MMM_YYYY, Locale.ENGLISH);

		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

		final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> allMap = getViewRiksdagenPoliticianDocumentDailySummaryMap();

		final List<ViewRiksdagenPoliticianDocumentDailySummary> itemList = allMap
				.get(personId.toUpperCase(Locale.ENGLISH).replace("_", "").trim());

		if (itemList != null) {

			final Map<String, List<ViewRiksdagenPoliticianDocumentDailySummary>> map = itemList
					.parallelStream()
					.filter(t -> t != null)
					.collect(
							Collectors.groupingBy(t -> StringUtils
									.defaultIfBlank(t.getEmbeddedId()
											.getDocumentType(), "NoInfo")));

			for (final String key : map.keySet()) {

				series.addSeries(new XYseries().setLabel(key));

				dataSeries.newSeries();
				final List<ViewRiksdagenPoliticianDocumentDailySummary> list = map
						.get(key.toUpperCase(Locale.ENGLISH));
				if (list != null) {
					for (final ViewRiksdagenPoliticianDocumentDailySummary item : list) {
						if (item != null) {
							dataSeries.add(simpleDateFormat.format(item
									.getEmbeddedId().getPublicDate()), item
									.getTotal());
						}
					}
				} else {
					LOGGER.info("missing data for key:{}", key);
				}

			}
		}

		return new DCharts().setDataSeries(dataSeries).setOptions(createOptionsXYDateFloatLegendOutside(series))
				.show();
	}

	/**
	 * Creates the axes xy date float.
	 *
	 * @return the axes
	 */
	private static Axes createAxesXYDateFloat() {
		return new Axes().addAxis(
				new XYaxis()
						.setRenderer(AxisRenderers.DATE)
						.setTickOptions(
								new AxisTickRenderer()
										.setFormatString("%Y-%#m-%#d"))
						.setNumberTicks(NUMBER_TICKS_DATE)).addAxis(
				new XYaxis(XYaxes.Y).setTickOptions(new AxisTickRenderer()
						.setFormatString("%.2f")));
	}


	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createIndicatorChart(java.util.List, com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary)
	 */
	@Override
	public DCharts createIndicatorChart(final List<WorldBankData> list,final ViewWorldbankIndicatorDataCountrySummary summary) {

		final String dateFormatPatter = "dd-MMM-yyyy";

		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormatPatter,Locale.ENGLISH);


		final DataSeries dataSeries = new DataSeries();

		final Series series = new Series();

			series.addSeries(
					new XYseries()
					.setLabel(summary.getIndicatorName()));

			dataSeries.newSeries();

			for (final WorldBankData item : list) {
				if (item !=null && item.getYearDate() !=null  && item.getDataValue() !=null && !item.getDataValue().isEmpty()) {
					dataSeries.add(item.getYearDate().toString() +"-01-01",Float.valueOf(item.getDataValue()));
				}
			}


		final Axes axes = new Axes()
		.addAxis(new XYaxis()
				.setRenderer(AxisRenderers.DATE)
				.setTickOptions(new AxisTickRenderer().setFormatString("%Y-%#m-%#d")).setNumberTicks(NUMBER_TICKS_DATE))
		.addAxis(new XYaxis(XYaxes.Y).setLabel(summary.getIndicatorName()));


		final Options options = new Options()
		.addOption(new SeriesDefaults())
		.addOption(axes)
		.addOption(createHighLighterNorth())
		.addOption(series)
		.addOption(createLegendOutside());

		return new DCharts()
		.setDataSeries(dataSeries)
		.setOptions(options)
		.show();
	}




	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.chartfactory.ChartDataManager#createChartPanel(org.dussan.vaadin.dcharts.data.DataSeries, java.lang.String)
	 */
	@Override
	public DCharts createChartPanel(final DataSeries dataSeries,final String caption) {

		final Options options = new Options()
		.setSeriesDefaults(createSeriesDefaultPieChart())
		.setLegend(createdLegendEnhancedInsideWest())
		.setHighlighter(createHighLighter());


		//String[] seriesColors= new String[]{"#333333", "#999999", "#3EA140", "#3EA140", "#3EA140", "#783F16", "#783F16", "#783F16"};
		//options.setSeriesColors(seriesColors);

		final DCharts chart = new DCharts().setDataSeries(dataSeries).setOptions(options);

		chart.setCaption(caption);
		chart.show();
		chart.setSizeFull();
		return chart;

	}

	/**
	 * Creates the options xy date float legend outside.
	 *
	 * @param series
	 *            the series
	 * @return the options
	 */
	private static Options createOptionsXYDateFloatLegendOutside(final Series series) {
		return new Options()
		.addOption(new SeriesDefaults())
		.addOption(createAxesXYDateFloat())
		.addOption(createHighLighterNorth())
		.addOption(series)
		.addOption(createLegendOutside());
	}


	/**
	 * Creates the series default pie chart.
	 *
	 * @return the series defaults
	 */
	private static SeriesDefaults createSeriesDefaultPieChart() {
		return new SeriesDefaults()
		.setRenderer(SeriesRenderers.PIE)
		.setRendererOptions(
				new PieRenderer()
				.setShowDataLabels(true)).setShadow(true);
	}

	/**
	 * Creates the high lighter.
	 *
	 * @return the highlighter
	 */
	private static Highlighter createHighLighter() {
		return new Highlighter()
		.setShow(true)
		.setShowTooltip(true)
		.setTooltipAlwaysVisible(true)
		.setKeepTooltipInsideChart(true);
	}

	/**
	 * Creates the high lighter north.
	 *
	 * @return the highlighter
	 */
	private static Highlighter createHighLighterNorth() {
		return new Highlighter()
		.setShow(true)
		.setShowTooltip(true)
		.setTooltipAlwaysVisible(true)
		.setKeepTooltipInsideChart(true)
		.setTooltipLocation(TooltipLocations.NORTH)
		.setTooltipAxes(TooltipAxes.XY_BAR);
	}

	/**
	 * Created legend enhanced inside west.
	 *
	 * @return the legend
	 */
	private static Legend createdLegendEnhancedInsideWest() {
		return new Legend()
		.setShow(true)
		.setPlacement(LegendPlacements.INSIDE_GRID)
		.setLocation(LegendLocations.WEST)
		.setRenderer(LegendRenderers.ENHANCED)
		.setRendererOptions(
				new EnhancedLegendRenderer()
				.setSeriesToggle(SeriesToggles.SLOW)
				.setSeriesToggleReplot(true));
	}

	/**
	 * Creates the legend outside.
	 *
	 * @return the legend
	 */
	private static Legend createLegendOutside() {
		return new Legend()
		.setShow(true)
		.setRendererOptions(
				new EnhancedLegendRenderer()
				.setSeriesToggle(SeriesToggles.SLOW)
				.setSeriesToggleReplot(true))
				.setPlacement(LegendPlacements.OUTSIDE_GRID);
	}

}

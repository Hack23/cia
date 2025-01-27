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
 *  $Id$
 *  $HeadURL$
 */
package com.hack23.cia.web.impl.ui.application.views.user.partyranking.pagemode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPolitician;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianBallotSummary;
import com.hack23.cia.model.internal.application.data.politician.impl.ViewRiksdagenPoliticianExperienceSummary;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.external.esv.api.EsvApi;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualOutcomeSummary;
import com.hack23.cia.service.external.esv.api.GovernmentBodyAnnualSummary;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandPartyRankingConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.LeaderCardUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PartyLeaderUtil;
import com.hack23.cia.web.impl.ui.application.views.common.rows.RowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl.
 */
@Service
public final class PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl
		extends AbstractPartyRankingPageModContentFactoryImpl {

	/** The Constant NAME. */
	public static final String NAME = UserViews.PARTY_RANKING_VIEW_NAME;

	/** The esv api. */
	// Adding EsvApi to fetch ministry-related data
	@Autowired
	private EsvApi esvApi;

	/** The leader card util. */
	@Autowired
	private LeaderCardUtil leaderCardUtil;

	/**
	 * Instantiates a new party ranking current parties leader scoreboard page mod content factory impl.
	 */
	public PartyRankingCurrentPartiesLeaderScoreboardPageModContentFactoryImpl() {
		super();
	}

	/**
	 * Creates the content.
	 *
	 * @param parameters the parameters
	 * @param menuBar the menu bar
	 * @param panel the panel
	 * @return the layout
	 */
	@Secured({ "ROLE_ANONYMOUS", "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		getPartyRankingMenuItemFactory().createPartyRankingMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(panel, panelContent,
			PartyRankingViewConstants.LEADER_SCOREBOARD_TITLE,
			PartyRankingViewConstants.TITLE_PARTY_RANKINGS,
			PartyRankingViewConstants.LEADER_SCOREBOARD_DESC);

		final HorizontalLayout chartLayout = new HorizontalLayout();
		chartLayout.setSizeFull();
		panelContent.addComponent(chartLayout);
		panelContent.setExpandRatio(chartLayout, ContentRatio.LARGE_FORM);

		final VerticalLayout wrapper = new VerticalLayout();
		wrapper.setSizeFull();
		chartLayout.addComponent(wrapper);

		final ResponsiveRow row = RowUtil.createGridLayout(wrapper);
		row.setSizeFull();

		final Map<String, List<ViewRiksdagenPolitician>> politicianMap = leaderCardUtil.loadActivePoliticiansByPersonId();
		final Map<String, Boolean> partyLeaderMap = PartyLeaderUtil.computePartyLeaders(getApplicationManager(), politicianMap.keySet());


		final List<ViewRiksdagenPolitician> partyLeaders = politicianMap.values().stream()
		        .flatMap(List::stream) // Flatten the list of politicians
		        .filter(p -> partyLeaderMap.getOrDefault(p.getPersonId(), false)) // Filter using the flattened stream
		        .collect(Collectors.toList());

		// Sort: in government first, then alphabetical by last name
		partyLeaders.sort((a, b) -> {
			final boolean aInGov = a.isActiveGovernment();
			final boolean bInGov = b.isActiveGovernment();
			if (aInGov == bInGov) {
				return a.getLastName().compareToIgnoreCase(b.getLastName());
			}
			// government first
			return Boolean.compare(!aInGov, !bInGov);
		});

		// Load ESV data for ministries
		final Map<Integer, List<GovernmentBodyAnnualSummary>> dataMap = esvApi.getData();
		final int CURRENT_YEAR = 2024;
		final List<GovernmentBodyAnnualSummary> currentYearGovernmentBodies = dataMap.get(CURRENT_YEAR);
		final Map<String, List<GovernmentBodyAnnualSummary>> governmentBodyByMinistry = currentYearGovernmentBodies
				.stream().collect(Collectors.groupingBy(GovernmentBodyAnnualSummary::getMinistry));

		final Map<String, List<GovernmentBodyAnnualOutcomeSummary>> reportByMinistry = esvApi
				.getGovernmentBodyReportByMinistry();

		for (final ViewRiksdagenPolitician leader : partyLeaders) {
			final ViewRiksdagenPoliticianBallotSummary ballotSummary = getApplicationManager()
					.getDataContainer(ViewRiksdagenPoliticianBallotSummary.class).load(leader.getPersonId());
		    final ViewRiksdagenPoliticianExperienceSummary experienceSummary = getApplicationManager().getDataContainer(ViewRiksdagenPoliticianExperienceSummary.class).load(leader.getPersonId());

			final Panel cardPanel = leaderCardUtil.createLeaderCard(leader, ballotSummary, governmentBodyByMinistry, reportByMinistry, experienceSummary);
			row.addColumn().withDisplayRules(12, 6, 4, 4).withComponent(cardPanel);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_PARTY_RANKING_VIEW, ApplicationEventGroup.USER, NAME,
				parameters, pageId);

		return panelContent;
	}

	/**
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandPartyRankingConstants.COMMAND_PARTY_LEADER_SCOREBOARD.matches(page, parameters);
	}

}

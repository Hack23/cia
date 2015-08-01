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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

@Component
public final class MenuItemFactoryImpl implements MenuItemFactory {

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The Constant POLITICIAN_RANKING_LINK_TEXT. */
	private static final String POLITICIAN_RANKING_LINK_TEXT = "Politician Ranking";

	/** The Constant PARTY_RANKING_LINK_TEXT. */
	private static final String PARTY_RANKING_LINK_TEXT = "Party Ranking";

	/** The Constant COMMITTEE_RANKING_LINK_TEXT. */
	private static final String COMMITTEE_RANKING_LINK_TEXT = "Committee Ranking";

	/** The Constant MINISTRY_RANKING_LINK_TEXT. */
	private static final String MINISTRY_RANKING_LINK_TEXT = "Ministry Ranking";

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMainPageMenuBar()
	 */
	@Override
	public MenuBar createMainPageMenuBar() {
		final MenuBar barmenu = new MenuBar();

		barmenu.addItem("Overview", null, new PageModeMenuCommand("main", PageMode.Overview));

		final MenuItem adminMenuItem = barmenu.addItem("Admin", null, null);

		adminMenuItem.addItem("Agent operations",
				new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, PageMode.Overview));
		adminMenuItem.addItem("Data Summary",
				new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, PageMode.Overview));

		final MenuItem rankingsMenuItem = barmenu.addItem("Ranking", null, null);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.Overview));

		createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Overview));

		createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));

		createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Overview));

		createMinistryRankingTopics(ministryMenuItem);

		barmenu.addItem("Test", new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Overview));

		return barmenu;
	}

	/**
	 * Creates the ministry ranking topics.
	 *
	 * @param ministryMenuItem
	 *            the ministry menu item
	 */
	private static void createMinistryRankingTopics(final MenuItem ministryMenuItem) {

		ministryMenuItem.addItem("Overview", null,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = ministryMenuItem.addItem("Ranking list by topic", null, null);

		final MenuItem listItem = listByTopic.addItem("Political Work Summary",
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Current and past member and summary of total polticial days membership");

		final MenuItem chartByTopic = ministryMenuItem.addItem("Chart by topic", null, null);

		chartByTopic.addItem("Current ministries, current members",
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in ministries, head count",
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total days served in ministries",
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All ministries, total members",
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Creates the committee ranking topics.
	 *
	 * @param committeeMenuItem
	 *            the committee menu item
	 */
	private static void createCommitteeRankingTopics(final MenuItem committeeMenuItem) {
		committeeMenuItem.addItem("Overview", null,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = committeeMenuItem.addItem("Ranking list by topic", null, null);

		final MenuItem listItem = listByTopic.addItem("Political Work Summary",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Current and past member and summary of polticial days ");

		final MenuItem chartByTopic = committeeMenuItem.addItem("Chart by topic", null, null);

		chartByTopic.addItem("Current committees, current members",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in committees, head count",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in committees, current assignments",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in committees, total days served in committees",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("All parties, total days served in committees",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total assignments",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("All committees, total members",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All committees,total days served in committees",
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Creates the party ranking topics.
	 *
	 * @param partynMenuItem
	 *            the partyn menu item
	 */
	private static void createPartyRankingTopics(final MenuItem partynMenuItem) {
		partynMenuItem.addItem("Overview", null,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = partynMenuItem.addItem("Ranking list by topic", null, null);

		final MenuItem listItem = listByTopic.addItem("Total members",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Party by total members, based on roles in departments, committees and parliament");

		final MenuItem chartByTopic = partynMenuItem.addItem("Chart by topic", null, null);

		chartByTopic.addItem("Current parties active in ministries, head count",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties, total days served in ministries",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("Current parties active in committees, head count",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in committees, current assignments",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties active in committees, total days served in committees",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("Current parties active in parliament, head count",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("Current parties, total days served in parliament",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("All parties, head count in parliament",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total days served in parliament",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

		chartByTopic.addItem("All parties, total days served in ministries",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total days served in committees",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total assignments in committees",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

	}

	/**
	 * Creates the politician ranking topics.
	 *
	 * @param politicianMenuItem
	 *            the politician menu item
	 */
	private static void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
		politicianMenuItem.addItem("Overview", null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = politicianMenuItem.addItem("Ranking list by topic", null, null);

		final MenuItem listItem = listByTopic.addItem("Political Experience Summary",
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Current and past assignments and summary experience in days");

		final MenuItem chartByTopic = politicianMenuItem.addItem("Chart by topic", null, null);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createTestTopicMenu(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createTestTopicMenu(final MenuBar barmenu) {
		barmenu.addItem("Overview", null, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Overview));

		final MenuItem charts = barmenu.addItem("Charts", null, null);

		// Submenu item with a sub-submenu
		final MenuItem chartIndicators = charts.addItem("Swedish parliament Indicators", null, null);
		final MenuItem addItem = chartIndicators.addItem("Party Winner", new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts, ChartIndicators.PartyWinner.toString()));
		addItem.setDescription("daily average % won ballots");
		final MenuItem addItem2 = chartIndicators.addItem("Document activity by type", new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts, ChartIndicators.DocumentActivityByType.toString()));
		addItem2.setDescription("daily total of number published documents");
		final MenuItem addItem3 = chartIndicators.addItem("Decision activity by type", new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.Charts, ChartIndicators.DecsionActivityByType.toString()));
		addItem3.setDescription("daily total of number of decsions made");

		final DataContainer<ViewWorldbankIndicatorDataCountrySummary, WorldbankIndicatorDataCountrySummaryEmbeddedId> indicatorDataCountrSummaryDailyDataContainer = applicationManager
				.getDataContainer(ViewWorldbankIndicatorDataCountrySummary.class);

		final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> sourceIndicatorMap = indicatorDataCountrSummaryDailyDataContainer
				.getAll().parallelStream()
				.filter(t -> t != null && t.getSourceValue() != null && t.getEndYear() > 2010 && t.getDataPoint() > 10)
				.collect(Collectors.groupingBy(t -> t.getSourceValue()));

		final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> topicIndicatorMap = indicatorDataCountrSummaryDailyDataContainer
				.getAll().parallelStream()
				.filter(t -> t != null && t.getSourceValue() != null && t.getEndYear() > 2010 && t.getDataPoint() > 10)
				.flatMap(t -> Arrays.asList(t.getTopics().split(";")).stream().map(
						topic -> new AbstractMap.SimpleEntry<String, ViewWorldbankIndicatorDataCountrySummary>(topic,
								t)))

		.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(v -> v.getValue(), Collectors.toList())));

		final MenuItem countryIndicators = charts.addItem("Country Indicators, Sweden", null, null);

		final MenuItem byTopicItem = countryIndicators.addItem("ByTopic", null);

		final MenuItem bySourceItem = countryIndicators.addItem("By Source", null);

		addSourcesAndIndicatorsToMenu(byTopicItem, topicIndicatorMap);
		addSourcesAndIndicatorsToMenu(bySourceItem, sourceIndicatorMap);
	}

	/**
	 * Adds the sources and indicators to menu.
	 *
	 * @param countryIndicators
	 *            the country indicators
	 * @param sourceIndicatorMap
	 *            the source indicator map
	 */
	private void addSourcesAndIndicatorsToMenu(final MenuItem countryIndicators,
			final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> sourceIndicatorMap) {

		final Map<String, List<ViewWorldbankIndicatorDataCountrySummary>> sortedIndicatorMap = sourceIndicatorMap
				.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (final Entry<String, List<ViewWorldbankIndicatorDataCountrySummary>> entry : sortedIndicatorMap
				.entrySet()) {

			final MenuItem sourceItems = countryIndicators.addItem(entry.getKey(), null, null);

			final List<ViewWorldbankIndicatorDataCountrySummary> sortedEntries = entry.getValue().stream()
					.sorted((e1, e2) -> e1.getIndicatorName().compareTo(e2.getIndicatorName()))
					.collect(Collectors.toList());

			for (final ViewWorldbankIndicatorDataCountrySummary indciatorSummary : sortedEntries) {
				final MenuItem addItem3 = sourceItems.addItem(indciatorSummary.getIndicatorName(),
						new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Indicators,
								indciatorSummary.getEmbeddedId().getIndicatorId()));
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPoliticianMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createPoliticianMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		if (menuBar.getItems().isEmpty()) {

			menuBar.addItem("Overview", null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem("Charts", null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem("Indicators", null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem("Roles", null, null);

			rolesItem.addItem("Total experience", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleSummary.toString(), pageId));

			rolesItem.addItem("RoleList", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleList.toString(), pageId));

			rolesItem.addItem("RoleGhant", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem("Documents", null, null);

			documentItem.addItem("Document Activity", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem("Document history", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DocumentHistory.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem("Ballots", null, null);

			ballotItem.addItem("Vote history", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.VoteHistory.toString(), pageId));

			ballotItem.addItem("Ballot Decision Summary", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.BallotDecisionSummary.toString(), pageId));

		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPoliticianRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
		if (!menuBar.getItems().isEmpty()) {
			menuBar.removeItems();
		}

		createPoliticianRankingTopics(menuBar.addItem("Politician Ranking", null,null));
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPartyMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		if (menuBar.getItems().isEmpty()) {

			menuBar.addItem("Overview", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem("Charts", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Charts, pageId));

			menuBar.addItem("Indicators", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem("Roles", null, null);

			rolesItem.addItem("Current Leaders", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CurrentLeaders.toString(), pageId));

			rolesItem.addItem("Leader History", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LeaderHistory.toString(), pageId));

			rolesItem.addItem("Current Members", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CurrentMembers.toString(), pageId));

			rolesItem.addItem("Member History", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem("Goverment Roles", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.GovernmentRoles.toString(), pageId));

			rolesItem.addItem("Committee Roles", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CommitteeRoles.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem("Documents", null, null);

			documentItem.addItem("Document Activity", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem("Document history", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DocumentHistory.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem("Ballots", null, null);

			ballotItem.addItem("Vote history", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VoteHistory.toString(), pageId));

			ballotItem.addItem("Ballot Decision Summary", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CommitteeBallotDecisionSummary.toString(), pageId));

			ballotItem.addItem("Party Won Daily Summary Chart", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.PartyWonDailySummaryChart.toString(), pageId));

		}

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPartyRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createPartyRankingMenuBar(final MenuBar menuBar) {
		if (!menuBar.getItems().isEmpty()) {
			menuBar.removeItems();
		}

		createPartyRankingTopics(menuBar.addItem("Party Ranking", null,null));
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createDocumentMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createDocumentMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		if (menuBar.getItems().isEmpty()) {

			menuBar.addItem("Overview", null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem("Charts", null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem("Indicators", null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem documentItem = menuBar.addItem("Document", null, null);

			documentItem.addItem("Document Activity", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem("Person references", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.PersonReferences.toString(), pageId));

			documentItem.addItem("Document details", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DocumentDetails.toString(), pageId));

			documentItem.addItem("Document data", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DocumentData.toString(), pageId));

			documentItem.addItem("Document References", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DocumentReferences.toString(), pageId));

			documentItem.addItem("Document Decision", null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DocumenDecision.toString(), pageId));

		}

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createCommitteeeMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createCommitteeeMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		if (menuBar.getItems().isEmpty()) {

			menuBar.addItem("Overview", null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem("Charts", null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem("Indicators", null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem("Roles", null, null);

			rolesItem.addItem("Current Members", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

			rolesItem.addItem("Member History", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem("RoleGhant", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem("Documents", null, null);

			documentItem.addItem("Document Activity", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem("Document history", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem("Ballots", null, null);

			ballotItem.addItem("Ballot Decision Summary", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.BallotDecisionSummary.toString(), pageId));

			ballotItem.addItem("Decision Summary", null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DecisionSummary.toString(), pageId));

			ballotItem.addItem("Decision Type Daily Summary", null, new PageModeMenuCommand(
					UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DecisionTypeDailySummary.toString(), pageId));

		}
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createCommitteeeRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createCommitteeeRankingMenuBar(final MenuBar menuBar) {
			if (!menuBar.getItems().isEmpty()) {
				menuBar.removeItems();
			}

			createCommitteeRankingTopics(menuBar.addItem("Committee Ranking", null,null));
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMinistryMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createMinistryMenuBar(final MenuBar menuBar, final String pageId) {

		menuBar.removeItems();

		if (menuBar.getItems().isEmpty()) {

			menuBar.addItem("Overview", null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem("Charts", null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem("Indicators", null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem("Roles", null, null);

			rolesItem.addItem("Current Members", null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.CurrentMembers.toString(), pageId));

			rolesItem.addItem("Member History", null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem("RoleGhant", null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem("Documents", null, null);

			documentItem.addItem("Document Activity", null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem("Document history", null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DocumentHistory.toString(), pageId));

		}

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMinistryRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createMinistryRankingMenuBar(final MenuBar menuBar) {
		if (!menuBar.getItems().isEmpty()) {
			menuBar.removeItems();
		}

		createMinistryRankingTopics(menuBar.addItem("Ministry Ranking", null,null));


	}

}

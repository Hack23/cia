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
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hack23.cia.model.internal.application.data.impl.ViewWorldbankIndicatorDataCountrySummary;
import com.hack23.cia.model.internal.application.data.impl.WorldbankIndicatorDataCountrySummaryEmbeddedId;
import com.hack23.cia.service.api.ApplicationManager;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ChartIndicators;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommitteePageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DocumentPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.MinistryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PartyPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PoliticianPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

@Service
public final class MenuItemFactoryImpl implements MenuItemFactory {

	/** The Constant CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT = "Current committees, current members";

	/** The Constant ALL_MINISTRIES_TOTAL_MEMBERS_TEXT. */
	private static final String ALL_MINISTRIES_TOTAL_MEMBERS_TEXT = "All ministries, total members";

	/** The Constant ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT. */
	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT = "All parties, total days served in ministries";

	/** The Constant CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT. */
	private static final String CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT = "Current parties active in ministries, head count";

	/** The Constant CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT = "Current ministries, current members";

	/**
	 * The Constant
	 * CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION.
	 */
	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION = "Current and past member and summary of total polticial days membership";

	/** The Constant POLITICAL_WORK_SUMMARY_TEXT. */
	private static final String POLITICAL_WORK_SUMMARY_TEXT = "Political Work Summary";

	/** The Constant DOCUMENT_HISTORY_TEXT. */
	private static final String DOCUMENT_HISTORY_TEXT = "Document history";

	/** The Constant INDICATORS_TEXT. */
	private static final String INDICATORS_TEXT = "Indicators";

	/** The Constant CHARTS_TEXT. */
	private static final String CHARTS_TEXT = "Charts";

	/** The Constant ROLE_GHANT_TEXT. */
	private static final String ROLE_GHANT_TEXT = "RoleGhant";

	/** The Constant MEMBER_HISTORY_TEXT. */
	private static final String MEMBER_HISTORY_TEXT = "Member History";

	/** The Constant CURRENT_MEMBERS_TEXT. */
	private static final String CURRENT_MEMBERS_TEXT = "Current Members";

	/** The Constant ROLES_TEXT. */
	private static final String ROLES_TEXT = "Roles";

	/** The Constant DOCUMENTS_TEXT. */
	private static final String DOCUMENTS_TEXT = "Documents";

	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

	/** The Constant DECISION_TYPE_DAILY_SUMMARY_TEXT. */
	private static final String DECISION_TYPE_DAILY_SUMMARY_TEXT = "Decision Type Daily Summary";

	/** The Constant DECISION_SUMMARY_TEXT. */
	private static final String DECISION_SUMMARY_TEXT = "Decision Summary";

	/** The Constant BALLOT_DECISION_SUMMARY_TEXT. */
	private static final String BALLOT_DECISION_SUMMARY_TEXT = "Ballot Decision Summary";

	/** The Constant BALLOTS_TEXT. */
	private static final String BALLOTS_TEXT = "Ballots";

	/** The Constant DOCUMENT_ACTIVITY_TEXT. */
	private static final String DOCUMENT_ACTIVITY_TEXT = "Document Activity";

	/** The Constant RANKING_LIST_BY_TOPIC_TEXT. */
	private static final String RANKING_LIST_BY_TOPIC_TEXT = "Ranking list by topic";

	/** The Constant CHART_BY_TOPIC_TEXT. */
	private static final String CHART_BY_TOPIC_TEXT = "Chart by topic";

	/** The Constant TEST_TEXT. */
	private static final String TEST_TEXT = "Test";

	/** The Constant DATA_SUMMARY_TEXT. */
	private static final String DATA_SUMMARY_TEXT = "Data Summary";

	/** The Constant AGENT_OPERATIONS_TEXT. */
	private static final String AGENT_OPERATIONS_TEXT = "Agent operations";

	/** The Constant OVERVIEW_TEXT. */
	private static final String OVERVIEW_TEXT = "Overview";

	/** The Constant RANKING_TEXT. */
	private static final String RANKING_TEXT = "Ranking";

	/** The Constant ADMIN_TEXT. */
	private static final String ADMIN_TEXT = "Admin";

	/** The application manager. */
	@Autowired
	@Qualifier("ApplicationManager")
	private transient ApplicationManager applicationManager;

	/** The Constant POLITICIAN_RANKING_LINK_TEXT. */
	private static final String POLITICIAN_RANKING_LINK_TEXT = "Politician Ranking";

	/** The Constant PARTY_RANKING_LINK_TEXT. */
	private static final String PARTY_RANKING_LINK_TEXT = "Party Ranking";

	/** The Constant COMMITTEE_RANKING_LINK_TEXT. */
	private static final String COMMITTEE_RANKING_LINK_TEXT = COMMITTEE_RANKING_TEXT;

	/** The Constant MINISTRY_RANKING_LINK_TEXT. */
	private static final String MINISTRY_RANKING_LINK_TEXT = "Ministry Ranking";

	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMainPageMenuBar()
	 */
	@Override
	public MenuBar createMainPageMenuBar() {
		final MenuBar barmenu = new MenuBar();

		barmenu.addItem(OVERVIEW_TEXT, null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.Overview));

		if (allowRoleInSecurityContext("ROLE_ADMIN")) {
			final MenuItem adminMenuItem = barmenu.addItem(ADMIN_TEXT, null, null);

			adminMenuItem.addItem(AGENT_OPERATIONS_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));
			adminMenuItem.addItem(DATA_SUMMARY_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

			adminMenuItem.addItem("System Performance",
					new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));

			adminMenuItem.addItem("Application Configuration",
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

			adminMenuItem.addItem("Agency",
					new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
			adminMenuItem.addItem("Portal",
					new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
			adminMenuItem.addItem("Application Session",
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
			adminMenuItem.addItem("Application Event",
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
			adminMenuItem.addItem("Useraccount",
					new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
			adminMenuItem.addItem("Country",
					new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
			adminMenuItem.addItem("Language",
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
			adminMenuItem.addItem("Language Content",
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));
		}


		if (allowRoleInSecurityContext("ROLE_ADMIN") || allowRoleInSecurityContext("ROLE_ADMIN")) {
			final MenuItem userhomeMenuItem = barmenu.addItem("Userhome", new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, ""));
		}

		final MenuItem rankingsMenuItem = barmenu.addItem(RANKING_TEXT, null, null);

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

		barmenu.addItem(TEST_TEXT, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Overview));

		barmenu.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PageVisitHistory));



		return barmenu;
	}

	/**
	 * Creates the ministry ranking topics.
	 *
	 * @param ministryMenuItem
	 *            the ministry menu item
	 */
	private static void createMinistryRankingTopics(final MenuItem ministryMenuItem) {

		ministryMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = ministryMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION);

		final MenuItem chartByTopic = ministryMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem(ALL_MINISTRIES_TOTAL_MEMBERS_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.Charts));

		ministryMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PageVisitHistory));

	}

	/**
	 * Creates the committee ranking topics.
	 *
	 * @param committeeMenuItem
	 *            the committee menu item
	 */
	private static void createCommitteeRankingTopics(final MenuItem committeeMenuItem) {
		committeeMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = committeeMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Current and past member and summary of polticial days ");

		final MenuItem chartByTopic = committeeMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,
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

		committeeMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PageVisitHistory));


	}

	/**
	 * Creates the party ranking topics.
	 *
	 * @param partynMenuItem
	 *            the partyn menu item
	 */
	private static void createPartyRankingTopics(final MenuItem partynMenuItem) {

		partynMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = partynMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem("Total members",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Party by total members, based on roles in departments, committees and parliament");

		final MenuItem chartByTopic = partynMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,
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

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total days served in committees",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));
		chartByTopic.addItem("All parties, total assignments in committees",
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.Charts));

		partynMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PageVisitHistory));


	}

	/**
	 * Creates the politician ranking topics.
	 *
	 * @param politicianMenuItem
	 *            the politician menu item
	 */
	private static void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
		politicianMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.Overview));

		final MenuItem listByTopic = politicianMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem("Political Experience Summary",
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DataGrid));
		listItem.setDescription("Current and past assignments and summary experience in days");

		final MenuItem chartByTopic = politicianMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		politicianMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PageVisitHistory));


	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createTestTopicMenu(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createTestTopicMenu(final MenuBar barmenu) {
		barmenu.addItem(OVERVIEW_TEXT, null, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.Overview));

		final MenuItem charts = barmenu.addItem(CHARTS_TEXT, null, null);

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


		barmenu.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.PageVisitHistory));

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

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPoliticianMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createPoliticianMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem("Total experience", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleSummary.toString(), pageId));

			rolesItem.addItem("RoleList", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleList.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DocumentHistory.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem("Vote history", null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.VoteHistory.toString(), pageId));

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.BallotDecisionSummary.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PageVisitHistory,pageId));

	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPoliticianRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createPoliticianRankingTopics(menuBar.addItem("Politician Ranking", null,null));
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPartyMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Charts, pageId));

			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem("Current Leaders", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CurrentLeaders.toString(), pageId));

			rolesItem.addItem("Leader History", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LeaderHistory.toString(), pageId));

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CurrentMembers.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem("Goverment Roles", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.GovernmentRoles.toString(), pageId));

			rolesItem.addItem("Committee Roles", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CommitteeRoles.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DocumentHistory.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem("Vote history", null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VoteHistory.toString(), pageId));

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CommitteeBallotDecisionSummary.toString(), pageId));

			ballotItem.addItem("Party Won Daily Summary Chart", null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.PartyWonDailySummaryChart.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PageVisitHistory,pageId));


	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createPartyRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createPartyRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createPartyRankingTopics(menuBar.addItem("Party Ranking", null,null));
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createDocumentMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createDocumentMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem documentItem = menuBar.addItem("Document", null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
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

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PageVisitHistory,pageId));

	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createCommitteeeMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createCommitteeeMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.BallotDecisionSummary.toString(), pageId));

			ballotItem.addItem(DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DecisionSummary.toString(), pageId));

			ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, null, new PageModeMenuCommand(
					UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DecisionTypeDailySummary.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PageVisitHistory,pageId));

	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createCommitteeeRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createCommitteeeRankingMenuBar(final MenuBar menuBar) {
			menuBar.removeItems();

			createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, null,null));
	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMinistryMenuBar(com.vaadin.ui.MenuBar, java.lang.String)
	 */
	@Override
	public void createMinistryMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Overview, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Charts, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.Indicators, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.CurrentMembers.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.MemberHistory.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.RoleGhant.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DocumentActivity.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DocumentHistory.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PageVisitHistory,pageId));


	}

	/** (non-Javadoc)
	 * @see com.hack23.cia.web.impl.ui.application.views.common.menufactory.MenuItemFactory#createMinistryRankingMenuBar(com.vaadin.ui.MenuBar)
	 */
	@Override
	public void createMinistryRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createMinistryRankingTopics(menuBar.addItem("Ministry Ranking", null,null));


	}

	@Override
	public void createUserHomeMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		menuBar.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.Overview, pageId));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.PageVisitHistory,pageId));

	}


	private static boolean allowRoleInSecurityContext(String role) {

		boolean result=false;

		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {

				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

				for (GrantedAuthority grantedAuthority : authorities) {
					if (role.equalsIgnoreCase(grantedAuthority.getAuthority())) {
						result=true;
					}
				}
			}
		}

		return result;
	}


}

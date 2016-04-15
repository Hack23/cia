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

/**
 * The Class MenuItemFactoryImpl.
 */
@Service
public final class MenuItemFactoryImpl implements MenuItemFactory {

	private static final String DOCUMENT_DATA = "Document data";

	private static final String DOCUMENT = "Document";

	private static final String PARTY_WON_DAILY_SUMMARY_CHART = "Party Won Daily Summary Chart";

	private static final String COMMITTEE_ROLES = "Committee Roles";

	private static final String GOVERMENT_ROLES = "Goverment Roles";

	private static final String LEADER_HISTORY = "Leader History";

	private static final String CURRENT_LEADERS = "Current Leaders";

	private static final String DOCUMENT_DETAILS = "Document details";

	private static final String POLITICIAN_RANKING = "Politician Ranking";

	private static final String PARTY_RANKING = "Party Ranking";

	private static final String VOTE_HISTORY = "Vote history";

	private static final String PERSON_REFERENCES = "Person references";

	private static final String ROLE_LIST = "RoleList";

	private static final String TOTAL_EXPERIENCE = "Total experience";

	private static final String BY_SOURCE = "By Source";

	private static final String DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE = "daily total of number of decsions made";

	private static final String BY_TOPIC = "ByTopic";

	private static final String COUNTRY_INDICATORS_SWEDEN = "Country Indicators, Sweden";

	private static final String DECISION_ACTIVITY_BY_TYPE = "Decision activity by type";

	private static final String DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS = "daily total of number published documents";

	private static final String DOCUMENT_ACTIVITY_BY_TYPE = "Document activity by type";

	private static final String DAILY_AVERAGE_WON_BALLOTS = "daily average % won ballots";

	private static final String PARTY_WINNER = "Party Winner";

	private static final String DOCUMENT_REFERENCES = "Document References";

	private static final String SWEDISH_PARLIAMENT_INDICATORS = "Swedish parliament Indicators";

	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT = "All parties, total days served in parliament";

	private static final String CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS = "Current and past assignments and summary experience in days";

	private static final String POLITICAL_EXPERIENCE_SUMMARY = "Political Experience Summary";

	private static final String ALL_PARTIES_HEAD_COUNT_IN_PARLIAMENT = "All parties, head count in parliament";

	private static final String CURRENT_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT = "Current parties, total days served in parliament";

	private static final String CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT = "Current parties active in parliament, head count";

	private static final String ALL_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "All committees,total days served in committees";

	private static final String ALL_PARTIES_TOTAL_ASSIGNMENTS_IN_COMMITTEES = "All parties, total assignments in committees";

	private static final String DOCUMENT_DECISION = "Document Decision";

	private static final String MINISTRY_RANKING = "Ministry Ranking";

	private static final String ALL_COMMITTEES_TOTAL_MEMBERS = "All committees, total members";

	private static final String CURRENT_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES = "Current parties, total days served in ministries";

	private static final String PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT = "Party by total members, based on roles in departments, committees and parliament";

	private static final String ROLE_USER = "ROLE_USER";

	private static final String USERHOME = "Userhome";

	private static final String ALL_PARTIES_TOTAL_ASSIGNMENTS = "All parties, total assignments";

	private static final String TOTAL_MEMBERS = "Total members";

	private static final String ALL_PARTIES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "All parties, total days served in committees";

	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES = "Current parties active in committees, total days served in committees";

	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS = "Current parties active in committees, current assignments";

	private static final String CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT = "Current parties active in committees, head count";

	private static final String CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS = "Current and past member and summary of polticial days ";

	private static final String LANGUAGE_CONTENT = "Language Content";

	private static final String LANGUAGE = "Language";

	private static final String COUNTRY = "Country";

	private static final String USERACCOUNT = "Useraccount";

	private static final String APPLICATION_EVENT = "Application Event";

	private static final String APPLICATION_SESSION = "Application Session";

	private static final String PORTAL = "Portal";

	private static final String AGENCY = "Agency";

	private static final String APPLICATION_CONFIGURATION = "Application Configuration";

	private static final String SYSTEM_PERFORMANCE = "System Performance";

	private static final String ROLE_ADMIN = "ROLE_ADMIN";

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
	private ApplicationManager applicationManager;

	/** The Constant POLITICIAN_RANKING_LINK_TEXT. */
	private static final String POLITICIAN_RANKING_LINK_TEXT = POLITICIAN_RANKING;

	/** The Constant PARTY_RANKING_LINK_TEXT. */
	private static final String PARTY_RANKING_LINK_TEXT = PARTY_RANKING;

	/** The Constant COMMITTEE_RANKING_LINK_TEXT. */
	private static final String COMMITTEE_RANKING_LINK_TEXT = COMMITTEE_RANKING_TEXT;

	/** The Constant MINISTRY_RANKING_LINK_TEXT. */
	private static final String MINISTRY_RANKING_LINK_TEXT = MINISTRY_RANKING;

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/**
	 * Instantiates a new menu item factory impl.
	 */
	public MenuItemFactoryImpl() {
		super();
	}

	@Override
	public MenuBar createMainPageMenuBar() {
		final MenuBar barmenu = new MenuBar();

		barmenu.addItem(OVERVIEW_TEXT, null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW));

		if (allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = barmenu.addItem(ADMIN_TEXT, null, null);

			adminMenuItem.addItem(AGENT_OPERATIONS_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));
			adminMenuItem.addItem(DATA_SUMMARY_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

			adminMenuItem.addItem(SYSTEM_PERFORMANCE,
					new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));

			adminMenuItem.addItem(APPLICATION_CONFIGURATION,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

			adminMenuItem.addItem(AGENCY,
					new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
			adminMenuItem.addItem(PORTAL,
					new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
			adminMenuItem.addItem(APPLICATION_SESSION,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
			adminMenuItem.addItem(APPLICATION_EVENT,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
			adminMenuItem.addItem(USERACCOUNT,
					new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));
			adminMenuItem.addItem(COUNTRY,
					new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
			adminMenuItem.addItem(LANGUAGE,
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
			adminMenuItem.addItem(LANGUAGE_CONTENT,
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));
		}


		if (allowRoleInSecurityContext(ROLE_ADMIN) || allowRoleInSecurityContext(ROLE_USER)) {
			barmenu.addItem(USERHOME, new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, ""));
		}

		final MenuItem rankingsMenuItem = barmenu.addItem(RANKING_TEXT, null, null);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		createMinistryRankingTopics(ministryMenuItem);

		barmenu.addItem(TEST_TEXT, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW));

		barmenu.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY));



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
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem listByTopic = ministryMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.DATAGRID));
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_TOTAL_POLTICIAL_DAYS_MEMBERSHIP_DESCRIPTION);

		final MenuItem chartByTopic = ministryMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_MINISTRIES_CURRENT_MEMBERS_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_MINISTRIES_TOTAL_MEMBERS_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.CHARTS));

		ministryMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));

	}

	/**
	 * Creates the committee ranking topics.
	 *
	 * @param committeeMenuItem
	 *            the committee menu item
	 */
	private static void createCommitteeRankingTopics(final MenuItem committeeMenuItem) {
		committeeMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem listByTopic = committeeMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_WORK_SUMMARY_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.DATAGRID));
		listItem.setDescription(CURRENT_AND_PAST_MEMBER_AND_SUMMARY_OF_POLTICIAL_DAYS);

		final MenuItem chartByTopic = committeeMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_COMMITTEES_CURRENT_MEMBERS_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_ASSIGNMENTS,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(ALL_COMMITTEES_TOTAL_MEMBERS,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.CHARTS));

		committeeMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));


	}

	/**
	 * Creates the party ranking topics.
	 *
	 * @param partynMenuItem
	 *            the partyn menu item
	 */
	private static void createPartyRankingTopics(final MenuItem partynMenuItem) {

		partynMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem listByTopic = partynMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(TOTAL_MEMBERS,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.DATAGRID));
		listItem.setDescription(PARTY_BY_TOTAL_MEMBERS_BASED_ON_ROLES_IN_DEPARTMENTS_COMMITTEES_AND_PARLIAMENT);

		final MenuItem chartByTopic = partynMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_MINISTRIES_HEAD_COUNT_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_HEAD_COUNT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_CURRENT_ASSIGNMENTS,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_COMMITTEES_TOTAL_DAYS_SERVED_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(CURRENT_PARTIES_ACTIVE_IN_PARLIAMENT_HEAD_COUNT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(CURRENT_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(ALL_PARTIES_HEAD_COUNT_IN_PARLIAMENT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_PARLIAMENT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_MINISTRIES_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_DAYS_SERVED_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));
		chartByTopic.addItem(ALL_PARTIES_TOTAL_ASSIGNMENTS_IN_COMMITTEES,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.CHARTS));

		partynMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));


	}

	/**
	 * Creates the politician ranking topics.
	 *
	 * @param politicianMenuItem
	 *            the politician menu item
	 */
	private static void createPoliticianRankingTopics(final MenuItem politicianMenuItem) {
		politicianMenuItem.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem listByTopic = politicianMenuItem.addItem(RANKING_LIST_BY_TOPIC_TEXT, null, null);

		final MenuItem listItem = listByTopic.addItem(POLITICAL_EXPERIENCE_SUMMARY,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.DATAGRID));
		listItem.setDescription(CURRENT_AND_PAST_ASSIGNMENTS_AND_SUMMARY_EXPERIENCE_IN_DAYS);

		politicianMenuItem.addItem(CHART_BY_TOPIC_TEXT, null, null);

		politicianMenuItem.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.PAGEVISITHISTORY));


	}

	@Override
	public void createTestTopicMenu(final MenuBar barmenu) {
		barmenu.addItem(OVERVIEW_TEXT, null, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW));

		final MenuItem charts = barmenu.addItem(CHARTS_TEXT, null, null);

		// Submenu item with a sub-submenu
		final MenuItem chartIndicators = charts.addItem(SWEDISH_PARLIAMENT_INDICATORS, null, null);
		final MenuItem addItem = chartIndicators.addItem(PARTY_WINNER, new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS, ChartIndicators.PARTYWINNER.toString()));
		addItem.setDescription(DAILY_AVERAGE_WON_BALLOTS);
		final MenuItem addItem2 = chartIndicators.addItem(DOCUMENT_ACTIVITY_BY_TYPE, new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DOCUMENTACTIVITYBYTYPE.toString()));
		addItem2.setDescription(DAILY_TOTAL_OF_NUMBER_PUBLISHED_DOCUMENTS);
		final MenuItem addItem3 = chartIndicators.addItem(DECISION_ACTIVITY_BY_TYPE, new PageModeMenuCommand(
				UserViews.TEST_CHART_VIEW_NAME, PageMode.CHARTS, ChartIndicators.DECSIONACTIVITYBYTYPE.toString()));
		addItem3.setDescription(DAILY_TOTAL_OF_NUMBER_OF_DECSIONS_MADE);

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
						topic -> new AbstractMap.SimpleEntry<>(topic,
								t)))

		.collect(Collectors.groupingBy(e -> e.getKey(), Collectors.mapping(v -> v.getValue(), Collectors.toList())));

		final MenuItem countryIndicators = charts.addItem(COUNTRY_INDICATORS_SWEDEN, null, null);

		final MenuItem byTopicItem = countryIndicators.addItem(BY_TOPIC, null);

		final MenuItem bySourceItem = countryIndicators.addItem(BY_SOURCE, null);

		addSourcesAndIndicatorsToMenu(byTopicItem, topicIndicatorMap);
		addSourcesAndIndicatorsToMenu(bySourceItem, sourceIndicatorMap);


		barmenu.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.PAGEVISITHISTORY));

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
				sourceItems.addItem(indciatorSummary.getIndicatorName(),
						new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.INDICATORS,
								indciatorSummary.getEmbeddedId().getIndicatorId()));
			}
		}

	}

	@Override
	public void createPoliticianMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.OVERVIEW, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.CHARTS, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.INDICATORS, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(TOTAL_EXPERIENCE, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.ROLESUMMARY.toString(), pageId));

			rolesItem.addItem(ROLE_LIST, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.ROLELIST.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.ROLEGHANT.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.DOCUMENTHISTORY.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem(VOTE_HISTORY, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.VOTEHISTORY.toString(), pageId));

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME,
					PoliticianPageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.POLITICIAN_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}

	@Override
	public void createPoliticianRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createPoliticianRankingTopics(menuBar.addItem(POLITICIAN_RANKING, null,null));
	}

	@Override
	public void createPartyMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.OVERVIEW, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.CHARTS, pageId));

			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.INDICATORS, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(CURRENT_LEADERS, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CURRENTLEADERS.toString(), pageId));

			rolesItem.addItem(LEADER_HISTORY, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.LEADERHISTORY.toString(), pageId));

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.CURRENTMEMBERS.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.MEMBERHISTORY.toString(), pageId));

			rolesItem.addItem(GOVERMENT_ROLES, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.GOVERNMENTROLES.toString(), pageId));

			rolesItem.addItem(COMMITTEE_ROLES, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.COMMITTEEROLES.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.DOCUMENTHISTORY.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem(VOTE_HISTORY, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PartyPageMode.VOTEHISTORY.toString(), pageId));

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.COMMITTEEBALLOTDECISIONSUMMARY.toString(), pageId));

			ballotItem.addItem(PARTY_WON_DAILY_SUMMARY_CHART, null, new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME,
					PartyPageMode.PARTYWONDAILYSUMMARYCHART.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.PARTY_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));


	}

	@Override
	public void createPartyRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createPartyRankingTopics(menuBar.addItem(PARTY_RANKING, null,null));
	}

	@Override
	public void createDocumentMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.OVERVIEW, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.CHARTS, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.INDICATORS, pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(PERSON_REFERENCES, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.PERSONREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DETAILS, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDETAILS.toString(), pageId));

			documentItem.addItem(DOCUMENT_DATA, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDATA.toString(), pageId));

			documentItem.addItem(DOCUMENT_REFERENCES, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTREFERENCES.toString(), pageId));

			documentItem.addItem(DOCUMENT_DECISION, null, new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME,
					DocumentPageMode.DOCUMENTDECISION.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.DOCUMENT_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}

	@Override
	public void createCommitteeeMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.OVERVIEW, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.CHARTS, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.INDICATORS, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.CURRENT_MEMBERS.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.MEMBERHISTORY.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.ROLEGHANT.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DOCUMENT_HISTORY.toString(), pageId));

			final MenuItem ballotItem = menuBar.addItem(BALLOTS_TEXT, null, null);

			ballotItem.addItem(BALLOT_DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.BALLOTDECISIONSUMMARY.toString(), pageId));

			ballotItem.addItem(DECISION_SUMMARY_TEXT, null, new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME,
					CommitteePageMode.DECISIONSUMMARY.toString(), pageId));

			ballotItem.addItem(DECISION_TYPE_DAILY_SUMMARY_TEXT, null, new PageModeMenuCommand(
					UserViews.COMMITTEE_VIEW_NAME, CommitteePageMode.DECISIONTYPEDAILYSUMMARY.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.COMMITTEE_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}

	@Override
	public void createCommitteeeRankingMenuBar(final MenuBar menuBar) {
			menuBar.removeItems();

			createCommitteeRankingTopics(menuBar.addItem(COMMITTEE_RANKING_TEXT, null,null));
	}

	@Override
	public void createMinistryMenuBar(final MenuBar menuBar, final String pageId) {
			menuBar.removeItems();

			menuBar.addItem(OVERVIEW_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.OVERVIEW, pageId));
			menuBar.addItem(CHARTS_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.CHARTS, pageId));
			menuBar.addItem(INDICATORS_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.INDICATORS, pageId));

			final MenuItem rolesItem = menuBar.addItem(ROLES_TEXT, null, null);

			rolesItem.addItem(CURRENT_MEMBERS_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.CURRENTMEMBERS.toString(), pageId));

			rolesItem.addItem(MEMBER_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.MEMBERHISTORY.toString(), pageId));

			rolesItem.addItem(ROLE_GHANT_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.ROLEGHANT.toString(), pageId));

			final MenuItem documentItem = menuBar.addItem(DOCUMENTS_TEXT, null, null);

			documentItem.addItem(DOCUMENT_ACTIVITY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DOCUMENTACTIVITY.toString(), pageId));

			documentItem.addItem(DOCUMENT_HISTORY_TEXT, null, new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME,
					MinistryPageMode.DOCUMENTHISTORY.toString(), pageId));

			menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
					new PageModeMenuCommand(UserViews.MINISTRY_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));


	}

	@Override
	public void createMinistryRankingMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();

		createMinistryRankingTopics(menuBar.addItem(MINISTRY_RANKING, null,null));


	}

	@Override
	public void createUserHomeMenuBar(final MenuBar menuBar, final String pageId) {
		menuBar.removeItems();

		menuBar.addItem(OVERVIEW_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.OVERVIEW, pageId));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, PageMode.PAGEVISITHISTORY,pageId));

	}


	/**
	 * Allow role in security context.
	 *
	 * @param role
	 *            the role
	 * @return true, if successful
	 */
	private static boolean allowRoleInSecurityContext(final String role) {

		boolean result=false;

		final SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			final Authentication authentication = context.getAuthentication();
			if (authentication != null) {

				final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

				for (final GrantedAuthority grantedAuthority : authorities) {
					if (role.equalsIgnoreCase(grantedAuthority.getAuthority())) {
						result=true;
					}
				}
			}
		}

		return result;
	}


}

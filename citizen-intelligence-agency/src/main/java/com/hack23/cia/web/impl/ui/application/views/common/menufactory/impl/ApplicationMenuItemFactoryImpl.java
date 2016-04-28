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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.CommitteeRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MinistryRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PartyRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.PoliticianRankingMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class MenuItemFactoryImpl.
 */
@Service
public final class ApplicationMenuItemFactoryImpl implements ApplicationMenuItemFactory {

	/** The Constant POLITICIAN_RANKING. */
	private static final String POLITICIAN_RANKING = "Politician Ranking";

	/** The Constant PARTY_RANKING. */
	private static final String PARTY_RANKING = "Party Ranking";

	/** The Constant MINISTRY_RANKING. */
	private static final String MINISTRY_RANKING = "Ministry Ranking";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant USERHOME. */
	private static final String USERHOME = "Userhome";

	/** The Constant LANGUAGE_CONTENT. */
	private static final String LANGUAGE_CONTENT = "Language Content";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "Language";

	/** The Constant COUNTRY. */
	private static final String COUNTRY = "Country";

	/** The Constant USERACCOUNT. */
	private static final String USERACCOUNT = "Useraccount";

	/** The Constant APPLICATION_EVENT. */
	private static final String APPLICATION_EVENT = "Application Event";

	/** The Constant APPLICATION_SESSION. */
	private static final String APPLICATION_SESSION = "Application Session";

	/** The Constant PORTAL. */
	private static final String PORTAL = "Portal";

	/** The Constant AGENCY. */
	private static final String AGENCY = "Agency";

	/** The Constant APPLICATION_CONFIGURATION. */
	private static final String APPLICATION_CONFIGURATION = "Application Configuration";

	/** The Constant SYSTEM_PERFORMANCE. */
	private static final String SYSTEM_PERFORMANCE = "System Performance";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";


	/** The Constant COMMITTEE_RANKING_TEXT. */
	private static final String COMMITTEE_RANKING_TEXT = "Committee Ranking";

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


	@Autowired
	private PoliticianRankingMenuItemFactory politicianRankingMenuItemFactory;

	@Autowired
	private PartyRankingMenuItemFactory partyRankingMenuItemFactory;

	@Autowired
	private CommitteeRankingMenuItemFactory committeeRankingMenuItemFactory;

	@Autowired
	private MinistryRankingMenuItemFactory ministryRankingMenuItemFactory;


	/**
	 * Instantiates a new application menu item factory impl.
	 */
	public ApplicationMenuItemFactoryImpl() {
		super();
	}

	@Override
	public MenuBar createMainPageMenuBar(final MenuBar menuBar) {
		final MenuItem mainViewItem = menuBar.addItem("Application", null, null);

		mainViewItem.addItem(OVERVIEW_TEXT, null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW));

		if (allowRoleInSecurityContext(ROLE_ADMIN) || allowRoleInSecurityContext(ROLE_USER)) {
			mainViewItem.addItem("Logout", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGOUT.toString()));
		} else {
			mainViewItem.addItem("Login", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));
			mainViewItem.addItem("Register", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		}


		if (allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = menuBar.addItem(ADMIN_TEXT, null, null);

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
			menuBar.addItem(USERHOME, new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, ""));
		}

		final MenuItem rankingsMenuItem = menuBar.addItem(RANKING_TEXT, null, null);

		final MenuItem politicianMenuItem = rankingsMenuItem.addItem(POLITICIAN_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.POLITICIAN_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		politicianRankingMenuItemFactory.createPoliticianRankingTopics(politicianMenuItem);

		final MenuItem partynMenuItem = rankingsMenuItem.addItem(PARTY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.PARTY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		partyRankingMenuItemFactory.createPartyRankingTopics(partynMenuItem);

		final MenuItem committeeMenuItem = rankingsMenuItem.addItem(COMMITTEE_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.COMMITTEE_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		committeeRankingMenuItemFactory.createCommitteeRankingTopics(committeeMenuItem);

		final MenuItem ministryMenuItem = rankingsMenuItem.addItem(MINISTRY_RANKING_LINK_TEXT,
				new PageModeMenuCommand(UserViews.MINISTRY_RANKING_VIEW_NAME, PageMode.OVERVIEW));

		ministryRankingMenuItemFactory.createMinistryRankingTopics(ministryMenuItem);

		menuBar.addItem(TEST_TEXT, new PageModeMenuCommand(UserViews.TEST_CHART_VIEW_NAME, PageMode.OVERVIEW));

		menuBar.addItem(PAGE_VISIT_HISTORY_TEXT, null,
				new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.PAGEVISITHISTORY));



		return menuBar;
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

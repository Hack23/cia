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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class AbstractMenuItemFactoryImpl.
 */
public abstract class AbstractMenuItemFactoryImpl  {

	private static final String USER_ACTIVITY = "User Activity";

	private static final String MANAGEMENT = "Management";

	private static final String CONFIGURATION = "Configuration";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The Constant OVERVIEW_TEXT. */
	private static final String START_TEXT = "Start";

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
	private static final String APPLICATION_CONFIGURATION = "System settings";

	/** The Constant SYSTEM_PERFORMANCE. */
	private static final String SYSTEM_PERFORMANCE = "System Performance";

	/** The Constant DATA_SUMMARY_TEXT. */
	private static final String DATA_SUMMARY_TEXT = "Data Summary";

	/** The Constant AGENT_OPERATIONS_TEXT. */
	private static final String AGENT_OPERATIONS_TEXT = "Agent operations";

	/** The Constant ADMIN_TEXT. */
	private static final String ADMIN_TEXT = "Admin";



	/**
	 * Instantiates a new abstract menu item factory impl.
	 */
	public AbstractMenuItemFactoryImpl() {
		super();
	}

	protected final void initApplicationMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();
		final MenuItem mainViewItem = menuBar.addItem("Application", null, null);

		mainViewItem.addItem(START_TEXT, null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, PageMode.OVERVIEW));

		if (allowRoleInSecurityContext(ROLE_ADMIN) || allowRoleInSecurityContext(ROLE_USER)) {
			mainViewItem.addItem(USERHOME, new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, ""));
			createAdminMenu(mainViewItem);
			mainViewItem.addItem("Logout", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGOUT.toString()));
		} else {
			mainViewItem.addItem("Login", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.LOGIN.toString()));
			mainViewItem.addItem("Register", null, new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME, ApplicationPageMode.REGISTER.toString()));
		}


	}

	private void createAdminMenu(final MenuItem mainViewItem) {
		if (allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = mainViewItem.addItem(ADMIN_TEXT, null, null);


			final MenuItem configurationMenuItem = adminMenuItem.addItem(CONFIGURATION, null, null);
			configurationMenuItem.addItem(APPLICATION_CONFIGURATION,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, ""));

			configurationMenuItem.addItem(AGENCY,
					new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, ""));
			configurationMenuItem.addItem(PORTAL,
					new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, ""));
			configurationMenuItem.addItem(COUNTRY,
					new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, ""));
			configurationMenuItem.addItem(LANGUAGE,
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME, ""));
			configurationMenuItem.addItem(LANGUAGE_CONTENT,
					new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, ""));

			final MenuItem managementMenuItem = adminMenuItem.addItem(MANAGEMENT, null, null);

			managementMenuItem.addItem(AGENT_OPERATIONS_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, ""));
			managementMenuItem.addItem(DATA_SUMMARY_TEXT,
					new PageModeMenuCommand(AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, ""));

			managementMenuItem.addItem(SYSTEM_PERFORMANCE,
					new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME, ""));



			final MenuItem userActivityMenuItem = adminMenuItem.addItem(USER_ACTIVITY, null, null);
			userActivityMenuItem.addItem(APPLICATION_SESSION,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, ""));
			userActivityMenuItem.addItem(APPLICATION_EVENT,
					new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, ""));
			userActivityMenuItem.addItem(USERACCOUNT,
					new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME, ""));

		}
	}

	/**
	 * Allow role in security context.
	 *
	 * @param role
	 *            the role
	 * @return true, if successful
	 */
	protected static boolean allowRoleInSecurityContext(final String role) {

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

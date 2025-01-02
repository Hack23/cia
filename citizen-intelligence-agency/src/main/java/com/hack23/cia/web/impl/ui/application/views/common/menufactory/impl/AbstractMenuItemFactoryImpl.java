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
package com.hack23.cia.web.impl.ui.application.views.common.menufactory.impl;

import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.DataSummaryPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

/**
 * The Class AbstractMenuItemFactoryImpl.
 *
 * This abstract class provides common functionality for creating menu items and button links
 * in the Citizen Intelligence Agency web application. It includes methods for initializing
 * application menus, creating admin menus, and generating button links with descriptions.
 */
public abstract class AbstractMenuItemFactoryImpl {

	/** The Constant DASHBOARD. */
	private static final String DASHBOARD = "Dashboard";

	/** The Constant ADMIN_TEXT. */
	private static final String ADMIN_TEXT = "Admin";

	/** The Constant AGENCY. */
	private static final String AGENCY = "Agency";

	/** The Constant AGENT_OPERATIONS_TEXT. */
	private static final String AGENT_OPERATIONS_TEXT = "Agent operations";

	/** The Constant APPLICATION. */
	private static final String APPLICATION = "Application";

	/** The Constant APPLICATION_CONFIGURATION. */
	private static final String APPLICATION_CONFIGURATION = "System settings";

	/** The Constant APPLICATION_EVENT. */
	private static final String APPLICATION_EVENT = "Application Event";

	/** The Constant APPLICATION_EVENT_CHARTS. */
	private static final String APPLICATION_EVENT_CHARTS = "Application Event charts";

	/** The Constant APPLICATION_SESSION. */
	private static final String APPLICATION_SESSION = "Application Session";

	/** The Constant APPLICATION_SESSION_CHARTS. */
	private static final String APPLICATION_SESSION_CHARTS = "Active Daily Users";

	/** The Constant COMMAND_AGENCY. */
	private static final PageModeMenuCommand COMMAND_AGENCY = new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, "");

	/** The Constant COMMAND_AGENT_OPERATION. */
	private static final PageModeMenuCommand COMMAND_AGENT_OPERATION = new PageModeMenuCommand(
			AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_CONFIGURATION. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_CONFIGURATION = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_EVENTS. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_EVENTS = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_EVENTS_CHARTS. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);

	/** The Constant COMMAND_APPLICATION_SESSION. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_SESSION = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_SESSION_CHARTS. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_SESSION_CHARTS = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, PageMode.CHARTS);

	/** The Constant COMMAND_AUTHOR_DATASUMMARY. */
	private static final PageModeMenuCommand COMMAND_AUTHOR_DATASUMMARY = new PageModeMenuCommand(
			AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, DataSummaryPageMode.AUTHORS.toString());

	/** The Constant COMMAND_COUNTRY. */
	private static final PageModeMenuCommand COMMAND_COUNTRY = new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, "");

	/** The Constant COMMAND_DATASUMMARY. */
	private static final PageModeMenuCommand COMMAND_DATASUMMARY = new PageModeMenuCommand(
			AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, "");

	/** The Constant COMMAND_EMAIL. */
	private static final PageModeMenuCommand COMMAND_EMAIL = new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME,
			"");

	/** The Constant COMMAND_LANGUAGE. */
	private static final PageModeMenuCommand COMMAND_LANGUAGE = new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME,
			"");

	/** The Constant COMMAND_LOGIN. */
	private static final PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.LOGIN.toString());

	/** The Constant COMMAND_LOGOUT. */
	private static final PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.LOGOUT.toString());

	/** The Constant COMMAND_MAINVIEW_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			PageMode.OVERVIEW);


	/** The Constant COMMAND_DASHBOARDVIEW_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_DASHBOARDVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.DASHBOARD_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant COMMAND_MAINVIEW_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_MONITORING. */
	private static final PageModeMenuCommand COMMAND_MONITORING = new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME,
			"");

	/** The Constant COMMAND_PORTAL. */
	private static final PageModeMenuCommand COMMAND_PORTAL = new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, "");

	/** The Constant COMMAND_REGISTER. */
	private static final PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.REGISTER.toString());

	/** The Constant COMMAND_USERACCOUNT. */
	private static final PageModeMenuCommand COMMAND_USERACCOUNT = new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME,
			"");

	/** The Constant COMMAND_USERHOME. */
	private static final PageModeMenuCommand COMMAND_USERHOME = new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, "");

	/** The Constant CONFIGURATION. */
	private static final String CONFIGURATION = "Configuration";

	/** The Constant COUNTRY. */
	private static final String COUNTRY = "Country";

	/** The Constant DATA_AUTHOR_SUMMARY. */
	private static final String DATA_AUTHOR_SUMMARY = "Data author summary";

	/** The Constant DATA_SUMMARY_TEXT. */
	private static final String DATA_SUMMARY_TEXT = "Data Summary";

	/** The Constant DISPLAY_SIZE_LG_DEVICE. */
	private static final int DISPLAY_SIZE_LG_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_MD_DEVICE. */
	private static final int DISPLAY_SIZE_MD_DEVICE = 4;

	/** The Constant DISPLAY_SIZE_XS_DEVICE. */
	private static final int DISPLAY_SIZE_XS_DEVICE = 12;

	/** The Constant DISPLAYS_SIZE_XM_DEVICE. */
	private static final int DISPLAYS_SIZE_XM_DEVICE = 6;

	/** The Constant EMAIL. */
	private static final String EMAIL = "Email";

	/** The Constant HEADER_STYLE_NAME. */
	private static final String HEADER_STYLE_NAME = "Header";

	/** The Constant LANGUAGE. */
	private static final String LANGUAGE = "Language";

	/** The Constant LINK_STYLE_NAME. */
	private static final String LINK_STYLE_NAME = "link";

	/** The Constant LOGIN. */
	private static final String LOGIN = "Login";

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant MAIN. */
	private static final String MAIN = "Main";

	/** The Constant MANAGEMENT. */
	private static final String MANAGEMENT = "Management";

	/** The Constant MENU_BAR_WIDTH. */
	private static final String MENU_BAR_WIDTH = "80%";

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant PORTAL. */
	private static final String PORTAL = "Portal";

	/** The Constant REGISTER. */
	private static final String REGISTER = "Register";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant START_TEXT. */
	private static final String START_TEXT = "Start";

	/** The Constant SYSTEM_PERFORMANCE. */
	private static final String SYSTEM_PERFORMANCE = "System Performance";

	/** The Constant USER_ACTIVITY. */
	private static final String USER_ACTIVITY = "User Activity";

	/** The Constant USERACCOUNT. */
	private static final String USERACCOUNT = "Useraccount";

	/** The Constant USERHOME. */
	private static final String USERHOME = "Userhome";




	/**
	 * Instantiates a new abstract menu item factory impl.
	 */
	public AbstractMenuItemFactoryImpl() {
		super();
	}

	/**
	 * Creates the admin menu.
	 *
	 * @param mainViewItem
	 *            the main view item
	 */
	private static void createAdminMenu(final MenuItem mainViewItem) {
		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = mainViewItem.addItem(ADMIN_TEXT, VaadinIcons.SERVER, null);

			final MenuItem configurationMenuItem = adminMenuItem.addItem(CONFIGURATION, VaadinIcons.TOOLS, null);
			configurationMenuItem.addItem(APPLICATION_CONFIGURATION,VaadinIcons.TOOLS, COMMAND_APPLICATION_CONFIGURATION);

			configurationMenuItem.addItem(AGENCY,VaadinIcons.SERVER, COMMAND_AGENCY);
			configurationMenuItem.addItem(PORTAL, VaadinIcons.SITEMAP,COMMAND_PORTAL);
			configurationMenuItem.addItem(COUNTRY,VaadinIcons.FLAG, COMMAND_COUNTRY);
			configurationMenuItem.addItem(LANGUAGE,VaadinIcons.ACCESSIBILITY, COMMAND_LANGUAGE);

			final MenuItem managementMenuItem = adminMenuItem.addItem(MANAGEMENT, VaadinIcons.SERVER, null);

			managementMenuItem.addItem(AGENT_OPERATIONS_TEXT,VaadinIcons.BRIEFCASE, COMMAND_AGENT_OPERATION);

			managementMenuItem.addItem(DATA_SUMMARY_TEXT,VaadinIcons.DATABASE, COMMAND_DATASUMMARY);
			managementMenuItem.addItem(DATA_AUTHOR_SUMMARY,VaadinIcons.DATABASE, COMMAND_AUTHOR_DATASUMMARY);


			managementMenuItem.addItem(EMAIL,VaadinIcons.MAILBOX, COMMAND_EMAIL);


			managementMenuItem.addItem(SYSTEM_PERFORMANCE,VaadinIcons.DASHBOARD, COMMAND_MONITORING);

			final MenuItem userActivityMenuItem = adminMenuItem.addItem(USER_ACTIVITY, VaadinIcons.DATABASE, null);
			userActivityMenuItem.addItem(APPLICATION_SESSION,VaadinIcons.LAPTOP, COMMAND_APPLICATION_SESSION);
			userActivityMenuItem.addItem(APPLICATION_SESSION_CHARTS,VaadinIcons.LAPTOP, COMMAND_APPLICATION_SESSION_CHARTS);
			userActivityMenuItem.addItem(APPLICATION_EVENT,VaadinIcons.ARROWS, COMMAND_APPLICATION_EVENTS);
			userActivityMenuItem.addItem(APPLICATION_EVENT_CHARTS,VaadinIcons.ARROWS, COMMAND_APPLICATION_EVENTS_CHARTS);

			userActivityMenuItem.addItem(USERACCOUNT,VaadinIcons.GROUP, COMMAND_USERACCOUNT);

		}
	}

	/**
	 * Creates the button link.
	 *
	 * @param row
	 *            the panel content
	 * @param linkText
	 *            the link text
	 * @param icon
	 *            the icon
	 * @param command
	 *            the command
	 * @param description
	 *            the description
	 */
	protected static final void createButtonLink(final ResponsiveRow row,final String linkText,final Resource icon, final ClickListener command, final String description) {
		final CssLayout layout = new CssLayout();
		layout.addStyleName("v-layout-content-overview-panel-level2");
		Responsive.makeResponsive(layout);
		layout.setSizeUndefined();


		final Button button = new Button(linkText);
		Responsive.makeResponsive(button);
		button.setStyleName(LINK_STYLE_NAME);
		button.addStyleName("title");
		button.addClickListener(command);
		button.setIcon(icon);
		button.setWidth(100, Unit.PERCENTAGE);


		layout.addComponent(button);

		final Label descriptionLabel = new Label(description);
		descriptionLabel.addStyleName("itembox");
		Responsive.makeResponsive(descriptionLabel);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(descriptionLabel);

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE,DISPLAYS_SIZE_XM_DEVICE,DISPLAY_SIZE_MD_DEVICE,DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Inits the application menu bar.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	protected static final void initApplicationMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();
		menuBar.setWidth(MENU_BAR_WIDTH);
		menuBar.setStyleName(HEADER_STYLE_NAME);
		final MenuItem mainViewItem = menuBar.addItem(APPLICATION, VaadinIcons.SERVER, null);

		mainViewItem.addItem(START_TEXT, VaadinIcons.HOME, COMMAND_MAINVIEW_OVERVIEW);

		mainViewItem.addItem(DASHBOARD, VaadinIcons.DASHBOARD, COMMAND_DASHBOARDVIEW_OVERVIEW);

		final MenuItem mainItem = mainViewItem.addItem(MAIN, VaadinIcons.STAR, null);

		mainItem.addItem(PAGE_VISIT_HISTORY_TEXT, VaadinIcons.CHART, COMMAND_MAINVIEW_PAGEVISITHISTORY);


		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN) || UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {
			mainViewItem.addItem(USERHOME, VaadinIcons.USER,COMMAND_USERHOME);
			createAdminMenu(mainViewItem);
			mainViewItem.addItem(LOGOUT, VaadinIcons.SIGN_OUT, COMMAND_LOGOUT);
		} else {
			mainViewItem.addItem(LOGIN, VaadinIcons.SIGN_IN, COMMAND_LOGIN);
			mainViewItem.addItem(REGISTER, VaadinIcons.FILE_ADD, COMMAND_REGISTER);
		}

	}

}

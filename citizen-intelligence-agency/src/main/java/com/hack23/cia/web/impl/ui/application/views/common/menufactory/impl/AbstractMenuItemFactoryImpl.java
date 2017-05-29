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

import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageModeMenuCommand;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.ApplicationPageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.CommonsViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.UserViews;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class AbstractMenuItemFactoryImpl.
 */
public abstract class AbstractMenuItemFactoryImpl {

	/** The Constant COMMAND_APPLICATION_EVENTS_CHARTS. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_EVENTS_CHARTS = new PageModeMenuCommand(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, PageMode.CHARTS);

	/** The Constant COMMAND_EMAIL. */
	private static final PageModeMenuCommand COMMAND_EMAIL = new PageModeMenuCommand(AdminViews.ADMIN_EMAIL_VIEW_NAME,
			"");

	/** The Constant COMMAND_MAINVIEW_PAGEVISITHISTORY. */
	private static final PageModeMenuCommand COMMAND_MAINVIEW_PAGEVISITHISTORY = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			PageMode.PAGEVISITHISTORY);

	/** The Constant COMMAND_USERACCOUNT. */
	private static final PageModeMenuCommand COMMAND_USERACCOUNT = new PageModeMenuCommand(AdminViews.ADMIN_USERACCOUNT_VIEW_NAME,
			"");

	/** The Constant COMMAND_APPLICATION_EVENTS. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_EVENTS = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_SESSION. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_SESSION = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "");

	/** The Constant COMMAND_MONITORING. */
	private static final PageModeMenuCommand COMMAND_MONITORING = new PageModeMenuCommand(AdminViews.ADMIN_MONITORING_VIEW_NAME,
			"");

	/** The Constant COMMAND_DATASUMMARY. */
	private static final PageModeMenuCommand COMMAND_DATASUMMARY = new PageModeMenuCommand(
			AdminViews.ADMIN_DATA_SUMMARY_VIEW_NAME, "");

	/** The Constant COMMAND_AGENT_OPERATION. */
	private static final PageModeMenuCommand COMMAND_AGENT_OPERATION = new PageModeMenuCommand(
			AdminViews.ADMIN_AGENT_OPERATIONVIEW_NAME, "");

	/** The Constant COMMAND_LANGUAGE_CONTENT. */
	private static final PageModeMenuCommand COMMAND_LANGUAGE_CONTENT = new PageModeMenuCommand(
			AdminViews.ADMIN_LANGUAGE_CONTENT_VIEW_NAME, "");

	/** The Constant COMMAND_LANGUAGE. */
	private static final PageModeMenuCommand COMMAND_LANGUAGE = new PageModeMenuCommand(AdminViews.ADMIN_LANGUAGE_VIEW_NAME,
			"");

	/** The Constant COMMAND_COUNTRY. */
	private static final PageModeMenuCommand COMMAND_COUNTRY = new PageModeMenuCommand(AdminViews.ADMIN_COUNTRY_VIEW_NAME, "");

	/** The Constant COMMAND_PORTAL. */
	private static final PageModeMenuCommand COMMAND_PORTAL = new PageModeMenuCommand(AdminViews.ADMIN_PORTAL_VIEW_NAME, "");

	/** The Constant COMMAND_AGENCY. */
	private static final PageModeMenuCommand COMMAND_AGENCY = new PageModeMenuCommand(AdminViews.ADMIN_AGENCY_VIEW_NAME, "");

	/** The Constant COMMAND_APPLICATION_CONFIGURATION. */
	private static final PageModeMenuCommand COMMAND_APPLICATION_CONFIGURATION = new PageModeMenuCommand(
			AdminViews.ADMIN_APPLICATIONS_CONFIGURATION_VIEW_NAME, "");

	/** The Constant COMMAND_REGISTER. */
	private static final PageModeMenuCommand COMMAND_REGISTER = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.REGISTER.toString());

	/** The Constant COMMAND_LOGIN. */
	private static final PageModeMenuCommand COMMAND_LOGIN = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.LOGIN.toString());

	/** The Constant COMMAND_LOGOUT. */
	private static final PageModeMenuCommand COMMAND_LOGOUT = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			ApplicationPageMode.LOGOUT.toString());

	/** The Constant COMMAND_USERHOME. */
	private static final PageModeMenuCommand COMMAND_USERHOME = new PageModeMenuCommand(UserViews.USERHOME_VIEW_NAME, "");

	/** The Constant COMMAND_MAINVIEW_OVERVIEW. */
	private static final PageModeMenuCommand COMMAND_MAINVIEW_OVERVIEW = new PageModeMenuCommand(CommonsViews.MAIN_VIEW_NAME,
			PageMode.OVERVIEW);

	/** The Constant LINK_STYLE_NAME. */
	private static final String LINK_STYLE_NAME = "link";

	/** The Constant HEADER_STYLE_NAME. */
	private static final String HEADER_STYLE_NAME = "Header";

	/** The Constant MENU_BAR_WIDTH. */
	private static final String MENU_BAR_WIDTH = "80%";

	/** The Constant MAIN. */
	private static final String MAIN = "Main";

	/** The Constant REGISTER. */
	private static final String REGISTER = "Register";

	/** The Constant LOGIN. */
	private static final String LOGIN = "Login";

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant APPLICATION. */
	private static final String APPLICATION = "Application";

	/** The Constant EMAIL. */
	private static final String EMAIL = "Email";

	/** The Constant USER_ACTIVITY. */
	private static final String USER_ACTIVITY = "User Activity";

	/** The Constant MANAGEMENT. */
	private static final String MANAGEMENT = "Management";

	/** The Constant CONFIGURATION. */
	private static final String CONFIGURATION = "Configuration";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The Constant START_TEXT. */
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

	/** The Constant PAGE_VISIT_HISTORY_TEXT. */
	private static final String PAGE_VISIT_HISTORY_TEXT = "Page Visit History";

	/** The Constant APPLICATION_EVENT_CHARTS. */
	private static final String APPLICATION_EVENT_CHARTS = "Application Event charts";




	/**
	 * Instantiates a new abstract menu item factory impl.
	 */
	public AbstractMenuItemFactoryImpl() {
		super();
	}

	/**
	 * Inits the application menu bar.
	 *
	 * @param menuBar
	 *            the menu bar
	 */
	protected final void initApplicationMenuBar(final MenuBar menuBar) {
		menuBar.removeItems();
		menuBar.setWidth(MENU_BAR_WIDTH);
		menuBar.setStyleName(HEADER_STYLE_NAME);
		final MenuItem mainViewItem = menuBar.addItem(APPLICATION, FontAwesome.SERVER, null);

		mainViewItem.addItem(START_TEXT, FontAwesome.STAR, COMMAND_MAINVIEW_OVERVIEW);

		final MenuItem mainItem = mainViewItem.addItem(MAIN, FontAwesome.STAR, null);

		mainItem.addItem(PAGE_VISIT_HISTORY_TEXT, FontAwesome.AREA_CHART, COMMAND_MAINVIEW_PAGEVISITHISTORY);


		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN) || UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {
			mainViewItem.addItem(USERHOME, FontAwesome.USER,COMMAND_USERHOME);
			createAdminMenu(mainViewItem);
			mainViewItem.addItem(LOGOUT, FontAwesome.SIGN_OUT, COMMAND_LOGOUT);
		} else {
			mainViewItem.addItem(LOGIN, FontAwesome.SIGN_IN, COMMAND_LOGIN);
			mainViewItem.addItem(REGISTER, FontAwesome.USER_PLUS, COMMAND_REGISTER);
		}

	}

	/**
	 * Creates the admin menu.
	 *
	 * @param mainViewItem
	 *            the main view item
	 */
	private static void createAdminMenu(final MenuItem mainViewItem) {
		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = mainViewItem.addItem(ADMIN_TEXT, FontAwesome.SERVER, null);

			final MenuItem configurationMenuItem = adminMenuItem.addItem(CONFIGURATION, FontAwesome.GEARS, null);
			configurationMenuItem.addItem(APPLICATION_CONFIGURATION,FontAwesome.GEARS, COMMAND_APPLICATION_CONFIGURATION);

			configurationMenuItem.addItem(AGENCY,FontAwesome.SERVER, COMMAND_AGENCY);
			configurationMenuItem.addItem(PORTAL, FontAwesome.SITEMAP,COMMAND_PORTAL);
			configurationMenuItem.addItem(COUNTRY,FontAwesome.FLAG, COMMAND_COUNTRY);
			configurationMenuItem.addItem(LANGUAGE,FontAwesome.LANGUAGE, COMMAND_LANGUAGE);
			configurationMenuItem.addItem(LANGUAGE_CONTENT,FontAwesome.FILE_TEXT, COMMAND_LANGUAGE_CONTENT);

			final MenuItem managementMenuItem = adminMenuItem.addItem(MANAGEMENT, FontAwesome.SERVER, null);

			managementMenuItem.addItem(AGENT_OPERATIONS_TEXT,FontAwesome.USER_SECRET, COMMAND_AGENT_OPERATION);

			managementMenuItem.addItem(DATA_SUMMARY_TEXT,FontAwesome.DATABASE, COMMAND_DATASUMMARY);

			managementMenuItem.addItem(EMAIL,FontAwesome.MAIL_FORWARD, COMMAND_EMAIL);


			managementMenuItem.addItem(SYSTEM_PERFORMANCE,FontAwesome.DASHBOARD, COMMAND_MONITORING);

			final MenuItem userActivityMenuItem = adminMenuItem.addItem(USER_ACTIVITY, FontAwesome.DATABASE, null);
			userActivityMenuItem.addItem(APPLICATION_SESSION,FontAwesome.LAPTOP, COMMAND_APPLICATION_SESSION);
			userActivityMenuItem.addItem(APPLICATION_EVENT,FontAwesome.ARROWS, COMMAND_APPLICATION_EVENTS);
			userActivityMenuItem.addItem(APPLICATION_EVENT_CHARTS,FontAwesome.ARROWS, COMMAND_APPLICATION_EVENTS_CHARTS);

			userActivityMenuItem.addItem(USERACCOUNT,FontAwesome.GROUP, COMMAND_USERACCOUNT);

		}
	}

	/**
	 * Creates the button link.
	 *
	 * @param panelContent
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
	protected final void createButtonLink(final GridLayout panelContent,final String linkText,final Resource icon, final ClickListener command, final String description) {
		final GridLayout grid = new GridLayout(1, 2);
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setHeight(100, Unit.PERCENTAGE);
		grid.addStyleName("v-grid-layout-content-overview-panel");

		final Button button = new Button(linkText);
		button.setStyleName(LINK_STYLE_NAME);
		button.addClickListener(command);
		button.setWidth(100, Unit.PERCENTAGE);

		button.setIcon(icon);
		button.setWidth(100, Unit.PERCENTAGE);


		grid.addComponent(button, 0, 0);
		grid.setComponentAlignment(button, Alignment.TOP_LEFT);

		final Label descriptionLabel = new Label(description);
		descriptionLabel.setWidth(100, Unit.PERCENTAGE);
		grid.addComponent(descriptionLabel,0,1);
		grid.setComponentAlignment(descriptionLabel, Alignment.TOP_LEFT);


		panelContent.addComponent(grid);
		panelContent.setComponentAlignment(grid, Alignment.TOP_LEFT);
	}

	/**
	 * Creates the grid layout.
	 *
	 * @param panelContent
	 *            the panel content
	 * @return the grid layout
	 */
	protected final GridLayout createGridLayout(final VerticalLayout panelContent) {
		final GridLayout grid = new GridLayout(2, 1);
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.setHeight(100, Unit.PERCENTAGE);
		grid.setColumnExpandRatio(0, 1);
		grid.setColumnExpandRatio(1, 1);
		grid.setSpacing(true);
		panelContent.addComponent(grid);
		panelContent.setExpandRatio(grid, ContentRatio.LARGE);
		return grid;
	}

}

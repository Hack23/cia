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
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.MenuItemConstants;
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
 * This abstract class provides common functionality for creating menu items and
 * button links
 * in the Citizen Intelligence Agency web application. It includes methods for
 * initializing
 * application menus, creating admin menus, and generating button links with
 * descriptions.
 */
public abstract class AbstractMenuItemFactoryImpl implements MenuItemConstants {

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
	 *                     the main view item
	 */
	private static void createAdminMenu(final MenuItem mainViewItem) {
		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN)) {
			final MenuItem adminMenuItem = mainViewItem.addItem(ADMIN_TEXT, VaadinIcons.SERVER, null);

			final MenuItem configurationMenuItem = adminMenuItem.addItem(CONFIGURATION, VaadinIcons.TOOLS, null);
			configurationMenuItem.addItem(APPLICATION_CONFIGURATION, VaadinIcons.TOOLS,
					COMMAND_APPLICATION_CONFIGURATION);

			configurationMenuItem.addItem(AGENCY, VaadinIcons.SERVER, COMMAND_AGENCY);
			configurationMenuItem.addItem(PORTAL, VaadinIcons.SITEMAP, COMMAND_PORTAL);
			configurationMenuItem.addItem(COUNTRY, VaadinIcons.FLAG, COMMAND_COUNTRY);
			configurationMenuItem.addItem(LANGUAGE, VaadinIcons.ACCESSIBILITY, COMMAND_LANGUAGE);

			final MenuItem managementMenuItem = adminMenuItem.addItem(MANAGEMENT, VaadinIcons.SERVER, null);

			managementMenuItem.addItem(AGENT_OPERATIONS_TEXT, VaadinIcons.BRIEFCASE, COMMAND_AGENT_OPERATION);

			managementMenuItem.addItem(DATA_SUMMARY_TEXT, VaadinIcons.DATABASE, COMMAND_DATASUMMARY);
			managementMenuItem.addItem(DATA_AUTHOR_SUMMARY, VaadinIcons.DATABASE, COMMAND_AUTHOR_DATASUMMARY);

			managementMenuItem.addItem(EMAIL, VaadinIcons.MAILBOX, COMMAND_EMAIL);

			managementMenuItem.addItem(SYSTEM_PERFORMANCE, VaadinIcons.DASHBOARD, COMMAND_MONITORING);

			final MenuItem userActivityMenuItem = adminMenuItem.addItem(USER_ACTIVITY, VaadinIcons.DATABASE, null);
			userActivityMenuItem.addItem(APPLICATION_SESSION, VaadinIcons.LAPTOP, COMMAND_APPLICATION_SESSION);
			userActivityMenuItem.addItem(APPLICATION_SESSION_CHARTS, VaadinIcons.LAPTOP,
					COMMAND_APPLICATION_SESSION_CHARTS);
			userActivityMenuItem.addItem(APPLICATION_EVENT, VaadinIcons.ARROWS, COMMAND_APPLICATION_EVENTS);
			userActivityMenuItem.addItem(APPLICATION_EVENT_CHARTS, VaadinIcons.ARROWS,
					COMMAND_APPLICATION_EVENTS_CHARTS);

			userActivityMenuItem.addItem(USERACCOUNT, VaadinIcons.GROUP, COMMAND_USERACCOUNT);

		}
	}

	/**
	 * Creates the button link.
	 *
	 * @param row
	 *                    the panel content
	 * @param linkText
	 *                    the link text
	 * @param icon
	 *                    the icon
	 * @param command
	 *                    the command
	 * @param description
	 *                    the description
	 */
	protected static final void createButtonLink(final ResponsiveRow row, final String linkText, final Resource icon,
			final ClickListener command, final String description) {
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

		row.addColumn().withDisplayRules(DISPLAY_SIZE_XS_DEVICE, DISPLAYS_SIZE_XM_DEVICE, DISPLAY_SIZE_MD_DEVICE,
				DISPLAY_SIZE_LG_DEVICE).withComponent(layout);
	}

	/**
	 * Inits the application menu bar.
	 *
	 * @param menuBar
	 *                the menu bar
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

		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN)
				|| UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {
			mainViewItem.addItem(USERHOME, VaadinIcons.USER, COMMAND_USERHOME);
			createAdminMenu(mainViewItem);
			mainViewItem.addItem(LOGOUT, VaadinIcons.SIGN_OUT, COMMAND_LOGOUT);
		} else {
			mainViewItem.addItem(LOGIN, VaadinIcons.SIGN_IN, COMMAND_LOGIN);
			mainViewItem.addItem(REGISTER, VaadinIcons.FILE_ADD, COMMAND_REGISTER);
		}

	}

}

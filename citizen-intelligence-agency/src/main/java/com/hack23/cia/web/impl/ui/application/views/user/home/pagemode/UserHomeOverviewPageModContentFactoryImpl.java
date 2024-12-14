/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeOverviewPageModContentFactoryImpl.
 */
@Component
public final class UserHomeOverviewPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant OVERVIEW. */
	private static final String OVERVIEW = "Overview";

	/** The user home menu item factory. */
	@Autowired
	private UserHomeMenuItemFactory userHomeMenuItemFactory;

	/**
	 * Instantiates a new user home overview page mod content factory impl.
	 */
	public UserHomeOverviewPageModContentFactoryImpl() {
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
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout panelContent = createPanelContent();
		panel.setContent(panelContent);

		final String pageId = getPageId(parameters);
		final Optional<UserAccount> userAccount = getActiveUserAccount();

		if (userAccount.isPresent()) {

			userHomeMenuItemFactory.createUserHomeMenuBar(menuBar, pageId);

			createPageHeader(panel, panelContent,"CitizenIntelligence Agency::UserHome::Overview",OVERVIEW,"Manage user and security settings");

			// Logout button
			final Button logoutButton = new Button(LOGOUT, VaadinIcons.SIGN_OUT);
			final LogoutRequest logoutRequest = new LogoutRequest();
			logoutRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
			logoutButton.addClickListener(new LogoutClickListener(logoutRequest));
			panelContent.addComponent(logoutButton);

			// Create a card-style panel for user details
			final Panel cardPanel = new Panel();
			cardPanel.addStyleName("politician-overview-card");
			cardPanel.setWidth("100%");
			cardPanel.setHeightUndefined();
			Responsive.makeResponsive(cardPanel);

			final VerticalLayout cardContent = new VerticalLayout();
			cardContent.setMargin(true);
			cardContent.setSpacing(true);
			cardContent.setWidth("100%");
			cardPanel.setContent(cardContent);

			panelContent.addComponent(cardPanel);
			panelContent.setExpandRatio(cardPanel, ContentRatio.SMALL_GRID);

			// Header layout for the card
			final HorizontalLayout headerLayout = new HorizontalLayout();
			headerLayout.setSpacing(true);
			headerLayout.setWidth("100%");
			headerLayout.addStyleName("card-header-section");

			final Label titleLabel = new Label("User Account Information", ContentMode.HTML);
			titleLabel.addStyleName("card-title");
			titleLabel.setWidthUndefined();
			headerLayout.addComponent(titleLabel);
			cardContent.addComponent(headerLayout);

			// Divider line
			final Label divider = new Label("<hr/>", ContentMode.HTML);
			divider.addStyleName("card-divider");
			divider.setWidth("100%");
			cardContent.addComponent(divider);

			// Two-column layout for user attributes
			final HorizontalLayout attributesLayout = new HorizontalLayout();
			attributesLayout.setSpacing(true);
			attributesLayout.setWidth("100%");
			cardContent.addComponent(attributesLayout);

			// Left column: Basic Profile
			final VerticalLayout profileLayout = new VerticalLayout();
			profileLayout.setSpacing(true);
			profileLayout.addStyleName("card-details-column");
			profileLayout.setWidthUndefined();

			final Label profileHeader = new Label("Profile Details");
			profileHeader.addStyleName("card-section-title");
			profileLayout.addComponent(profileHeader);

			// Display key fields from user account in Profile Details
			final UserAccount account = userAccount.get();
			profileLayout.addComponent(createInfoRow("Username:", account.getUsername(), VaadinIcons.USER, "Your unique username"));
			profileLayout.addComponent(createInfoRow("Email:", account.getEmail(), VaadinIcons.ENVELOPE_O, "Your registered email address"));
			profileLayout.addComponent(createInfoRow("Country:", account.getCountry(), VaadinIcons.GLOBE, "Country of residence"));
			profileLayout.addComponent(createInfoRow("Created Date:", String.valueOf(account.getCreatedDate()), VaadinIcons.CALENDAR, "Date when the account was created"));

			// Right column: Status & Statistics
			final VerticalLayout statusLayout = new VerticalLayout();
			statusLayout.setSpacing(true);
			statusLayout.addStyleName("card-details-column");
			statusLayout.setWidthUndefined();

			final Label statusHeader = new Label("Status & Statistics");
			statusHeader.addStyleName("card-section-title");
			statusLayout.addComponent(statusHeader);

			statusLayout.addComponent(createInfoRow("User Type:", account.getUserType().toString(), VaadinIcons.INFO_CIRCLE, "Type of user account"));
			statusLayout.addComponent(createInfoRow("User Role:", account.getUserRole().toString(), VaadinIcons.USER_CHECK, "Your assigned role in the system"));
			statusLayout.addComponent(createInfoRow("Email Status:", account.getUserEmailStatus().toString(), VaadinIcons.ENVELOPE, "Status of email verification"));
			statusLayout.addComponent(createInfoRow("Number of Visits:", String.valueOf(account.getNumberOfVisits()), VaadinIcons.CHART, "How many times you have visited"));

			attributesLayout.addComponents(profileLayout, statusLayout);

			panelContent.setExpandRatio(logoutButton, ContentRatio.SMALL);

			// Overview layout after card
			final VerticalLayout overviewLayout = new VerticalLayout();
			overviewLayout.setSizeFull();

			panelContent.addComponent(overviewLayout);
			panelContent.setExpandRatio(overviewLayout, ContentRatio.LARGE_FORM);

			userHomeMenuItemFactory.createOverviewPage(overviewLayout);
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_USER_HOME_VIEW, ApplicationEventGroup.USER,
				NAME, parameters, pageId);

		return panelContent;

	}

	/**
	 * Creates a row displaying a caption and value, with optional icon and tooltip.
	 *
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon for better visual cue
	 * @param tooltip optional tooltip to provide more info
	 * @return a HorizontalLayout representing the info row
	 */
	private HorizontalLayout createInfoRow(final String caption, final String value, VaadinIcons icon,
			final String tooltip) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addStyleName("metric-label");
		layout.setWidthUndefined();

		if (icon != null) {
			final Label iconLabel = new Label(icon.getHtml(), ContentMode.HTML);
			iconLabel.addStyleName("card-info-icon");
			if (tooltip != null && !tooltip.isEmpty()) {
				iconLabel.setDescription(tooltip);
			}
			layout.addComponent(iconLabel);
		}

		final Label captionLabel = new Label(caption);
		captionLabel.addStyleName("card-info-caption");
		if (tooltip != null && !tooltip.isEmpty()) {
			captionLabel.setDescription(tooltip);
		}

		final Label valueLabel = new Label(value != null ? value : "");
		valueLabel.addStyleName("card-info-value");

		layout.addComponents(captionLabel, valueLabel);
		return layout;
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
		return NAME.equals(page)
				&& (StringUtils.isEmpty(parameters) || parameters.contains(PageMode.OVERVIEW.toString()));
	}

}

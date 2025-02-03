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
package com.hack23.cia.web.impl.ui.application.views.user.home.pagemode;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.user.impl.UserAccount;
import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.UserHomeMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandUserHomeConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class UserHomeOverviewPageModContentFactoryImpl.
 */
@Component
public final class UserHomeOverviewPageModContentFactoryImpl extends AbstractUserHomePageModContentFactoryImpl {

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

			CardInfoRowUtil.createPageHeader(panel, panelContent,
				UserHomeViewConstants.TITLE_PREFIX + UserHomeViewConstants.OVERVIEW_TITLE,
				UserHomeViewConstants.OVERVIEW_TITLE,
				UserHomeViewConstants.OVERVIEW_DESC);

			final Button logoutButton = new Button(UserHomeViewConstants.BUTTON_LOGOUT, VaadinIcons.SIGN_OUT);
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

			CardInfoRowUtil.createCardHeader(cardContent,"User Account Information");

			// Two-column layout for user attributes
			final HorizontalLayout attributesLayout = new HorizontalLayout();
			attributesLayout.setSpacing(true);
			attributesLayout.setWidth("100%");
			cardContent.addComponent(attributesLayout);

			// Left column: Basic Profile
			final VerticalLayout profileLayout = CardInfoRowUtil.createSectionLayout(UserHomeViewConstants.PROFILE_SECTION_TITLE);

			// Display key fields from user account in Profile Details
			final UserAccount account = userAccount.get();
			profileLayout.addComponent(CardInfoRowUtil.createInfoRow(
                UserHomeViewConstants.USERNAME_LABEL,
                account.getUsername(),
                VaadinIcons.USER,
                UserHomeViewConstants.USERNAME_DESC));
            profileLayout.addComponent(CardInfoRowUtil.createInfoRow(
                UserHomeViewConstants.EMAIL_LABEL,
                account.getEmail(),
                VaadinIcons.ENVELOPE_O,
                UserHomeViewConstants.EMAIL_DESC));
            profileLayout.addComponent(CardInfoRowUtil.createInfoRow(
                UserHomeViewConstants.COUNTRY_LABEL,
                account.getCountry(),
                VaadinIcons.GLOBE,
                UserHomeViewConstants.COUNTRY_DESC));
            profileLayout.addComponent(CardInfoRowUtil.createInfoRow(
                UserHomeViewConstants.CREATED_DATE_LABEL,
                String.valueOf(account.getCreatedDate()),
                VaadinIcons.CALENDAR,
                UserHomeViewConstants.CREATED_DATE_DESC));

			// Right column: Status & Statistics
			final VerticalLayout statusLayout = CardInfoRowUtil.createSectionLayout(UserHomeViewConstants.STATUS_SECTION_TITLE);

            statusLayout.addComponent(CardInfoRowUtil.createInfoRow(
                UserHomeViewConstants.USER_TYPE_LABEL,
                account.getUserType().toString(),
                VaadinIcons.INFO_CIRCLE,
                UserHomeViewConstants.USER_TYPE_DESC));
			statusLayout.addComponent(CardInfoRowUtil.createInfoRow("User Role:", account.getUserRole().toString(), VaadinIcons.USER_CHECK, "Your assigned role in the system"));
			statusLayout.addComponent(CardInfoRowUtil.createInfoRow("Email Status:", account.getUserEmailStatus().toString(), VaadinIcons.ENVELOPE, "Status of email verification"));
			statusLayout.addComponent(CardInfoRowUtil.createInfoRow("Number of Visits:", String.valueOf(account.getNumberOfVisits()), VaadinIcons.CHART, "How many times you have visited"));

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
	 * Matches.
	 *
	 * @param page the page
	 * @param parameters the parameters
	 * @return true, if successful
	 */
	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandUserHomeConstants.COMMAND_USERHOME_OVERVIEW.matchesPage(page, parameters);
	}

}

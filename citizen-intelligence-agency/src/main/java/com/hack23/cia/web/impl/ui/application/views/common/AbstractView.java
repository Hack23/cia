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
package com.hack23.cia.web.impl.ui.application.views.common;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.web.impl.ui.application.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;

/**
 * The Class AbstractView.
 */
public abstract class AbstractView extends Panel implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The page mode content factory map. */
	private final transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/** The page name. */
	private final String pageName;

	/** The barmenu. */
	private final MenuBar barmenu = new MenuBar();

	/** The top header right panel. */
	private final HorizontalLayout topHeaderRightPanel = new HorizontalLayout();

	/** The panel. */
	private Panel panel;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/** The page action event helper. */
	@Autowired
	protected transient PageActionEventHelper pageActionEventHelper;

	/**
	 * Instantiates a new abstract view.
	 */
	protected AbstractView(final Map<String, PageModeContentFactory> pageModeContentFactoryMap, final String pageName) {
		super();
		this.pageModeContentFactoryMap = pageModeContentFactoryMap;
		this.pageName = pageName;
	}

	/**
	 * Post construct.
	 */
	@PostConstruct
	public final void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(pageName);
	}


	@Override
	public final void enter(final ViewChangeEvent event) {
		try {

			final String parameters = event.getParameters();
			for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {
				if (pageModeContentFactory.matches(pageName, parameters)) {
					getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));
					return;
				}
			}
		} catch (final AccessDeniedException e ) {
			LOGGER.warn("Access denided:" +pageName,e);
			final VerticalLayout panelContent = new VerticalLayout();
			panelContent.setMargin(true);
			panelContent.setWidth(100, Unit.PERCENTAGE);
			panelContent.setHeight(100, Unit.PERCENTAGE);
			LabelFactory.createHeader2Label(panelContent,"Access denided:" +pageName);
			getPanel().setContent(panelContent);
			getPanel().setCaption("Access denied");
		}
	}

	/**
	 * Creates the basic layout with panel and footer.
	 *
	 * @param panelName
	 *            the panel name
	 */
	protected final void createBasicLayoutWithPanelAndFooter(final String panelName) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);


		final VerticalLayout pageModeContent = new VerticalLayout();
		pageModeContent.setMargin(false);
		pageModeContent.setSpacing(false);
		pageModeContent.setWidth(100, Unit.PERCENTAGE);
		pageModeContent.setHeight(100, Unit.PERCENTAGE);

		layout.addComponent(pageModeContent);

		final ThemeResource ciaLogoResource = new ThemeResource("cia-logo.png");

		final Image ciaLogoImage = new Image(null,ciaLogoResource);

		final HorizontalLayout topHeader = new HorizontalLayout();

		topHeader.addComponent(ciaLogoImage);
		ciaLogoImage.setWidth("60px");
		ciaLogoImage.setHeight("60px");
		topHeader.setComponentAlignment(ciaLogoImage, Alignment.MIDDLE_LEFT);
		topHeader.setExpandRatio(ciaLogoImage, ContentRatio.SMALL);


		final HorizontalLayout topTitleHeadertPanel = new HorizontalLayout();


		final Label titleLabel = new Label("Citizen Intelligence Agency");
		titleLabel.setStyleName("Header");
		topTitleHeadertPanel.addComponent(titleLabel);
		topTitleHeadertPanel.setComponentAlignment(titleLabel, Alignment.MIDDLE_LEFT);

		final Label sloganLabel = new Label("// Tracking politicians like bugs!");
		sloganLabel.setStyleName("HeaderSlogan");
		topTitleHeadertPanel.addComponent(sloganLabel);
		topTitleHeadertPanel.setComponentAlignment(sloganLabel, Alignment.MIDDLE_RIGHT);

		topHeader.addComponent(topTitleHeadertPanel);
		topHeader.setComponentAlignment(topTitleHeadertPanel, Alignment.MIDDLE_LEFT);
		topHeader.setExpandRatio(topTitleHeadertPanel, ContentRatio.GRID);


		topHeaderRightPanel.removeAllComponents();
		topHeader.addComponent(topHeaderRightPanel);
		topHeader.setComponentAlignment(topHeaderRightPanel, Alignment.MIDDLE_RIGHT);
		topHeader.setExpandRatio(topHeaderRightPanel, ContentRatio.LARGE);



		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN) || UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {


			final Link userHomePageLink = pageLinkFactory.createUserHomeViewPageLink();
			topHeaderRightPanel.addComponent(userHomePageLink);
			topHeaderRightPanel.setComponentAlignment(userHomePageLink, Alignment.MIDDLE_RIGHT);


			final Button logoutButton = new Button(LOGOUT,FontAwesome.SIGN_OUT);

			final LogoutRequest logoutRequest = new LogoutRequest();
			logoutRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
			logoutButton.addClickListener(new LogoutClickListener(logoutRequest));

			topHeaderRightPanel.addComponent(logoutButton);
			topHeaderRightPanel.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);

		} else {
			final Link createRegisterPageLink = pageLinkFactory.createRegisterPageLink();
			topHeaderRightPanel.addComponent(createRegisterPageLink);
			topHeaderRightPanel.setComponentAlignment(createRegisterPageLink, Alignment.MIDDLE_RIGHT);

			final Link createLoginPageLink = pageLinkFactory.createLoginPageLink();
			topHeaderRightPanel.addComponent(createLoginPageLink);
			topHeaderRightPanel.setComponentAlignment(createLoginPageLink, Alignment.MIDDLE_RIGHT);
		}


		topHeaderRightPanel.setWidth("100%");
		topHeaderRightPanel.setHeight("50px");

		topHeader.setWidth("100%");
		topHeader.setHeight("50px");

		pageModeContent.addComponent(topHeader);
		pageModeContent.setComponentAlignment(topHeader, Alignment.TOP_CENTER);


		pageModeContent.addComponent(getBarmenu());
		pageModeContent.setComponentAlignment(getBarmenu(), Alignment.TOP_CENTER);

		panel = new Panel(panelName);
		panel.addStyleName("v-panel-page-panel");

		panel.setSizeFull();
		pageModeContent.addComponent(panel);
		pageModeContent.setExpandRatio(panel, ContentRatio.FULL_SIZE);

		pageModeContent.addComponent(pageLinkFactory.createMainViewPageLink());
		setContent(layout);

		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setSizeFull();

	}

	/**
	 * Gets the barmenu.
	 *
	 * @return the barmenu
	 */
	public final MenuBar getBarmenu() {
		return barmenu;
	}

	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	protected final Panel getPanel() {
		return panel;
	}

}

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.ApplicationManager;
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
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractView.
 */
public abstract class AbstractView extends Panel implements View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	/** The application manager. */
	@Autowired
	private ApplicationManager applicationManager;

	
	/** The page mode content factory map. */
	private transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

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
			final VerticalLayout panelContent = new VerticalLayout();
			panelContent.setMargin(true);
			panelContent.setWidth(100, Unit.PERCENTAGE);
			panelContent.setHeight(100, Unit.PERCENTAGE);
			panelContent.addComponent(LabelFactory.createHeader2Label("Access denided:" +pageName));
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

		final VerticalLayout pageModeContent = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(pageModeContent);

		final ThemeResource ciaLogoResource = new ThemeResource("cia-logo.png");
		
		final Image ciaLogoImage = new Image(null,ciaLogoResource);
		
		final HorizontalLayout topHeader = new HorizontalLayout();
		
		topHeader.addComponent(ciaLogoImage);
		ciaLogoImage.setWidth("75px");
		ciaLogoImage.setHeight("75px");
		topHeader.setComponentAlignment(ciaLogoImage, Alignment.MIDDLE_LEFT);
		
		topHeaderRightPanel.removeAllComponents();
		topHeader.addComponent(topHeaderRightPanel);
		topHeader.setComponentAlignment(topHeaderRightPanel, Alignment.MIDDLE_RIGHT);

		
		
		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN) || UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {

			
			Link userHomePageLink = pageLinkFactory.createUserHomeViewPageLink();	
			topHeaderRightPanel.addComponent(userHomePageLink);
			topHeaderRightPanel.setComponentAlignment(userHomePageLink, Alignment.MIDDLE_RIGHT);

			
			final Button logoutButton = new Button(LOGOUT);

			final LogoutRequest logoutRequest = new LogoutRequest();
			logoutRequest.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
			logoutButton.addClickListener(new LogoutClickListener(logoutRequest,applicationManager));

			topHeaderRightPanel.addComponent(logoutButton);
			topHeaderRightPanel.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);

		} else {
			Link createRegisterPageLink = pageLinkFactory.createRegisterPageLink();	
			topHeaderRightPanel.addComponent(createRegisterPageLink);
			topHeaderRightPanel.setComponentAlignment(createRegisterPageLink, Alignment.MIDDLE_RIGHT);

			Link createLoginPageLink = pageLinkFactory.createLoginPageLink();	
			topHeaderRightPanel.addComponent(createLoginPageLink);
			topHeaderRightPanel.setComponentAlignment(createLoginPageLink, Alignment.MIDDLE_RIGHT);
		}
		
				
		topHeaderRightPanel.setWidth("100%");
		topHeaderRightPanel.setHeight("60px");
		
		topHeader.setWidth("100%");
		topHeader.setHeight("60px");
		
		pageModeContent.addComponent(topHeader);
		pageModeContent.setComponentAlignment(topHeader, Alignment.TOP_CENTER);
		
		
		barmenu.setWidth("50%");
		pageModeContent.addComponent(barmenu);
		pageModeContent.setComponentAlignment(barmenu, Alignment.TOP_CENTER);

		panel = new Panel(panelName);

		panel.setSizeFull();
		pageModeContent.addComponent(panel);
		pageModeContent.setExpandRatio(panel, ContentRatio.FULL_SIZE);

		pageModeContent.addComponent(pageLinkFactory.createMainViewPageLink());
		setContent(layout);

		pageModeContent.setWidth(100, Unit.PERCENTAGE);
		pageModeContent.setHeight(100, Unit.PERCENTAGE);

		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);

	}

	/**
	 * Gets the barmenu.
	 *
	 * @return the barmenu
	 */
	public final MenuBar getBarmenu() {
		barmenu.setWidth("100%");
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

	/**
	 * Gets the top header right panel.
	 *
	 * @return the top header right panel
	 */
	protected final HorizontalLayout getTopHeaderRightPanel() {
		return topHeaderRightPanel;
	}

}

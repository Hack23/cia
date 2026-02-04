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
package com.hack23.cia.web.impl.ui.application.views.common.abstracts;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.RequestContextHolder;

import com.hack23.cia.service.api.action.application.LogoutRequest;
import com.hack23.cia.web.impl.ui.application.views.common.action.PageActionEventHelper;
import com.hack23.cia.web.impl.ui.application.views.common.util.UserContextUtil;
import com.hack23.cia.web.impl.ui.application.views.common.labelfactory.LabelFactory;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.ApplicationMenuItemFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagelinks.api.PageLinkFactory;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.PageModeContentFactory;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.LogoutClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AbstractView.
 */
public abstract class AbstractView extends Panel implements View {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractView.class);

	/** The Constant LOGOUT. */
	private static final String LOGOUT = "Logout";

	/** The Constant ROLE_ADMIN. */
	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	/** The Constant ROLE_USER. */
	private static final String ROLE_USER = "ROLE_USER";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The barmenu. */
	private final MenuBar barmenu = new MenuBar();

	/** The menu item factory. */
	@Autowired
	private transient ApplicationMenuItemFactory menuItemFactory;

	/** The page action event helper. */
	@Autowired
	protected transient PageActionEventHelper pageActionEventHelper;

	/** The page link factory. */
	@Autowired
	protected transient PageLinkFactory pageLinkFactory;

	/** The page mode content factory map. */
	private transient Map<String, PageModeContentFactory> pageModeContentFactoryMap;

	/** The application name. */
	@Value("${application.name}")
	protected String applicationName;

	/** The application version. */
	@Value("${application.version}")
	protected String applicationVersion;

	/** The application url. */
	@Value("${application.url}")
	protected String applicationUrl;

	/** The page name. */
	private final String pageName;

	/** The panel. */
	private Panel panel;

	/** The top header right panel. */
	private final HorizontalLayout topHeaderRightPanel = new HorizontalLayout();


	/**
	 * Instantiates a new abstract view.
	 *
	 * @param pageModeContentFactoryMap the page mode content factory map
	 * @param pageName the page name
	 */
	protected AbstractView(final Map<String, PageModeContentFactory> pageModeContentFactoryMap, final String pageName) {
		super();
		this.pageModeContentFactoryMap = pageModeContentFactoryMap;
		this.pageName = pageName;
	}

	/**
	 * Adds the logo to header.
	 *
	 * @param topHeader
	 *            the top header
	 */
	private static void addLogoToHeader(final HorizontalLayout topHeader) {
		final ThemeResource ciaLogoResource = new ThemeResource("cia-logo.png");
		final Image ciaLogoImage = new Image(null,ciaLogoResource);
		topHeader.addComponent(ciaLogoImage);
		ciaLogoImage.setWidth("60px");
		ciaLogoImage.setHeight("60px");
		topHeader.setComponentAlignment(ciaLogoImage, Alignment.MIDDLE_LEFT);
		topHeader.setExpandRatio(ciaLogoImage, ContentRatio.SMALL);
	}


	/**
	 * Creates the full size vertical layout.
	 *
	 * @return the vertical layout
	 */
	private static VerticalLayout createFullSizeVerticalLayout() {
		return createFullSizeVerticalLayout(true,true);
	}

	/**
	 * Creates the full size vertical layout.
	 *
	 * @param margin
	 *            the margin
	 * @param spacing
	 *            the spacing
	 * @return the vertical layout
	 */
	private static VerticalLayout createFullSizeVerticalLayout(final boolean margin, final boolean spacing) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(margin);
		layout.setSpacing(spacing);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.setHeight(100, Unit.PERCENTAGE);
		return layout;
	}

	/**
	 * Creates the top title header.
	 *
	 * @param topHeader
	 *            the top header
	 */
	private static void createTopTitleHeader(final HorizontalLayout topHeader) {
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
	}

	/**
	 * Creates the basic layout with panel and footer.
	 *
	 * @param panelName
	 *            the panel name
	 */
	protected final void createBasicLayoutWithPanelAndFooter(final String panelName) {
		final VerticalLayout layout = createFullSizeVerticalLayout();
		final VerticalLayout pageModeContent = createFullSizeVerticalLayout(false,false);
		layout.addComponent(pageModeContent);

		final HorizontalLayout topHeader = new HorizontalLayout();

		addLogoToHeader(topHeader);
		createTopTitleHeader(topHeader);

		topHeaderRightPanel.removeAllComponents();
		topHeader.addComponent(topHeaderRightPanel);
		topHeader.setComponentAlignment(topHeaderRightPanel, Alignment.MIDDLE_RIGHT);
		topHeader.setExpandRatio(topHeaderRightPanel, ContentRatio.LARGE);

		createTopHeaderActionsForUserContext();

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

		final HorizontalLayout footerTop = new HorizontalLayout();
		final HorizontalLayout footerBottom = new HorizontalLayout();
		final Link createMainViewPageLink = pageLinkFactory.createMainViewPageLink();
		final Label appVersion = new Label(applicationName + " (" + applicationVersion +")(Apache License 2.0)");
		final Link spdxLink = new Link("SBOM(spdx)", new ExternalResource("https://github.com/Hack23/cia/releases/download/" + applicationVersion +"/com.hack23.cia_citizen-intelligence-agency-" + applicationVersion +".spdx.json"));
		final Link sourcCodeLink = new Link("https://github.com/Hack23/cia", new ExternalResource("https://github.com/Hack23/cia"));
		final Label licenseLink = new Label("Open Source");

		footerTop.addComponent(createMainViewPageLink);
		footerTop.addComponent(appVersion);
		footerBottom.addComponent(licenseLink);
		footerBottom.addComponent(sourcCodeLink);
		footerBottom.addComponent(spdxLink);
		footerTop.setComponentAlignment(createMainViewPageLink, Alignment.MIDDLE_LEFT);
		footerTop.setComponentAlignment(appVersion, Alignment.MIDDLE_LEFT);

		footerBottom.setComponentAlignment(licenseLink, Alignment.MIDDLE_LEFT);
		footerBottom.setComponentAlignment(sourcCodeLink, Alignment.MIDDLE_LEFT);
		footerBottom.setComponentAlignment(spdxLink, Alignment.MIDDLE_LEFT);

		footerTop.setWidth("100%");
		footerTop.setHeight("25px");
		footerBottom.setWidth("100%");
		footerBottom.setHeight("25px");

		pageModeContent.addComponent(footerTop);
		pageModeContent.addComponent(footerBottom);

		setContent(layout);

		setWidth(100, Unit.PERCENTAGE);
		setHeight(100, Unit.PERCENTAGE);
		setSizeFull();

	}

	/**
	 * Creates the top header actions for user context.
	 */
	private void createTopHeaderActionsForUserContext() {
		if (UserContextUtil.allowRoleInSecurityContext(ROLE_ADMIN) || UserContextUtil.allowRoleInSecurityContext(ROLE_USER)) {
			final Link userHomePageLink = pageLinkFactory.createUserHomeViewPageLink();
			topHeaderRightPanel.addComponent(userHomePageLink);
			topHeaderRightPanel.setComponentAlignment(userHomePageLink, Alignment.MIDDLE_RIGHT);

			final Button logoutButton = new Button(LOGOUT,VaadinIcons.SIGN_OUT);

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
	}

	@Override
	public final void enter(final ViewChangeEvent event) {
		try {

			final String parameters = Jsoup.clean(event.getParameters(), Safelist.basic());

			for (final PageModeContentFactory pageModeContentFactory : pageModeContentFactoryMap.values()) {
				if (pageModeContentFactory.matches(pageName, parameters) && pageModeContentFactory.validReference(parameters)) {
					getPanel().setContent(pageModeContentFactory.createContent(parameters, getBarmenu(), getPanel()));
					return;
				}
			}

			LOGGER.warn("SECURITY:Invalid reference, content not found:{}/{}",pageName, parameters);
			final VerticalLayout panelContent = createFullSizeVerticalLayout();

			menuItemFactory.createMainPageMenuBar(getBarmenu());

			LabelFactory.createHeader2Label(panelContent,"Invalid reference, content not found:" +pageName+ "/"+ parameters);

			getPanel().setContent(panelContent);
			getPanel().setCaption("Invalid Reference");
		} catch (final AccessDeniedException e ) {
			LOGGER.warn("Security:Authorization Failure:: {} : {}  exception : {}",e.getMessage(),pageName,e.getClass().getName());
			final VerticalLayout panelContent = createFullSizeVerticalLayout();
			LabelFactory.createHeader2Label(panelContent,"Access denied:" +pageName);
			getPanel().setContent(panelContent);
			getPanel().setCaption("Access denied");
		}
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

	/**
	 * Post construct.
	 */
	@PostConstruct
	public final void postConstruct() {
		setSizeFull();
		createBasicLayoutWithPanelAndFooter(pageName);
	}

}

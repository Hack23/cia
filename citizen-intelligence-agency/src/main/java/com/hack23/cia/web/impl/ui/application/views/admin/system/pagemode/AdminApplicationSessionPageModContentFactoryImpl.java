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
package com.hack23.cia.web.impl.ui.application.views.admin.system.pagemode;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession;
import com.hack23.cia.model.internal.application.system.impl.ApplicationSession_;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.admin.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationSessionPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationSessionPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant APPLICATION_ACTION_EVENT. */
	private static final String APPLICATION_ACTION_EVENT = "ApplicationActionEvent";

	/** The Constant APPLICATION_SESSION. */
	private static final String APPLICATION_SESSION = "ApplicationSession";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = {
			"hjid",
			"createdDate",
			"sessionType",
			"userId",
			"events",
			"operatingSystem",
			"locale",
			"ipInformation",
			"userAgentInformation",
			"sessionId"
	};

	/** The Constant COLUMN_ORDER2. */
	private static final String[] COLUMN_ORDER2 = {
			"hjid",
			"createdDate",
			"eventGroup",
			"actionName",
			"applicationOperation",
			"page",
			"pageMode",
			"elementId",
			"errorMessage",
			"applicationMessage",
			"modelObjectVersion"
	};

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = {
			"hjid",
			"modelObjectId",
			"modelObjectVersion",
			"userAgentInformation",
			"sessionId",
			"ipInformation"
	};

	/** The Constant HIDE_COLUMNS2. */
	private static final String[] HIDE_COLUMNS2 = {
			"hjid",
			"modelObjectId",
			"modelObjectVersion",
			"userId",
			"sessionId"
	};

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME;

	/**
	 * Instantiates a new admin application session page mod content factory impl.
	 */
	public AdminApplicationSessionPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr = getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		CardInfoRowUtil.createPageHeader(
				panel,
				content,
				 AdminViewConstants.ADMIN_APPLICATION_SESSIONS,
				 AdminViewConstants.SESSION_DETAILS,
				 AdminViewConstants.SESSION_OVERVIEW
		);

		final DataContainer<ApplicationSession, Long> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationSession.class);

		final List<ApplicationSession> pageOrderBy = dataContainer.getPageOrderBy(
				pageNr,
				DEFAULT_RESULTS_PER_PAGE,
				ApplicationSession_.createdDate
		);

		getPagingUtil().createPagingControls(
				content,
				NAME,
				pageId,
				dataContainer.getSize(),
				pageNr,
				DEFAULT_RESULTS_PER_PAGE
		);

		getGridFactory().createBasicBeanItemGrid(
				content,
				ApplicationSession.class,
				pageOrderBy,
				APPLICATION_SESSION,
				COLUMN_ORDER,
				HIDE_COLUMNS,
				new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_SESSION_VIEW_NAME, "hjid"),
				null,
				new ListPropertyConverter[] { new ListPropertyConverter("page", "events", "actionName") }
		);

		if (pageId != null && !pageId.isEmpty()) {
			final ApplicationSession applicationSession = dataContainer.load(Long.valueOf(pageId));
			if (applicationSession != null) {
				// Create container layout for both the session card and the events grid
				final HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setWidth(ContentSize.FULL_SIZE);
				content.addComponent(horizontalLayout);
				content.setExpandRatio(horizontalLayout, ContentRatio.GRID);

				// Left side: card layout for ApplicationSession details
				final Panel cardPanel = new Panel();
				cardPanel.addStyleName("politician-overview-card"); // Reuse existing card style
				cardPanel.setWidth("100%");
				cardPanel.setHeightUndefined();
				Responsive.makeResponsive(cardPanel);

				final VerticalLayout cardContent = new VerticalLayout();
				cardContent.setMargin(true);
				cardContent.setSpacing(true);
				cardContent.setWidth("100%");
				cardPanel.setContent(cardContent);

				horizontalLayout.addComponent(cardPanel);
				horizontalLayout.setExpandRatio(cardPanel, ContentRatio.GRID);

				CardInfoRowUtil.createCardHeader(cardContent, AdminViewConstants.APPLICATION_SESSION_DETAILS);

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				// Display each field if not null or empty
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.CREATED_DATE, String.valueOf(applicationSession.getCreatedDate()), VaadinIcons.CALENDAR);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.SESSION_TYPE, applicationSession.getSessionType().toString(), VaadinIcons.CONNECT);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.USER_ID, applicationSession.getUserId(), VaadinIcons.USER);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.SESSION_ID, applicationSession.getSessionId(), VaadinIcons.KEY);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.OPERATING_SYSTEM, applicationSession.getOperatingSystem(), VaadinIcons.DESKTOP);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.LOCALE, applicationSession.getLocale(), VaadinIcons.GLOBE);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.IP_INFORMATION, applicationSession.getIpInformation(), VaadinIcons.INFO);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.USER_AGENT, applicationSession.getUserAgentInformation(), VaadinIcons.BROWSER);

				// Right side: grid for ApplicationActionEvent (session events)
				final VerticalLayout rightLayout = new VerticalLayout();
				rightLayout.setSizeFull();
				rightLayout.addStyleName("v-layout-content-overview-panel-level2");
				horizontalLayout.addComponent(rightLayout);
				horizontalLayout.setExpandRatio(rightLayout, ContentRatio.GRID);

				getGridFactory().createBasicBeanItemGrid(
						rightLayout,
						ApplicationActionEvent.class,
						applicationSession.getEvents(),
						APPLICATION_ACTION_EVENT,
						COLUMN_ORDER2,
						HIDE_COLUMNS2,
						new PageItemPropertyClickListener(AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid"),
						null,
						null
				);
			}
		}

		getPageActionEventHelper().createPageEvent(
				ViewAction.VISIT_ADMIN_APPLICATION_SESSION_VIEW,
				ApplicationEventGroup.ADMIN,
				NAME,
				null,
				pageId
		);

		return content;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandAdminConstants.COMMAND_APPLICATION_SESSION.matches(page, parameters);
	}

}

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
import com.hack23.cia.model.internal.application.system.impl.ApplicationActionEvent_;
import com.hack23.cia.model.internal.application.system.impl.ApplicationEventGroup;
import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.web.impl.ui.application.action.ViewAction;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Component
public final class AdminApplicationEventsPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	private static final String APPLICATION_ACTION_EVENT = "ApplicationActionEvent";

	private static final String[] COLUMN_ORDER = { "hjid", "createdDate", "userId", "actionName", "errorMessage",
			"applicationMessage", "page", "pageMode", "elementId", "modelObjectVersion" };

	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion", "sessionId",
			"eventGroup", "applicationOperation" };

	private static final PageItemPropertyClickListener LISTENER = new PageItemPropertyClickListener(
			AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME, "hjid");

	/** The Constant NAME. */
	public static final String NAME = AdminViews.ADMIN_APPLICATIONS_EVENTS_VIEW_NAME;

	/**
	 * Instantiates a new admin application events page mod content factory impl.
	 */
	public AdminApplicationEventsPageModContentFactoryImpl() {
		super(NAME);
	}

	@Secured({ "ROLE_ADMIN" })
	@Override
	public Layout createContent(final String parameters, final MenuBar menuBar, final Panel panel) {
		final VerticalLayout content = createPanelContent();

		final String pageId = getPageId(parameters);
		final int pageNr = getPageNr(parameters);

		getMenuItemFactory().createMainPageMenuBar(menuBar);

		createPageHeader(panel, content, "Admin Application Events", "Event Details",
				"Review a comprehensive list of application events, including timestamps and statuses.");

		final DataContainer<ApplicationActionEvent, Long> dataContainer = getApplicationManager()
				.getDataContainer(ApplicationActionEvent.class);

		final List<ApplicationActionEvent> pageOrderBy = dataContainer.getPageOrderBy(pageNr, DEFAULT_RESULTS_PER_PAGE,
				ApplicationActionEvent_.createdDate);

		getPagingUtil().createPagingControls(content, NAME, pageId, dataContainer.getSize(), pageNr,
				DEFAULT_RESULTS_PER_PAGE);

		getGridFactory().createBasicBeanItemGrid(content, ApplicationActionEvent.class, pageOrderBy,
				APPLICATION_ACTION_EVENT, COLUMN_ORDER, HIDE_COLUMNS, LISTENER, null, null);

		if (pageId != null && !pageId.isEmpty()) {
			final ApplicationActionEvent applicationActionEvent = dataContainer.load(Long.valueOf(pageId));
			if (applicationActionEvent != null) {
				// Instead of form fields, use a card layout similar to the party overview.
				final Panel cardPanel = new Panel();
				cardPanel.addStyleName("politician-overview-card"); // Reuse existing style
				cardPanel.setWidth("100%");
				cardPanel.setHeightUndefined();
				Responsive.makeResponsive(cardPanel);

				final VerticalLayout cardContent = new VerticalLayout();
				cardContent.setMargin(true);
				cardContent.setSpacing(true);
				cardContent.setWidth("100%");
				cardPanel.setContent(cardContent);

				content.addComponent(cardPanel);

				createCardHeader(cardContent,"Application Event Details");

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				addInfoRowIfNotNull(attributesLayout, "Created Date:",
						String.valueOf(applicationActionEvent.getCreatedDate()), VaadinIcons.CALENDAR);
				addInfoRowIfNotNull(attributesLayout, "Event Group:",
						String.valueOf(applicationActionEvent.getEventGroup()), VaadinIcons.INFO);
				addInfoRowIfNotNull(attributesLayout, "Application Operation:",
						String.valueOf(applicationActionEvent.getApplicationOperation()), VaadinIcons.TOOLS);
				addInfoRowIfNotNull(attributesLayout, "Page:",
						String.valueOf(applicationActionEvent.getPage()), VaadinIcons.FILE_TEXT);
				addInfoRowIfNotNull(attributesLayout, "Page Mode:",
						String.valueOf(applicationActionEvent.getPageMode()), VaadinIcons.LIST);
				addInfoRowIfNotNull(attributesLayout, "Element Id:",
						String.valueOf(applicationActionEvent.getElementId()), VaadinIcons.POINTER);
				addInfoRowIfNotNull(attributesLayout, "Action Name:",
						String.valueOf(applicationActionEvent.getActionName()), VaadinIcons.PLAY_CIRCLE);
				addInfoRowIfNotNull(attributesLayout, "User Id:",
						String.valueOf(applicationActionEvent.getUserId()), VaadinIcons.USER);
				addInfoRowIfNotNull(attributesLayout, "Session Id:",
						String.valueOf(applicationActionEvent.getSessionId()), VaadinIcons.KEY);
				addInfoRowIfNotNull(attributesLayout, "Error Message:",
						String.valueOf(applicationActionEvent.getErrorMessage()), VaadinIcons.WARNING);
				addInfoRowIfNotNull(attributesLayout, "Application Message:",
						String.valueOf(applicationActionEvent.getApplicationMessage()), VaadinIcons.INFO_CIRCLE);
			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_EVENTS_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return NAME.equals(page) && (parameters == null || !parameters.contains(PageMode.CHARTS.toString()));
	}

}

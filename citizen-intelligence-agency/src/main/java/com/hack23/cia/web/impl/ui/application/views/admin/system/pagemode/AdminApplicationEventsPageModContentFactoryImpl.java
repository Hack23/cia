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
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViewConstants;
import com.hack23.cia.web.impl.ui.application.views.common.menufactory.api.pagecommands.PageCommandAdminConstants;
import com.hack23.cia.web.impl.ui.application.views.common.pagemode.CardInfoRowUtil;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationEventsPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationEventsPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	/** The Constant APPLICATION_ACTION_EVENT. */
	private static final String APPLICATION_ACTION_EVENT = "ApplicationActionEvent";

	/** The Constant COLUMN_ORDER. */
	private static final String[] COLUMN_ORDER = { "hjid", "createdDate", "userId", "actionName", "errorMessage",
			"applicationMessage", "page", "pageMode", "elementId", "modelObjectVersion" };

	/** The Constant HIDE_COLUMNS. */
	private static final String[] HIDE_COLUMNS = { "hjid", "modelObjectId", "modelObjectVersion", "sessionId",
			"eventGroup", "applicationOperation" };

	/** The Constant LISTENER. */
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

		CardInfoRowUtil.createPageHeader(panel, content, AdminViewConstants.ADMIN_APPLICATION_EVENTS, AdminViewConstants.EVENT_DETAILS,
				AdminViewConstants.EVENT_REVIEW);

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

				CardInfoRowUtil.createCardHeader(cardContent, AdminViewConstants.EVENT_DETAILS);

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.CREATED_DATE,
						String.valueOf(applicationActionEvent.getCreatedDate()), VaadinIcons.CALENDAR);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.EVENT_GROUP,
						String.valueOf(applicationActionEvent.getEventGroup()), VaadinIcons.INFO);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.APPLICATION_OPERATION,
						String.valueOf(applicationActionEvent.getApplicationOperation()), VaadinIcons.TOOLS);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.PAGE,
						String.valueOf(applicationActionEvent.getPage()), VaadinIcons.FILE_TEXT);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.PAGE_MODE,
						String.valueOf(applicationActionEvent.getPageMode()), VaadinIcons.LIST);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.ELEMENT_ID,
						String.valueOf(applicationActionEvent.getElementId()), VaadinIcons.POINTER);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.ACTION_NAME,
						String.valueOf(applicationActionEvent.getActionName()), VaadinIcons.PLAY_CIRCLE);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.USER_ID,
						String.valueOf(applicationActionEvent.getUserId()), VaadinIcons.USER);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.SESSION_ID,
						String.valueOf(applicationActionEvent.getSessionId()), VaadinIcons.KEY);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.ERROR_MESSAGE,
						String.valueOf(applicationActionEvent.getErrorMessage()), VaadinIcons.WARNING);
				CardInfoRowUtil.addInfoRowIfNotNull(attributesLayout, AdminViewConstants.APPLICATION_MESSAGE,
						String.valueOf(applicationActionEvent.getApplicationMessage()), VaadinIcons.INFO_CIRCLE);
			}
		}

		getPageActionEventHelper().createPageEvent(ViewAction.VISIT_ADMIN_APPLICATION_EVENTS_VIEW,
				ApplicationEventGroup.ADMIN, NAME, null, pageId);

		return content;
	}

	@Override
	public boolean matches(final String page, final String parameters) {
		return PageCommandAdminConstants.COMMAND_APPLICATION_EVENTS.matches(page, parameters);
	}

}

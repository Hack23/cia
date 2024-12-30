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
import com.hack23.cia.web.impl.ui.application.views.common.converters.ListPropertyConverter;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentRatio;
import com.hack23.cia.web.impl.ui.application.views.common.sizing.ContentSize;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.AdminViews;
import com.hack23.cia.web.impl.ui.application.views.common.viewnames.PageMode;
import com.hack23.cia.web.impl.ui.application.views.pageclicklistener.PageItemPropertyClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * The Class AdminApplicationSessionPageModContentFactoryImpl.
 */
@Component
public final class AdminApplicationSessionPageModContentFactoryImpl extends AbstractAdminSystemPageModContentFactoryImpl {

	private static final String APPLICATION_ACTION_EVENT = "ApplicationActionEvent";
	private static final String APPLICATION_SESSION = "ApplicationSession";

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

	private static final String[] HIDE_COLUMNS = {
			"hjid",
			"modelObjectId",
			"modelObjectVersion",
			"userAgentInformation",
			"sessionId",
			"ipInformation"
	};

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

		createPageHeader(
				panel,
				content,
				"Admin Application Sessions",
				"Session Details",
				"Explore detailed data on user sessions, including durations and activity logs."
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

				// Card Header
				final HorizontalLayout headerLayout = new HorizontalLayout();
				headerLayout.setSpacing(true);
				headerLayout.setWidth("100%");
				headerLayout.addStyleName("card-header-section");

				final String titleText = "Application Session Details";
				final Label titleLabel = new Label(titleText, ContentMode.HTML);
				titleLabel.addStyleName("card-title");
				titleLabel.setWidthUndefined();
				headerLayout.addComponent(titleLabel);

				cardContent.addComponent(headerLayout);

				// Divider
				final Label divider = new Label("<hr/>", ContentMode.HTML);
				divider.addStyleName("card-divider");
				divider.setWidth("100%");
				cardContent.addComponent(divider);

				// Attributes layout
				final VerticalLayout attributesLayout = new VerticalLayout();
				attributesLayout.setSpacing(true);
				attributesLayout.setWidth("100%");
				cardContent.addComponent(attributesLayout);

				// Display each field if not null or empty
				addInfoRowIfNotNull(attributesLayout, "Created Date:", String.valueOf(applicationSession.getCreatedDate()), VaadinIcons.CALENDAR);
				addInfoRowIfNotNull(attributesLayout, "Session Type:", applicationSession.getSessionType().toString(), VaadinIcons.CONNECT);
				addInfoRowIfNotNull(attributesLayout, "User Id:", applicationSession.getUserId(), VaadinIcons.USER);
				addInfoRowIfNotNull(attributesLayout, "Session Id:", applicationSession.getSessionId(), VaadinIcons.KEY);
				addInfoRowIfNotNull(attributesLayout, "Operating System:", applicationSession.getOperatingSystem(), VaadinIcons.DESKTOP);
				addInfoRowIfNotNull(attributesLayout, "Locale:", applicationSession.getLocale(), VaadinIcons.GLOBE);
				addInfoRowIfNotNull(attributesLayout, "IP Information:", applicationSession.getIpInformation(), VaadinIcons.INFO);
				addInfoRowIfNotNull(attributesLayout, "User Agent:", applicationSession.getUserAgentInformation(), VaadinIcons.BROWSER);

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
		return NAME.equals(page) && (parameters == null || !parameters.contains(PageMode.CHARTS.toString()));
	}

	/**
	 * Adds an info row to the parent layout if value is not null or empty.
	 *
	 * @param parent the parent layout
	 * @param caption the field caption
	 * @param value   the field value
	 * @param icon    a VaadinIcons icon
	 */
	private void addInfoRowIfNotNull(final VerticalLayout parent, final String caption, final String value, final VaadinIcons icon) {
		if (value != null && !value.trim().isEmpty() && !"null".equalsIgnoreCase(value)) {
			parent.addComponent(createInfoRow(caption, value, icon));
		}
	}
}
